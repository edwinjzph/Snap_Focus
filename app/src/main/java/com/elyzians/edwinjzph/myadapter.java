package com.elyzians.edwinjzph;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class myadapter extends FirebaseRecyclerAdapter<uploadinfo, myadapter.myviewholder> {
    public myadapter(FirebaseRecyclerOptions<uploadinfo> firebaseRecyclerOptions) {
        super(firebaseRecyclerOptions);
    }
@Override
    public void onBindViewHolder(myviewholder myviewholder2, final int i, final uploadinfo uploadinfo) {
        myviewholder2.nametext.setText(uploadinfo.Potrait);
        myviewholder2.status.setText(uploadinfo.status);
        final DatabaseReference child = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderDetails").child(getRef(i).getKey());
        Picasso.get().load(uploadinfo.imageURL).fit().centerCrop().placeholder((int) R.drawable.h).error((int) R.drawable.h).into(myviewholder2.img1);
        myviewholder2.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new decfragment(uploadinfo.Address, uploadinfo.Date, uploadinfo.Potrait, uploadinfo.Phonenumber, uploadinfo.imageName, uploadinfo.imageURL, uploadinfo.status, i, child)).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int i) {
        return new myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false));
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        public final ImageView img1;
        TextView nametext;
        TextView status;

        public myviewholder(View view) {
            super(view);
            this.img1 = (ImageView) view.findViewById(R.id.img1);
            this.nametext = (TextView) view.findViewById(R.id.nametext);
            this.status = (TextView) view.findViewById(R.id.coursetext);
        }
    }
}
