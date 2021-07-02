package com.elyzians.edwinjzph;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent intent = new Intent(splash.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    splash.this.startActivity(intent);
                } else {
                    splash.this.startActivity(new Intent(splash.this, loginactivity2.class));
                }
                splash.this.finish();
            }
        }, (long) SPLASH_TIME_OUT);
    }
}
