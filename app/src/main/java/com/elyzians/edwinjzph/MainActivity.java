package com.elyzians.edwinjzph;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int RC_APP_UPDATE = 100;
    FloatingActionButton FAB;
    /* access modifiers changed from: private */
    public AppUpdateManager appUpdateManager;
    TextView creative;
    TextView digital;
    TextView digitalcon;
    boolean doubleBackToExitPressedOnce = false;
    DrawerLayout drawerLayout;
    SwipeRefreshLayout swipe;
    ExtendedFloatingActionButton fab;
    ShimmerFrameLayout layout;
    ShimmerFrameLayout layout3;
    ShimmerFrameLayout layout400;
    myadapter3 myadapter3;
    NavigationView navigationView;
    TextView normal;
    public TextView nou;
    public TextView nou2;
    RecyclerView recview;
    RecyclerView recyclerView;
    DatabaseReference reference;
    TextView scribble;
    Toolbar toolbar;
    /* access modifiers changed from: private */
    public FirebaseUser user;
     String userid;
    TextView water;
    String deviceToken;
    myadapter3 myadapter32;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView( R.layout.activity_main);

swipe=findViewById(R.id.swipe);
swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        fetch();
    }
});

        this.layout400 =  findViewById(R.id.shimmer400);

        AppUpdateManager create = AppUpdateManagerFactory.create(this);
        this.appUpdateManager = create;
        create.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                try {
                    MainActivity.this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, MainActivity.this, RC_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });

        RecyclerView recyclerView2 =  findViewById(R.id.recview299);
        this.recyclerView = recyclerView2;
        this.recview = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        myadapter32 = new myadapter3(new FirebaseRecyclerOptions.Builder().setQuery((Query) FirebaseDatabase.getInstance().getReference().child("recycleview"), post.class).build(), getApplication());
        this.myadapter3 = myadapter32;
        this.recview.setAdapter(myadapter32);
        this.nou = (TextView) findViewById(R.id.textView4);
        this.user = FirebaseAuth.getInstance().getCurrentUser();
        this.nou2 = (TextView) findViewById(R.id.textView11);
        this.normal = (TextView) findViewById(R.id.normal);
        this.water = (TextView) findViewById(R.id.watercolour);
        this.scribble = (TextView) findViewById(R.id.scribble);
        this.digital = (TextView) findViewById(R.id.digital);
        this.digitalcon = (TextView) findViewById(R.id.digitalcon);
        this.layout = (ShimmerFrameLayout) findViewById(R.id.shimmer);
        this.layout3 = (ShimmerFrameLayout) findViewById(R.id.shimmer3);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        this.FAB = floatingActionButton;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = "https://api.whatsapp.com/send?phone=" + "+91 9074201648";
                try {
                    MainActivity.this.getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    MainActivity.this.startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        this.creative = (TextView) findViewById(R.id.creative);
        this.normal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this.getBaseContext(), retrive_address_credentials.class);
                    intent.putExtra("portrait", "Normal portrait");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.water.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this.getBaseContext(), retrive_address_credentials.class);
                    intent.putExtra("water", "Portrait watercolour");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.scribble.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this, retrive_address_credentials.class);
                    intent.putExtra("scribble", "Scribble art");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.digital.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this.getBaseContext(), retrive_address_credentials.class);
                    intent.putExtra("digital", "Digital art");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.digitalcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this.getBaseContext(), retrive_address_credentials.class);
                    intent.putExtra("digitalcon", "Digital concepted art");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.creative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    Intent intent = new Intent(MainActivity.this.getBaseContext(), retrive_address_credentials.class);
                    intent.putExtra("creative", "Creative ideas");
                    MainActivity.this.startActivity(intent);
                    return;
                }
                Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
            }
        });
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView2.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        if (this.user != null) {
            menu.findItem(R.id.nav_logout).setVisible(true);
            menu.findItem(R.id.nav_login).setVisible(false);
        } else {
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);
        }
        this.navigationView = navigationView2;
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView2.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar4);
        this.toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        setTitle("Home");
        this.navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, this.toolbar, R.string.open_navi, R.string.close_navi);
        this.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.user != null) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, retrive_address_credentials.class));
                } else {
                    Toast.makeText(MainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.reference = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser firebaseUser = this.user;
        if (firebaseUser != null) {
            this.userid = firebaseUser.getUid();
            final TextView textView = (TextView) findViewById(R.id.textView10);
            this.reference.child(this.userid).addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user user = (user) dataSnapshot.getValue(user.class);
                    if (user != null) {
                        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                deviceToken = task.getResult();
                            }
                        });
                        DatabaseReference child = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("token");
                        child.setValue(new token(deviceToken));
                        MainActivity.this.layout.stopShimmer();
                        MainActivity.this.layout.hideShimmer();
                        MainActivity.this.layout.setVisibility(View.GONE);
                        MainActivity.this.layout3.stopShimmer();
                        MainActivity.this.layout3.hideShimmer();
                        MainActivity.this.layout3.setVisibility(View.GONE);
                        MainActivity.this.layout400.stopShimmer();
                        MainActivity.this.layout400.hideShimmer();

                        MainActivity.this.layout400.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        MainActivity.this.nou.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        MainActivity.this.nou2.setVisibility(View.VISIBLE);
                        textView.setText("Hi\n" + user.Username);
                        MainActivity.this.nou.setText(user.nou);
                        MainActivity.this.nou2.setText(user.nou2);
                    }
                }

                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Something Wrong Happened", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            FirebaseDatabase.getInstance().getReference().child("Notification").addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Noutification noutification = (Noutification) dataSnapshot.getValue(Noutification.class);
                    if (noutification != null) {
                        MainActivity.this.layout.stopShimmer();
                        MainActivity.this.layout.hideShimmer();
                        MainActivity.this.layout.setVisibility(View.GONE);
                        MainActivity.this.layout3.stopShimmer();
                        MainActivity.this.layout3.hideShimmer();
                        MainActivity.this.layout3.setVisibility(View.GONE);
                        MainActivity.this.layout400.stopShimmer();
                        MainActivity.this.layout400.hideShimmer();
                        MainActivity.this.layout400.setVisibility(View.GONE);
                        MainActivity.this.recyclerView.setVisibility(View.VISIBLE);
                        MainActivity.this.nou.setVisibility(View.VISIBLE);
                        MainActivity.this.nou2.setVisibility(View.VISIBLE);
                        MainActivity.this.nou.setText(noutification.nou2);
                        MainActivity.this.nou2.setText(noutification.nou);
                    }
                }

                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                }
            });
        }


        this.navigationView.setCheckedItem((int) R.id.nav_home);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.fab = (ExtendedFloatingActionButton) findViewById(R.id.neethu);
        ((NestedScrollView) findViewById(R.id.nsv)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                if (i2 > i4) {
                    MainActivity.this.fab.hide();
                } else {
                    MainActivity.this.fab.show();
                }
            }
        });
        this.fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.getIntent());
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });
        this.appUpdateManager.registerListener(this.installStateUpdatedListener);
    }



    private final InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState installState) {
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                MainActivity.this.showCompleteupdate();
            }
        }
    };

    public void onStop() {
        if (appUpdateManager != null)
            appUpdateManager.unregisterListener(this.installStateUpdatedListener);
        super.onStop();
    }

    /* access modifiers changed from: private */
    public void showCompleteupdate() {
        Snackbar make = Snackbar.make(findViewById(R.id.content), (CharSequence) "New app is ready!", Snackbar.LENGTH_INDEFINITE);
        make.setAction((CharSequence) "Install", (View.OnClickListener) new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.appUpdateManager.completeUpdate();
            }
        });
        make.show();
    }

   @Override
    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        if (requestCode == RC_APP_UPDATE && resultCode != RESULT_OK) {
            Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen( GravityCompat.START)) {
            this.drawerLayout.closeDrawer((int) GravityCompat.START);
        } else if (this.doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MainActivity.this.doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.accout, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.contactuss /*2131296418*/:
                startActivity(getIntent());
                startActivity(new Intent(this, MainActivity4.class));
                break;
            case R.id.insta /*2131296564*/:
                finish();
                startActivity(new Intent(this, insta.class));
                break;
            case R.id.nav_about /*2131296669*/:
                startActivity(new Intent(this, MainActivity3.class));
                break;
            case R.id.nav_acccout /*2131296670*/:
                if (this.user == null) {
                    startActivity(getIntent());
                    startActivity(new Intent(this, Login_activity.class));
                    Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    startActivity(new Intent(this, account_details.class));
                    break;
                }
            case R.id.nav_book /*2131296671*/:
                if (this.user == null) {
                    startActivity(getIntent());
                    startActivity(new Intent(this, Login_activity.class));
                    Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    startActivity(new Intent(this, retrive_address_credentials.class));
                    break;
                }
            case R.id.nav_login /*2131296673*/:
                startActivity(getIntent());
                startActivity(new Intent(this, Login_activity.class));
                break;
            case R.id.nav_logout /*2131296674*/:
                finish();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(new Intent(this, loginactivity2.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.nav_status /*2131296675*/:
                startActivity(getIntent());
                if (this.user == null) {
                    startActivity(getIntent());
                    startActivity(new Intent(this, Login_activity.class));
                    Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    startActivity(new Intent(this, MainActivity2.class));
                    break;
                }
            case R.id.share /*2131296770*/:
                finish();
                startActivity(getIntent());
                try {
                    Intent intent2 = new Intent("android.intent.action.SEND");
                    intent2.setType("text/plain");
                    intent2.putExtra("android.intent.extra.SUBJECT", "My application name");
                    intent2.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n");
                    startActivity(Intent.createChooser(intent2, "choose one"));
                    break;
                } catch (Exception unused) {
                    break;
                }
        }
        this.drawerLayout.closeDrawer((int) GravityCompat.START);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.ee) {
            return true;
        }
        if (this.user != null) {
            startActivity(new Intent(this, account_details.class));
        } else {
            startActivity(getIntent());
            startActivity(new Intent(this, Login_activity.class));
            Toast.makeText(this, "Please login to continue", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    public void onStart() {
        this.myadapter3.startListening();
        super.onStart();

    }
    public void fetch() {
        this.recview.setAdapter(myadapter32);
        myadapter32.notifyDataSetChanged();
        swipe.setRefreshing(false);
    }





}
