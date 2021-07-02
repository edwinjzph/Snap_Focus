package com.elyzians.edwinjzph;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class insta extends AppCompatActivity {
    ProgressBar myprogressbar;
    WebView mywebview;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_insta);
        System.setProperty("https.protocols", "TLSv1.1");
        Toolbar toolbar = (Toolbar) findViewById(R.id.insta3);
        toolbar.setTitle((CharSequence) "Privacy policy");
        toolbar.setNavigationIcon((int) R.drawable.ic_baseline_arrow_back_ios_24);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                insta.this.dialog();
            }
        });
        ActionBar supportActionBar = getSupportActionBar();
        Objects.requireNonNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        this.mywebview = (WebView) findViewById(R.id.mywebview);
        this.myprogressbar = (ProgressBar) findViewById(R.id.myprogressbar);
        this.mywebview.loadUrl("https://snap-focus-productio.flycricket.io/privacy.html");
        this.mywebview.getSettings().setJavaScriptEnabled(true);
        this.mywebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                insta.this.myprogressbar.setProgress(i);
                super.onProgressChanged(webView, i);
            }
        });
        this.mywebview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                insta.this.myprogressbar.setVisibility(View.VISIBLE);
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                insta.this.myprogressbar.setVisibility(View.INVISIBLE);
                super.onPageFinished(webView, str);
            }
        });
    }

    public void onBackPressed() {
        if (this.mywebview.canGoBack()) {
            this.mywebview.goBack();
        } else {
            dialog();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.drawable.ic_baseline_arrow_back_24) {
            dialog();
        }
        return super.onContextItemSelected(menuItem);
    }

    public void dialog() {
        DialogInterface.OnClickListener onClickListener = null;
        final AlertDialog show = new AlertDialog.Builder(this).setTitle((CharSequence) "Exit").setMessage((CharSequence) "Do you want to exit?").setPositiveButton((CharSequence) "Exit", onClickListener).setNegativeButton((CharSequence) "Cancel", onClickListener).show();
        show.getButton(-1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                insta.this.startActivity(new Intent(insta.this, MainActivity.class));
            }
        });
        show.getButton(-2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                show.dismiss();
            }
        });
    }
}
