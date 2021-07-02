package com.elyzians.edwinjzph;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class Login_activity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Button button2;
    private TextInputEditText editemail;
    TextInputEditText editpassword;
    private TextView forgotpassword;
    private FirebaseAuth mAuth;
    public ProgressBar prograssbar;
    private TextView register;
    Button textView10;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login_activity);
        this.textView10 = (Button) findViewById(R.id.privacypolicy2);
        this.button2 = (Button) findViewById(R.id.textView500);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar43);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle((CharSequence) "");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login_activity.super.onBackPressed();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button3 = (Button) findViewById(R.id.textview211);
        this.register = button3;
        button3.setOnClickListener(this);
        this.button = (Button) findViewById(R.id.button3);
        this.textView10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login_activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://snap-focus-productio.flycricket.io/privacy.html")));
            }
        });
        this.button.setOnClickListener(this);
        this.editemail = (TextInputEditText) findViewById(R.id.editText5);
        this.editpassword = (TextInputEditText) findViewById(R.id.editText11);
        this.prograssbar = (ProgressBar) findViewById(R.id.progressBar2);
        this.mAuth = FirebaseAuth.getInstance();
        Button button4 = (Button) findViewById(R.id.textView18);
        this.forgotpassword = button4;
        button4.setOnClickListener(this);
        this.button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Login_activity.this.invalidateOptionsMenu();
                Login_activity.this.startActivity(new Intent(Login_activity.this, MainActivity.class));
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button3) {
            userlogin();
        } else if (id == R.id.textView18) {
            startActivity(new Intent(this, forgot_password.class));
            this.prograssbar.setVisibility(View.GONE);
        } else if (id == R.id.textview211) {
            startActivity(new Intent(this, loginactivity2.class));
            this.prograssbar.setVisibility(View.GONE);
        }
    }

    private void userlogin() {
        Editable text = this.editemail.getText();
        Objects.requireNonNull(text);
        String trim = text.toString().trim();
        Editable text2 = this.editpassword.getText();
        Objects.requireNonNull(text2);
        String trim2 = text2.toString().trim();
        if (trim.isEmpty()) {
            this.editemail.setError("Email is Required");
            this.editemail.requestFocus();
        } else if (trim2.isEmpty()) {
            this.editpassword.setError("password is Required");
            this.editpassword.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trim).matches()) {
            this.editemail.setError("Please provide a valid email");
            this.editemail.requestFocus();
        } else if (trim2.length() < 6) {
            this.editpassword.setError("Please provide a valid password");
            this.editpassword.requestFocus();
        } else {
            this.button.setVisibility(View.GONE);
            this.prograssbar.setVisibility(View.VISIBLE);
            this.mAuth.signInWithEmailAndPassword(trim, trim2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Login_activity.this.startActivity(new Intent(Login_activity.this, MainActivity.class));
                        Login_activity.this.button.setVisibility(View.VISIBLE);
                        Login_activity.this.prograssbar.setVisibility(View.GONE);
                        Login_activity.this.button.setVisibility(View.VISIBLE);
                        return;
                    }
                    Toast.makeText(Login_activity.this, "Failed to login!,please check your credentials", Toast.LENGTH_LONG).show();
                    Login_activity.this.button.setVisibility(View.VISIBLE);
                    Login_activity.this.prograssbar.setVisibility(View.GONE);
                }
            });
        }
    }
}
