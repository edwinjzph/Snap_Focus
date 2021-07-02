package com.elyzians.edwinjzph;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.Objects;

public class decfragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static TextView CANCEL;
    static DatabaseReference reference;
    String NAME;
    String Phonenum;
    String TAG = "main";
    String address;
    String amount;
    long amount2;
    String date;
    String email;
    String imagename;
    String imageurl;
    private String mParam1;
    private String mParam2;
    int pos;
    String potrait;
    private DatabaseReference reference2;
    String status;
    Toolbar toolbar;
    private FirebaseUser user;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public decfragment() {
    }

    public decfragment(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DatabaseReference databaseReference) {
        this.address = str;
        this.date = str2;
        this.Phonenum = str4;
        this.potrait = str3;
        this.imagename = str5;
        this.imageurl = str6;
        this.status = str7;
        this.pos = i;
        reference = databaseReference;
    }

    public static decfragment newInstance(String str, String str2) {
        decfragment decfragment = new decfragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, str);
        bundle.putString(ARG_PARAM2, str2);
        decfragment.setArguments(bundle);
        return decfragment;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Toolbar toolbar2 = (Toolbar) getView().findViewById(R.id.edwin2);
        this.toolbar = toolbar2;
        toolbar2.setTitle((CharSequence) "Order Details");
        this.toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((AppCompatActivity) decfragment.this.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).addToBackStack(null).commit();
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_decfragment, viewGroup, false);
        final TextView textView = (TextView) inflate.findViewById(R.id.nameholder);
        CANCEL = (TextView) inflate.findViewById(R.id.cancel);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.courseholder);
        final TextView textView3 = (TextView) inflate.findViewById(R.id.emailholder);
        final TextView textView4 = (TextView) inflate.findViewById(R.id.address);
        final TextView textView5 = (TextView) inflate.findViewById(R.id.Phone);
        reference.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadinfo uploadinfo = (uploadinfo) dataSnapshot.getValue(uploadinfo.class);
                if (uploadinfo != null) {
                    decfragment.this.NAME = uploadinfo.Potrait;
                }
            }
        });
        this.user = FirebaseAuth.getInstance().getCurrentUser();
        this.reference2 = FirebaseDatabase.getInstance().getReference("Users");
        this.reference2.child(this.user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                user user = (user) dataSnapshot.getValue(user.class);
                if (user != null) {
                    decfragment.this.email = user.Email;
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(decfragment.this.getActivity(), "Something Wrong Happened", Toast.LENGTH_LONG).show();
            }
        });
        textView4.setText(this.address);
        textView.setText(this.potrait);
        textView5.setText(this.Phonenum);
        textView2.setText(this.status);
        textView3.setText(this.date);
        reference.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                uploadinfo uploadinfo = (uploadinfo) dataSnapshot.getValue(uploadinfo.class);
                if (uploadinfo != null) {
                    String str = uploadinfo.status;
                    if (Objects.equals(str, "Cancelled") || str.equals("Confirmed") || str.equals("Payed")) {
                        decfragment.CANCEL.setVisibility(View.GONE);
                    }
                    if (!str.equals("Not confirmed")) {
                        decfragment.CANCEL.setVisibility(View.GONE);
                    }
                }
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Picasso.get().load(this.imageurl).into((ImageView) inflate.findViewById(R.id.imagegholder));
        CANCEL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogInterface.OnClickListener onClickListener = null;
                final AlertDialog show = new AlertDialog.Builder(decfragment.this.getActivity()).setTitle((CharSequence) "CANCEL").setMessage((CharSequence) "Do you want cancel this order").setPositiveButton((CharSequence) "Ok", onClickListener).setNegativeButton((CharSequence) "Cancel", onClickListener).show();
                show.getButton(-1).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(NotificationCompat.CATEGORY_STATUS, "Cancelled");
                        decfragment.reference.updateChildren(hashMap);
                        textView.setText(decfragment.this.potrait);
                        textView4.setText(decfragment.this.address);
                        textView5.setText(decfragment.this.Phonenum);
                        textView2.setText(decfragment.this.status);
                        textView3.setText(decfragment.this.date);
                        show.dismiss();
                    }
                });
                show.getButton(-2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        show.dismiss();
                    }
                });
            }
        });
        return inflate;
    }
    public void onBackPressed() {
        Context context = getContext();
        Objects.requireNonNull(context);
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).addToBackStack(null).commit();
    }
}
