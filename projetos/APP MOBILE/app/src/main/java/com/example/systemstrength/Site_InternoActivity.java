package com.example.systemstrength;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.airbnb.lottie.LottieAnimationView;

public class Site_InternoActivity extends AppCompatActivity {
    WebView view_website;
    ConstraintLayout site_is_loading;
    String cpfrecebidodaprincipal;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site__interno);
        view_website = findViewById(R.id.view_website);
        site_is_loading = findViewById(R.id.site_is_loading);

        //  Set somethings with gone
        site_is_loading.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodaprincipal = bundle.getString("cpfusu");

        view_website.loadUrl("https://www.kauavitorio.com");
        view_website.getSettings().setJavaScriptEnabled(true);

        view_website.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                site_is_loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                site_is_loading.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().getHost().equals("www.kauavitorio.com")){
                    return false;
                }

                Intent intent1 = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent1);

                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && view_website.canGoBack()){
            view_website.goBack();
            return true;
        }else {
            Intent voltar_contato = new Intent(Site_InternoActivity.this,ContatoActivity.class);
            voltar_contato.putExtra("cpfusu", cpfrecebidodaprincipal);
            startActivity(voltar_contato);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}