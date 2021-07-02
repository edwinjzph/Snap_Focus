package com.elyzians.edwinjzph;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class forgot_password extends AppCompatActivity {
    FirebaseAuth auth;
    /* access modifiers changed from: private */
    public Button button3;
    private TextInputEditText email;
    private TextView login;
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public Button reset;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_forgot_password);
        this.email = (TextInputEditText) findViewById(R.id.editText6);
        this.button3 = (Button) findViewById(R.id.forgot);
        this.reset = (Button) findViewById(R.id.button4);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        this.auth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar33);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle((CharSequence) "");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                forgot_password.this.finish();
                forgot_password.super.onBackPressed();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                forgot_password.this.resetpassword();
            }
        });
        this.button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.APP_EMAIL");
                    forgot_password.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(forgot_password.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void resetpassword() {
        String trim = this.email.getText().toString().trim();
        if (trim.isEmpty()) {
            this.email.setError("Email is required!");
            this.email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trim).matches()) {
            this.email.setError("Please provide an valid email");
            this.email.requestFocus();
        } else {
            this.reset.setVisibility(View.GONE);
            this.progressBar.setVisibility(View.VISIBLE);
            this.auth.sendPasswordResetEmail(trim).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(forgot_password.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                        forgot_password.this.button3.setVisibility(View.VISIBLE);
                        forgot_password.this.reset.setVisibility(View.VISIBLE);
                        forgot_password.this.progressBar.setVisibility(View.GONE);
                        return;
                    }
                    Toast.makeText(forgot_password.this, "Try Again!,Something Wrong Happened", Toast.LENGTH_LONG).show();
                    forgot_password.this.reset.setVisibility(View.VISIBLE);
                    forgot_password.this.progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}
