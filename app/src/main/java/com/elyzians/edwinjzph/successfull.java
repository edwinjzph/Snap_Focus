package com.elyzians.edwinjzph;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class successfull extends AppCompatActivity {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_successfull);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        toolbar.setTitle((CharSequence) "Successfully ordered");
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                successfull.this.startActivity(new Intent(successfull.this, MainActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button textView =  findViewById(R.id.elvin1);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                successfull.this.finish();
                successfull.this.startActivity(new Intent(successfull.this, MainActivity.class));
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.drawable.ic_baseline_arrow_back_24) {
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onContextItemSelected(menuItem);
    }
}
