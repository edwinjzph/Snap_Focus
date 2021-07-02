package com.elyzians.edwinjzph;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).commit();
    }
}
