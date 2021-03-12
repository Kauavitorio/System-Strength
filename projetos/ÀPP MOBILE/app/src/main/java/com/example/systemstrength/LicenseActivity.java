package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

public class LicenseActivity extends AppCompatActivity {
    LinearLayout linearlicensevoltaraoprincipal;
    Button btnlicensa;
    RelativeLayout  relativoinfo;
    LottieAnimationView licenseanimacaovoltaraoprincipal, animationinfo,  btncloselicense;
    ConstraintLayout contenderanimationinfo, contenderallinformation;
    Dialog  popuplicense;
    Animation animationparafrente;
    String cpfrecebidoprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        linearlicensevoltaraoprincipal = findViewById(R.id.linearlicensevoltaraoprincipal);
        licenseanimacaovoltaraoprincipal = findViewById(R.id.licenseanimacaovoltaraoprincipal);
        contenderanimationinfo = findViewById(R.id.contenderanimationinfo);
        contenderallinformation = findViewById(R.id.contenderallinformation);
        animationinfo = findViewById(R.id.animationinfo);
        relativoinfo = findViewById(R.id.relativoinfo);
        btnlicensa = findViewById(R.id.btnlicensa);
        btncloselicense = findViewById(R.id.btncloselicense);
        animationparafrente = AnimationUtils.loadAnimation(this,R.anim.animationparafrente);
        popuplicense = new Dialog(this);

        //  Get some information
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidoprincipal = bundle.getString("cpfusu");

        //  hidden relative
        relativoinfo.setAlpha(0);

        //  Set time to show information
        contenderanimationinfo.setVisibility(View.VISIBLE);
        contenderallinformation.setVisibility(View.GONE);
        animationinfo.setSpeed(2);
        new Handler().postDelayed(()-> {
            contenderanimationinfo.setVisibility(View.GONE);
            contenderallinformation.setVisibility(View.VISIBLE);
        },1900);

        //  When click here will go to come back to PrincipalActivity
        linearlicensevoltaraoprincipal.setOnClickListener(v -> {
            licenseanimacaovoltaraoprincipal.playAnimation();
            new Handler().postDelayed(()-> {
                Intent voltaraoprincipal = new Intent(LicenseActivity.this,PrincipalActivity.class);
                voltaraoprincipal.putExtra("cpfusu", cpfrecebidoprincipal);
                startActivity(voltaraoprincipal);
            },600);
        });

        //  When click here will close de Info
        btncloselicense.setOnClickListener(v -> {
            relativoinfo.startAnimation(animationparafrente);
            relativoinfo.setAlpha(0);
            btnlicensa.setVisibility(View.VISIBLE);
        });

        //  When click here will open the popup License
        btnlicensa.setOnClickListener(v -> {
            relativoinfo.setAlpha(1);
            btnlicensa.setVisibility(View.GONE);
            relativoinfo.startAnimation(animationparafrente);
        });
    }

    @Override
    public void onBackPressed() {
        Intent voltaraoprincipal = new Intent(LicenseActivity.this,PrincipalActivity.class);
        voltaraoprincipal.putExtra("cpfusu", cpfrecebidoprincipal);
        startActivity(voltaraoprincipal);
    }
}