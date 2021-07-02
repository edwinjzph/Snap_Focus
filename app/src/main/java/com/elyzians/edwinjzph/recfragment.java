package com.elyzians.edwinjzph;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class recfragment extends Fragment {
    static final boolean $assertionsDisabled = false;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    myadapter adapter;
    ShimmerFrameLayout layout5;
    private String mParam1;
    private String mParam2;
    TextView order;
    TextView order1;
    RecyclerView recview;
    DatabaseReference reference;

    public static recfragment newInstance(String str, String str2) {
        recfragment recfragment = new recfragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        recfragment.setArguments(bundle);
        return recfragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recfragment, viewGroup, false);
        this.order = (TextView) inflate.findViewById(R.id.noorder);
        this.order1 = (TextView) inflate.findViewById(R.id.noorder1);
        this.layout5 = (ShimmerFrameLayout) inflate.findViewById(R.id.shimmer4);
        Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.tool6);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                recfragment.this.startActivity(new Intent(recfragment.this.getActivity(), MainActivity.class));
            }
        });
        toolbar.setTitle((CharSequence) "My Orders");
        DatabaseReference child = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        this.reference = child;
        child.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("OrderDetails")) {
                    recfragment.this.order.setVisibility(View.VISIBLE);
                    recfragment.this.order1.setVisibility(View.VISIBLE);
                    recfragment.this.layout5.stopShimmer();
                    recfragment.this.layout5.hideShimmer();
                    recfragment.this.layout5.setVisibility(View.GONE);
                }
            }
        });
        child.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("OrderDetails")) {
                    recfragment.this.layout5.stopShimmer();
                    recfragment.this.layout5.hideShimmer();
                    recfragment.this.layout5.setVisibility(View.GONE);
                    recfragment.this.recview.setVisibility(View.VISIBLE);
                }
            }
        });
        this.order1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                recfragment.this.startActivity(new Intent(recfragment.this.getActivity(), retrive_address_credentials.class));
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recview);
        this.recview = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions build = new FirebaseRecyclerOptions.Builder().setQuery((Query) FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderDetails"), uploadinfo.class).build();
        postponeEnterTransition();
        ViewGroup viewGroup2 = (ViewGroup) inflate.getParent();
        myadapter myadapter = new myadapter(build);
        this.adapter = myadapter;
        this.recview.setAdapter(myadapter);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        this.adapter.startListening();
    }

    public void onStop() {
        super.onStop();
        this.adapter.stopListening();
    }



    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.drawable.ic_baseline_arrow_back_24) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
        return super.onContextItemSelected(menuItem);
    }
}
