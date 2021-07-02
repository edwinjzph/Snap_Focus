package com.elyzians.edwinjzph;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class loginactivity2 extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Button button2;
    private TextInputEditText confirmpassword;
    boolean doubleBackToExitPressedOnce = false;
    private TextInputEditText emailaddress;
    private TextView login;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    String nou;
    String nou2;
    private TextInputEditText password;
    Button privacypolicy4;
    public ProgressBar prograssbar;
    private Button registeruser;
    private TextInputEditText username;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_loginactivity2);
        mAuth = FirebaseAuth.getInstance();
        this.button = (Button) findViewById(R.id.button);
        this.button2 = (Button) findViewById(R.id.button8);
        this.registeruser = this.button;
        this.privacypolicy4 = (Button) findViewById(R.id.privacypolicy4);
        this.button.setOnClickListener(this);
        this.username = (TextInputEditText) findViewById(R.id.editText);
        this.emailaddress = (TextInputEditText) findViewById(R.id.editText2);
        this.password = (TextInputEditText) findViewById(R.id.editText3);
        this.confirmpassword = (TextInputEditText) findViewById(R.id.editText4);
        this.prograssbar = (ProgressBar) findViewById(R.id.progressBar);
        Button button3 = (Button) findViewById(R.id.ww);
        this.privacypolicy4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                loginactivity2.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://snap-focus-productio.flycricket.io/privacy.html")));
            }
        });
        this.button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                loginactivity2.this.startActivity(new Intent(loginactivity2.this, MainActivity.class));
            }
        });
        this.login = button3;
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                loginactivity2.this.startActivity(new Intent(loginactivity2.this, Login_activity.class));
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            registeruser();
        }
    }

    private void registeruser() {
        final String trim = emailaddress.getText().toString().trim();
        final String trim2 = password.getText().toString().trim();
         final String trim3 = confirmpassword.getText().toString().trim();
        final String trim4 = username.getText().toString().trim();
        if (trim4.isEmpty()) {
            this.username.setError("Username is required");
            this.username.requestFocus();
        } else if (trim.isEmpty()) {
            this.emailaddress.setError("Email is required");
            this.emailaddress.requestFocus();
        } else if (trim2.isEmpty()) {
            this.password.setError("Password is required");
            this.password.requestFocus();
        } else if (trim3.isEmpty()) {
            this.confirmpassword.setError("Password is required");
            this.confirmpassword.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trim).matches()) {
            this.emailaddress.setError("Please provide an valid email ");
            this.emailaddress.requestFocus();
        } else if (trim2.length() < 6) {
            this.password.setError("Min password length should be six characters!");
            this.password.requestFocus();
        } else if (!trim2.equals(trim3)) {
            this.confirmpassword.setError("Passwords should be equal");
            this.confirmpassword.requestFocus();
        } else {
            this.nou = "Digital portraits for just 1500";
            this.nou2 = "We will let you know the rate and time required for the drawing after seeing your reference picture";
            this.button.setVisibility(View.GONE);
            this.prograssbar.setVisibility(View.VISIBLE);
            this.mAuth.createUserWithEmailAndPassword(emailaddress.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        Objects.requireNonNull(currentUser);
                        reference.child(currentUser.getUid()).setValue(new user(username.getText().toString().trim(), emailaddress.getText().toString().trim(), loginactivity2.this.nou, loginactivity2.this.nou2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = loginactivity2.this.mAuth.getCurrentUser();
                                    Objects.requireNonNull(currentUser);
                                    currentUser.sendEmailVerification();
                                    loginactivity2.this.mAuth.signInWithEmailAndPassword(emailaddress.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        public void onComplete(Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                loginactivity2.this.startActivity(new Intent(loginactivity2.this, MainActivity.class));
                                                loginactivity2.this.button.setVisibility(View.VISIBLE);
                                                loginactivity2.this.prograssbar.setVisibility(View.GONE);
                                                return;
                                            }
                                            loginactivity2.this.startActivity(new Intent(loginactivity2.this, Login_activity.class));
                                            Toast.makeText(loginactivity2.this, "User has been successfully registered!,please check email for verification", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    return;
                                }
                                Toast.makeText(loginactivity2.this, "Failed to register!,Try again!", Toast.LENGTH_LONG).show();
                                loginactivity2.this.button.setVisibility(View.VISIBLE);
                                loginactivity2.this.prograssbar.setVisibility(View.GONE);
                            }
                        });
                        return;
                    }
                    Toast.makeText(loginactivity2.this, "Failed to register!,Try again!", Toast.LENGTH_LONG).show();
                    loginactivity2.this.button.setVisibility(View.VISIBLE);
                    loginactivity2.this.prograssbar.setVisibility(View.GONE);
                }
            });
        }
    }

    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                loginactivity2.this.doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
