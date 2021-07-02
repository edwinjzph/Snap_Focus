package com.elyzians.edwinjzph;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity4 extends AppCompatActivity {

    /* renamed from: a */
    String f92a;
    ArrayList<String> arrayList;

    /* renamed from: b */
    String f93b;
    Button button;

    /* renamed from: c */
    String f94c;
    MaterialCardView cardView;
    MaterialCardView cardView2;
    MaterialCardView cardView3;
    CoordinatorLayout coordinatorLayout;
    DatabaseReference databaseReference;
    TextInputLayout layout;
    AutoCompleteTextView potrait;
    ProgressBar progressBar;
    ArrayAdapter<String> stringArrayAdapter;
    AutoCompleteTextView textInputEditText1;
    TextInputEditText textInputEditText2;
    TextInputEditText textInputEditText3;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.contact);
        this.layout = (TextInputLayout) findViewById(R.id.name_text_input201);
        this.databaseReference = FirebaseDatabase.getInstance().getReference("requests");
        this.textInputEditText1 = (AutoCompleteTextView) findViewById(R.id.ourservices);
        this.cardView = (MaterialCardView) findViewById(R.id.elvin100);
        this.cardView2 = (MaterialCardView) findViewById(R.id.elvin101);
        this.cardView3 = (MaterialCardView) findViewById(R.id.elvin102);
        this.coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        this.progressBar = (ProgressBar) findViewById(R.id.prograssrequest);
        this.textInputEditText2 = (TextInputEditText) findViewById(R.id.ourservices2);
        this.textInputEditText3 = (TextInputEditText) findViewById(R.id.ourservices3);
        this.button = (Button) findViewById(R.id.ourservices5);
        this.potrait = (AutoCompleteTextView) findViewById(R.id.ourservices);
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.arrayList = arrayList2;
        arrayList2.add("Cinematography");
        this.arrayList.add("Spot Editing");
        this.arrayList.add("Makeup");
        this.arrayList.add("Still Photography");
        this.arrayList.add("Light Unit");
        this.arrayList.add("Camera Unit");
        this.arrayList.add("Sync Sound");
        this.arrayList.add("Editing");
        this.arrayList.add("Visual EX");
        this.arrayList.add("DI Colouring");
        this.arrayList.add("Music");
        this.arrayList.add("Original Score");
        this.arrayList.add("Animation");
        this.arrayList.add("Sound Design and Dubbing");
        this.arrayList.add("Title Design");
        this.arrayList.add("Poster Design");
        this.arrayList.add("Poster Ads");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.drop, this.arrayList);
        this.stringArrayAdapter = arrayAdapter;
        this.potrait.setAdapter(arrayAdapter);
        this.potrait.setThreshold(1);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle((CharSequence) "Contact us");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity4.this.finish();
                MainActivity4.super.onBackPressed();
            }
        });
        this.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity4.this.request();
            }
        });
    }

    public void request() {
        this.f92a = this.textInputEditText1.getText().toString().trim();
        Editable text = this.textInputEditText2.getText();
        Objects.requireNonNull(text);
        this.f93b = text.toString().trim();
        Editable text2 = this.textInputEditText3.getText();
        Objects.requireNonNull(text2);
        this.f94c = text2.toString().trim();
        if (this.f92a.isEmpty()) {
            this.textInputEditText1.setError("Required");
            this.textInputEditText1.requestFocus();
        } else if (this.f93b.isEmpty()) {
            this.textInputEditText2.setError("Required");
            this.textInputEditText2.requestFocus();
        } else if (this.f94c.isEmpty()) {
            this.textInputEditText3.setError("Required");
            this.textInputEditText3.requestFocus();
        } else {
            this.progressBar.setVisibility(View.VISIBLE);
            this.button.setVisibility(View.GONE);
            this.databaseReference.push().setValue(new userrequest(this.f92a, this.f93b, this.f94c)).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        MainActivity4.this.progressBar.setVisibility(View.GONE);
                        MainActivity4.this.cardView.setVisibility(View.VISIBLE);
                        MainActivity4.this.cardView2.setVisibility(View.GONE);
                        MainActivity4.this.cardView3.setVisibility(View.GONE);
                        Snackbar.make((View) MainActivity4.this.coordinatorLayout, (CharSequence) "Thank you, We will contact you asap", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    MainActivity4.this.progressBar.setVisibility(View.GONE);
                    MainActivity4.this.button.setVisibility(View.VISIBLE);
                    Snackbar.make((View) MainActivity4.this.coordinatorLayout, (CharSequence) "Some thing wrong happened", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}
