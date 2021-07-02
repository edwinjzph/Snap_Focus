package com.elyzians.edwinjzph;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {
    TextView aboutus;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.aboutus2);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle((CharSequence) "");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity3.this.finish();
                MainActivity3.this.startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.aboutus = (TextView) findViewById(R.id.aboutus);
        FirebaseDatabase.getInstance().getReference().child("Aboutus").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onCancelled(DatabaseError databaseError) {
            }

            public void onDataChange(DataSnapshot dataSnapshot) {
                about about = (about) dataSnapshot.getValue(about.class);
                if (about != null) {
                    MainActivity3.this.aboutus.setText(about.about);
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
