package com.elyzians.edwinjzph;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class myadapter2 extends FirebaseRecyclerAdapter<uploadinfo, myadapter2.myviewholder> {
    public myadapter2(FirebaseRecyclerOptions<uploadinfo> firebaseRecyclerOptions) {
        super(firebaseRecyclerOptions);
    }

    public void onBindViewHolder(myviewholder myviewholder2, int i, uploadinfo uploadinfo) {
        myviewholder2.nametext.setText(uploadinfo.Potrait);
        myviewholder2.status.setText(uploadinfo.status);
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderDetails").child(getRef(i).getKey());
        Picasso.get().load(uploadinfo.imageURL).fit().centerCrop().placeholder((int) R.drawable.h).error((int) R.drawable.h).into(myviewholder2.img1);
    }

    public myviewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row2, viewGroup, false));
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
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
