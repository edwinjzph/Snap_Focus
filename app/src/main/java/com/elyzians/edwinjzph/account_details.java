package com.elyzians.edwinjzph;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class account_details extends AppCompatActivity {
    TextView BOOK;
    TextView ORDER;
    ShimmerFrameLayout layout6;
    ShimmerFrameLayout layout7;
    myadapter2 myadapter2;
    TextView notverified;
    ProgressBar progressBar;
    RecyclerView recview;
    private DatabaseReference reference;
    private TextView signout;
    private FirebaseUser user;
    private String userid;
    ImageView verified;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_account_details);
        this.notverified = (TextView) findViewById(R.id.notverifed);
        this.verified = (ImageView) findViewById(R.id.checktik);
        this.progressBar = (ProgressBar) findViewById(R.id.verificationpro);
        this.layout6 = (ShimmerFrameLayout) findViewById(R.id.shimmer6);
        this.layout7 = (ShimmerFrameLayout) findViewById(R.id.shimmer8);
        DatabaseReference child = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        this.reference = child;
        child.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("OrderDetails")) {
                    account_details.this.layout6.stopShimmer();
                    account_details.this.layout6.hideShimmer();
                    account_details.this.layout6.setVisibility(View.GONE);
                    account_details.this.ORDER.setVisibility(View.VISIBLE);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                account_details.this.ORDER.setVisibility(View.GONE);
            }
        });
        child.addValueEventListener(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("OrderDetails")) {
                    account_details.this.layout6.stopShimmer();
                    account_details.this.layout6.hideShimmer();
                    account_details.this.layout6.setVisibility(View.GONE);
                    account_details.this.recview.setVisibility(View.VISIBLE);
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        toolbar.setTitle((CharSequence) "My Account");
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                account_details.this.startActivity(new Intent(account_details.this, MainActivity.class));
            }
        });
        this.signout = (TextView) findViewById(R.id.button2);
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.signout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                account_details.this.finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(new Intent(account_details.this, loginactivity2.class));
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                account_details.this.startActivity(intent);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview2);
        this.recview = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter2 myadapter22 = new myadapter2(new FirebaseRecyclerOptions.Builder().setQuery((Query) FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderDetails"), uploadinfo.class).build());
        this.myadapter2 = myadapter22;
        this.recview.setAdapter(myadapter22);
        this.ORDER = (TextView) findViewById(R.id.noorderr);
        this.user = FirebaseAuth.getInstance().getCurrentUser();
        this.reference = FirebaseDatabase.getInstance().getReference("Users");
        this.userid = this.user.getUid();
        final TextView textView = (TextView) findViewById(R.id.textView3);
        final TextView textView2 = (TextView) findViewById(R.id.textView7);
        this.reference.child(this.userid).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                user user = (user) dataSnapshot.getValue(user.class);
                if (user != null) {
                    String str = user.Username;
                    String str2 = user.Email;
                    account_details.this.layout7.stopShimmer();
                    account_details.this.layout7.hideShimmer();
                    account_details.this.layout7.setVisibility(View.GONE);
                    if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                        account_details.this.verified.setVisibility(View.VISIBLE);
                    } else {
                        account_details.this.notverified.setVisibility(View.VISIBLE);
                    }
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView.setText(str);
                    textView2.setText(str2);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(account_details.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
            }
        });
        TextView textView3 = (TextView) findViewById(R.id.book);
        this.BOOK = textView3;
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                account_details.this.startActivity(new Intent(new Intent(account_details.this, retrive_address_credentials.class)));
            }
        });
        this.notverified.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                account_details.this.progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(account_details.this, "Check your mail for verification", Toast.LENGTH_SHORT).show();
                            account_details.this.progressBar.setVisibility(View.GONE);
                            account_details.this.notverified.setVisibility(View.GONE);
                            return;
                        }
                        Toast.makeText(account_details.this, "Some wrong happened", Toast.LENGTH_SHORT).show();
                        account_details.this.progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.drawable.ic_baseline_arrow_back_24) {
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onStart() {
        super.onStart();
        this.myadapter2.startListening();
    }

    public void onStop() {
        super.onStop();
        this.myadapter2.stopListening();
    }


}
