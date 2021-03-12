package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;

public class ContratosActivity extends AppCompatActivity {
    LinearLayout linearvoltaraclientescontratos, headercontratos;
    ConstraintLayout basepdfreader;
    TextView txtcontratopalacepetz, txtcontratoapresentado, txtcontratoespn, txtcontratotesla, txtcontratodell, txtcontratosamsung, txtcontratoapple;
    LottieAnimationView clisemenucontratos;
    PDFView pdfreader;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratos);
        linearvoltaraclientescontratos = findViewById(R.id.linearvoltaraclientescontratos);
        clisemenucontratos = findViewById(R.id.clisemenucontratos);
        pdfreader = findViewById(R.id.pdfreader);
        headercontratos = findViewById(R.id.headercontratos);
        txtcontratopalacepetz = findViewById(R.id.txtcontratopalacepetz);
        txtcontratoapresentado = findViewById(R.id.txtcontratoapresentado);
        txtcontratoespn = findViewById(R.id.txtcontratoespn);
        txtcontratotesla = findViewById(R.id.txtcontratotesla);
        txtcontratodell = findViewById(R.id.txtcontratodell);
        txtcontratosamsung = findViewById(R.id.txtcontratosamsung);
        txtcontratoapple = findViewById(R.id.txtcontratoapple);
        basepdfreader = findViewById(R.id.basepdfreader);

        //  When click will open menu and show pdf of PalacePetz
        txtcontratopalacepetz.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("Palace Petz");
                pdfreader.fromAsset("PalacePetz.pdf").load();
            }
        });

        //  When click will open menu and show pdf of ESPN
        txtcontratoespn.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("ESPN");
                pdfreader.fromAsset("ESPN.pdf").load();
            }
        });

        //  When click will open menu and show pdf of Tesla
        txtcontratotesla.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("Tesla");
                pdfreader.fromAsset("Tesla.pdf").load();
            }
        });

        //  When click will open menu and show pdf of Dell
        txtcontratodell.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("Dell");
                pdfreader.fromAsset("Dell.pdf").load();
            }
        });

        //  When click will open menu and show pdf of Dell
        txtcontratosamsung.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("Samsung");
                pdfreader.fromAsset("Samsung.pdf").load();
            }
        });

        //  When click will open menu and show pdf of Dell
        txtcontratoapple.setOnClickListener(v -> {
            if (basepdfreader.getVisibility() == View.VISIBLE){
                basepdfreader.setVisibility(View.GONE);
                headercontratos.setVisibility(View.VISIBLE);
            }
            else {
                basepdfreader.setVisibility(View.VISIBLE);
                headercontratos.setVisibility(View.GONE);
                txtcontratoapresentado.setText("Apple");
                pdfreader.fromAsset("Apple.pdf").load();
            }
        });

        //  When click will close menu
        clisemenucontratos.setOnClickListener(v -> {
            basepdfreader.setVisibility(View.GONE);
            headercontratos.setVisibility(View.VISIBLE);
        });

        //  When click will exit activity
        linearvoltaraclientescontratos.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        if (basepdfreader.getVisibility() == View.VISIBLE){
            basepdfreader.setVisibility(View.GONE);
            headercontratos.setVisibility(View.VISIBLE);
        }else {
            finish();
        }
    }
}