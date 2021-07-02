package com.elyzians.edwinjzph;

import android.app.Application;

import android.content.Intent;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class myadapter3 extends FirebaseRecyclerAdapter<post, myadapter3.myviewholder> {

    /* renamed from: HG */
    public Application f130HG;


    public myadapter3(FirebaseRecyclerOptions firebaseRecyclerOptions, Application application) {
        super(firebaseRecyclerOptions);
        this.f130HG = application;
    }

    public void onBindViewHolder(final myviewholder myviewholder2, int i, final post post) {
        myviewholder2.nametext.setText(post.discription);
        if (post.type.equals("1")) {

            myviewholder2.youtube.setVisibility(View.GONE);
            myviewholder2.mainslider.setVisibility(View.GONE);
            myviewholder2.imageSlider.setVisibility(View.GONE);
            myviewholder2.imageView2.setVisibility(View.GONE);
            Picasso.get().load(post.url).fit().centerInside().placeholder((int) R.drawable.h).error((int) R.drawable.h).into(myviewholder2.img1);
        }

        if (post.type.equals("3")) {
            myviewholder2.img1.setVisibility(View.GONE);
            myviewholder2.mainslider.setVisibility(View.GONE);
            myviewholder2.imageSlider.setVisibility(View.GONE);
            myviewholder2.youtube.setVisibility(View.GONE);
            Picasso.get().load(post.url).fit().centerInside().placeholder((int) R.drawable.h).error((int) R.drawable.h).into(myviewholder2.imageView2);
        }
        if (post.type.equals("4")) {
            myviewholder2.img1.setVisibility(View.GONE);
            myviewholder2.imageView2.setVisibility(View.GONE);
            myviewholder2.imageSlider.setVisibility(View.GONE);
            FirebaseDatabase.getInstance().getReference().child("recycleview").child(getRef(i).getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                public void onDataChange(DataSnapshot dataSnapshot) {
                    post post = (post) dataSnapshot.getValue(post.class);
                    if (post != null && post.youtubeurl.equals("null")) {
                        myviewholder2.youtube.setVisibility(View.GONE);
                    }
                }
            });
            final ArrayList arrayList = new ArrayList();
            FirebaseDatabase.getInstance().getReference().child("recycleview").child(getRef(i).getKey()).child("slider").addListenerForSingleValueEvent(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        arrayList.add(new SlideModel(child.child(ImagesContract.URL).getValue().toString(), ScaleTypes.CENTER_CROP));
                    }
                    myviewholder2.mainslider.setImageList(arrayList, ScaleTypes.CENTER_CROP);
                }
            });
        }
        if (post.type.equals("5")) {
            myviewholder2.img1.setVisibility(View.GONE);
            myviewholder2.imageView2.setVisibility(View.GONE);
            myviewholder2.mainslider.setVisibility(View.GONE);
            myviewholder2.youtube.setVisibility(View.GONE);
            final ArrayList arrayList2 = new ArrayList();
            FirebaseDatabase.getInstance().getReference().child("recycleview").child(getRef(i).getKey()).child("slider").addListenerForSingleValueEvent(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        arrayList2.add(new SlideModel(child.child(ImagesContract.URL).getValue().toString(), ScaleTypes.CENTER_CROP));
                    }
                    myviewholder2.imageSlider.setImageList(arrayList2, ScaleTypes.CENTER_CROP);
                }
            });
        }
        myviewholder2.youtube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse(post.youtubeurl));
                view.getContext().startActivity(intent);
            }
        });
    }

    public myviewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new myviewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false));
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageSlider imageSlider;
        ImageView imageView2;
        ImageView img1;
        ImageSlider mainslider;
        TextView nametext;
        Button youtube;

        public myviewholder(View view) {
            super(view);
            this.img1 = (ImageView) view.findViewById(R.id.postphoto);
            this.imageView2 = (ImageView) view.findViewById(R.id.postphoto3);
            this.youtube = (Button) view.findViewById(R.id.watchonyoutube);
            this.mainslider = (ImageSlider) view.findViewById(R.id.image_slider2);
            this.imageSlider = (ImageSlider) view.findViewById(R.id.image_slider3);
            this.nametext = (TextView) view.findViewById(R.id.postdis);
        }



    }
}
