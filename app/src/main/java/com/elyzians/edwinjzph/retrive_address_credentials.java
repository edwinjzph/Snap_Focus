package com.elyzians.edwinjzph;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class retrive_address_credentials extends AppCompatActivity {
    public static String valuefinal;
    public TextView DATE;

    /* renamed from: DD */
    TextInputEditText f131DD;
    TextView EDS;
    Uri FilePathUri;

    /* renamed from: GG */
    String f132GG;
    int Image_Request_Code = 7;

    /* renamed from: MM */
    Switch f133MM;
    public Button Send;

    /* renamed from: UH */
    TextInputEditText f134UH;
    TextInputLayout UUUU;
    public EditText address;
    ArrayList<String> arrayList;
    Button btnbrowse;
    /* access modifiers changed from: private */
    public Button button;
    final Context context = this;
    DatabaseReference databaseReference;
    public Button date;
    SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    TextInputLayout eeee;
    ImageView imgview;
    TextInputLayout layout;
    AutoCompleteTextView potrait;
    ProgressDialog progressDialog;
    /* access modifiers changed from: private */
    public TextView result;
    StorageReference storageReference;
    ArrayAdapter<String> stringArrayAdapter;
    SwitchMaterial switchMaterial;
    TextInputEditText txtdata;
    TextInputEditText userInput;
    TextInputEditText userInput2;
    TextInputEditText userInput3;
    TextInputEditText userInput4;
    TextInputEditText userInput5;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.order);
        this.EDS = (TextView) findViewById(R.id.edws);
        this.f131DD = (TextInputEditText) findViewById(R.id.edwinoo);
        this.button = (Button) findViewById(R.id.buttonPrompt);
        this.result = (TextView) findViewById(R.id.editTextResult);
        this.DATE = (TextView) findViewById(R.id.date);
        this.UUUU = (TextInputLayout) findViewById(R.id.name_text_input61);
        this.eeee = (TextInputLayout) findViewById(R.id.name_text_input62);
        this.f134UH = (TextInputEditText) findViewById(R.id.elnayoo);
        SwitchMaterial switchMaterial2 = (SwitchMaterial) findViewById(R.id.ggg);
        this.switchMaterial = switchMaterial2;
        switchMaterial2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (retrive_address_credentials.this.switchMaterial.isChecked()) {
                    EDS.setVisibility(View.GONE);
                    retrive_address_credentials.this.UUUU.setVisibility(View.VISIBLE);
                    retrive_address_credentials.this.eeee.setVisibility(View.VISIBLE);
                    retrive_address_credentials.this.button.setVisibility(View.GONE);
                    result.setText("");
                    retrive_address_credentials.this.result.setVisibility(View.GONE);
                    return;
                }
                retrive_address_credentials.this.UUUU.setVisibility(View.GONE);
                retrive_address_credentials.this.eeee.setVisibility(View.GONE);
                retrive_address_credentials.this.button.setVisibility(View.VISIBLE);
                retrive_address_credentials.this.result.setVisibility(View.VISIBLE);
            }
        });
        this.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View inflate = LayoutInflater.from(retrive_address_credentials.this.context).inflate(R.layout.dialog, (ViewGroup) null);
                AlertDialog.Builder builder = new AlertDialog.Builder(retrive_address_credentials.this.context);
                builder.setView(inflate);
                retrive_address_credentials.this.userInput = (TextInputEditText) inflate.findViewById(R.id.editTextDialogUserInput);
                retrive_address_credentials.this.userInput2 = (TextInputEditText) inflate.findViewById(R.id.ads1);
                retrive_address_credentials.this.userInput3 = (TextInputEditText) inflate.findViewById(R.id.ads2);
                retrive_address_credentials.this.userInput4 = (TextInputEditText) inflate.findViewById(R.id.ads3);
                retrive_address_credentials.this.userInput5 = (TextInputEditText) inflate.findViewById(R.id.ads4);
                builder.setCancelable(false).setPositiveButton((CharSequence) "Add address", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog create = builder.create();
                create.show();
                create.getButton(-1).setOnClickListener(new CustomListener(create));
            }
        });
        this.layout = (TextInputLayout) findViewById(R.id.name_text_input41);
        this.potrait = (AutoCompleteTextView) findViewById(R.id.elna);
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.arrayList = arrayList2;
        arrayList2.add("Normal portrait");
        this.arrayList.add("Portrait watercolour");
        this.arrayList.add("scribble art");
        this.arrayList.add("Digital art");
        this.arrayList.add("Digital concepted art");
        this.arrayList.add("Creative ideas");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.drop, this.arrayList);
        this.stringArrayAdapter = arrayAdapter;
        this.potrait.setAdapter(arrayAdapter);
        this.potrait.setThreshold(1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle((CharSequence) "Enter order details");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                retrive_address_credentials.this.dialog();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String stringExtra = getIntent().getStringExtra("portrait");
        String stringExtra2 = getIntent().getStringExtra("water");
        String stringExtra3 = getIntent().getStringExtra("scribble");
        String stringExtra4 = getIntent().getStringExtra("digital");
        String stringExtra5 = getIntent().getStringExtra("digitalcon");
        String stringExtra6 = getIntent().getStringExtra("creative");
        this.storageReference = FirebaseStorage.getInstance().getReference("Images");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Objects.requireNonNull(currentUser);
        this.databaseReference = reference.child(currentUser.getUid()).child("OrderDetails");
        this.btnbrowse = (Button) findViewById(R.id.btnbrowse);
        this.txtdata = (TextInputEditText) findViewById(R.id.txtdata);

        this.imgview = (ImageView) findViewById(R.id.image_view);
        this.progressDialog = new ProgressDialog(this);
        this.btnbrowse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                retrive_address_credentials.this.startActivityForResult(Intent.createChooser(intent, "Select Image"), retrive_address_credentials.this.Image_Request_Code);
            }
        });
        this.date = (Button) findViewById(R.id.elvin);
        this.Send = (Button) findViewById(R.id.button6);
        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        this.date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                retrive_address_credentials.this.showDateDialog();
            }
        });
        if ("Normal portrait".equals(stringExtra)) {
            this.potrait.setText(stringExtra);
        }
        if ("Portrait watercolour".equals(stringExtra2)) {
            this.potrait.setText(stringExtra2);
        }
        if ("Scribble art".equals(stringExtra3)) {
            this.potrait.setText(stringExtra3);
        }
        if ("Digital art".equals(stringExtra4)) {
            this.potrait.setText(stringExtra4);
        }
        if ("Digital concepted art".equals(stringExtra5)) {
            this.potrait.setText(stringExtra5);
        }
        if ("Creative ideas".equals(stringExtra6)) {
            this.potrait.setText(stringExtra6);
        }
        this.imgview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                retrive_address_credentials.this.startActivityForResult(Intent.createChooser(intent, "Select Image"), retrive_address_credentials.this.Image_Request_Code);
            }
        });
        this.Send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (retrive_address_credentials.this.result.getText().toString().trim().isEmpty() && !retrive_address_credentials.this.switchMaterial.isChecked()) {
                    retrive_address_credentials.this.result.setError("Address is required");
                    retrive_address_credentials.this.result.requestFocus();
                } else if (retrive_address_credentials.this.switchMaterial.isChecked() && retrive_address_credentials.this.f134UH.getText().toString().trim().isEmpty()) {
                    retrive_address_credentials.this.f134UH.setError(" E-mail is required");
                    retrive_address_credentials.this.f134UH.requestFocus();
                } else if (retrive_address_credentials.this.switchMaterial.isChecked() && !Patterns.EMAIL_ADDRESS.matcher(retrive_address_credentials.this.f134UH.getText().toString()).matches()) {
                    retrive_address_credentials.this.f134UH.setError("Please provide a valid email");
                    retrive_address_credentials.this.f134UH.requestFocus();
                } else if (retrive_address_credentials.this.switchMaterial.isChecked() && retrive_address_credentials.this.f131DD.getText().toString().trim().isEmpty()) {
                    retrive_address_credentials.this.f131DD.setError(" Phone Number is required");
                    retrive_address_credentials.this.f131DD.requestFocus();
                } else if (retrive_address_credentials.this.potrait.getText().toString().trim().isEmpty()) {
                    retrive_address_credentials.this.potrait.setError(" Required");
                    retrive_address_credentials.this.potrait.requestFocus();
                } else if (retrive_address_credentials.this.DATE.getText().toString().trim().equals("MM-DD-YYYY")) {
                    retrive_address_credentials.this.date.setError("Date is required");
                    retrive_address_credentials.this.date.requestFocus();
                } else {
                    if (retrive_address_credentials.this.switchMaterial.isChecked()) {
                        retrive_address_credentials retrive_address_credentials = retrive_address_credentials.this;
                        retrive_address_credentials.f132GG = retrive_address_credentials.f131DD.getText().toString().trim();
                        retrive_address_credentials.valuefinal = retrive_address_credentials.this.f134UH.getText().toString().trim();
                    } else {
                        retrive_address_credentials.valuefinal = retrive_address_credentials.this.result.getText().toString().trim();
                        retrive_address_credentials retrive_address_credentials2 = retrive_address_credentials.this;
                        retrive_address_credentials2.f132GG = retrive_address_credentials2.userInput.getText().toString().trim();
                    }
                    retrive_address_credentials.this.UploadImage();
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.drawable.ic_baseline_arrow_back_24) {
            dialog();
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.Image_Request_Code && i2 == -1 && intent != null && intent.getData() != null) {
            this.FilePathUri = intent.getData();
            try {
                this.imgview.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), this.FilePathUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }

    public void UploadImage() {
        if (this.FilePathUri != null) {
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.setIcon(R.drawable.sf);
            this.progressDialog.setTitle("Uploading files..please wait");
            this.progressDialog.show();
            StorageReference storageReference2 = this.storageReference;
            final StorageReference child = storageReference2.child(System.currentTimeMillis() + "." + GetFileExtension(this.FilePathUri));
            child.putFile(this.FilePathUri).addOnSuccessListener((OnSuccessListener<? super UploadTask.TaskSnapshot>) new OnSuccessListener<UploadTask.TaskSnapshot>() {
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    child.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        public void onSuccess(Uri uri) {
                            String uri2 = uri.toString();
                            DatabaseReference push = retrive_address_credentials.this.databaseReference.push();
                            Editable text = retrive_address_credentials.this.txtdata.getText();
                            Objects.requireNonNull(text);
                            push.setValue(new uploadinfo(text.toString().trim(), uri2, retrive_address_credentials.valuefinal, retrive_address_credentials.this.DATE.getText().toString().trim(), retrive_address_credentials.this.potrait.getText().toString().trim(), retrive_address_credentials.this.f132GG, "Not confirmed", "", "")).addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        retrive_address_credentials.this.finish();
                                        retrive_address_credentials.this.progressDialog.dismiss();
                                        Toast.makeText(retrive_address_credentials.this.getApplicationContext(), "Your order has been successfully ordered", Toast.LENGTH_SHORT).show();
                                        retrive_address_credentials.this.startActivity(new Intent(retrive_address_credentials.this, successfull.class));
                                        return;
                                    }
                                    retrive_address_credentials.this.progressDialog.dismiss();
                                    Toast.makeText(retrive_address_credentials.this, "Something Wrong Happened,Try again", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });
            return;
        }
        Toast.makeText(this, "Please Select Image or Add Image Name", Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed() {
        dialog();
    }

    public void dialog() {
        DialogInterface.OnClickListener onClickListener = null;
        final AlertDialog show = new AlertDialog.Builder(this).setTitle((CharSequence) "Cancel").setMessage((CharSequence) "Do you want cancel this order?").setPositiveButton((CharSequence) "Ok", onClickListener).setNegativeButton((CharSequence) "Cancel", onClickListener).show();
        show.getButton(-1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                retrive_address_credentials.this.startActivity(new Intent(retrive_address_credentials.this, MainActivity.class));
            }
        });
        show.getButton(-2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                show.dismiss();
            }
        });
    }

    public void showDateDialog() {
        Calendar instance = Calendar.getInstance();
        final DatePickerDialog datePickerDialog2 = new DatePickerDialog(this,  R.style.my_dialog_theme,new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar instance = Calendar.getInstance();
                instance.set(i, i2, i3);
                retrive_address_credentials.this.DATE.setText(retrive_address_credentials.this.dateFormatter.format(instance.getTime()));
            }
        }, instance.get(1), instance.get(2), instance.get(5));
        this.datePickerDialog = datePickerDialog2;
        datePickerDialog2.getDatePicker().setMinDate(System.currentTimeMillis() + 7000);
        datePickerDialog2.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                datePickerDialog2.getButton(-2).setTextColor(Color.parseColor("#FF000000"));
                datePickerDialog2.getButton(-1).setTextColor(Color.parseColor("#FF000000"));
            }
        });
        datePickerDialog2.show();
    }

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;

        public CustomListener(Dialog dialog2) {
            this.dialog = dialog2;
        }

        public void onClick(View view) {
            Editable text = retrive_address_credentials.this.userInput2.getText();
            Objects.requireNonNull(text);
            if (text.toString().trim().isEmpty()) {
                retrive_address_credentials.this.userInput2.setError("Required");
                retrive_address_credentials.this.userInput2.requestFocus();
                return;
            }
            Editable text2 = retrive_address_credentials.this.userInput.getText();
            Objects.requireNonNull(text2);
            if (text2.toString().trim().isEmpty()) {
                retrive_address_credentials.this.userInput.setError("Required");
                retrive_address_credentials.this.userInput.requestFocus();
                return;
            }
            Editable text3 = retrive_address_credentials.this.userInput3.getText();
            Objects.requireNonNull(text3);
            if (text3.toString().trim().isEmpty()) {
                retrive_address_credentials.this.userInput3.setError("Required");
                retrive_address_credentials.this.userInput3.requestFocus();
                return;
            }
            Editable text4 = retrive_address_credentials.this.userInput4.getText();
            Objects.requireNonNull(text4);
            if (text4.toString().trim().isEmpty()) {
                retrive_address_credentials.this.userInput4.setError("Required");
                retrive_address_credentials.this.userInput4.requestFocus();
                return;
            }
            Editable text5 = retrive_address_credentials.this.userInput5.getText();
            Objects.requireNonNull(text5);
            if (text5.toString().trim().isEmpty()) {
                retrive_address_credentials.this.userInput5.setError("Required");
                retrive_address_credentials.this.userInput5.requestFocus();
            } else if (retrive_address_credentials.this.userInput5.length() < 6) {
                retrive_address_credentials.this.userInput5.setError("Min zip length should be six characters!");
                retrive_address_credentials.this.userInput5.requestFocus();
            } else {
                retrive_address_credentials.this.result.setText(retrive_address_credentials.this.userInput2.getText().toString() + "\n" + retrive_address_credentials.this.userInput3.getText().toString() + "\n" + retrive_address_credentials.this.userInput4.getText().toString() + " " + retrive_address_credentials.this.userInput5.getText().toString() + "\n" + retrive_address_credentials.this.userInput.getText().toString());
                retrive_address_credentials.this.EDS.setVisibility(View.VISIBLE);
                this.dialog.dismiss();
            }
        }
    }
}
