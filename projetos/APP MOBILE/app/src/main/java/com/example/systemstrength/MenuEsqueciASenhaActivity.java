package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class MenuEsqueciASenhaActivity extends AppCompatActivity {
    TextView txtcontatoprotoculo, txtprotocolo;
    ProgressBar progressminiloadingprotocolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_esqueci_a_senha);
        //  Ids
        txtcontatoprotoculo = findViewById(R.id.txtcontatoprotoculo);
        txtprotocolo = findViewById(R.id.txtprotocolo);
        progressminiloadingprotocolo = findViewById(R.id.progressminiloadingprotocolo);


        //  When screen open will set that 3 items Visible and Gone
        progressminiloadingprotocolo.setVisibility(View.VISIBLE);
        txtcontatoprotoculo.setVisibility(View.GONE);
        txtprotocolo.setVisibility(View.GONE);

        new Handler().postDelayed(() -> {
            progressminiloadingprotocolo.setVisibility(View.GONE);
            txtcontatoprotoculo.setVisibility(View.VISIBLE);
            txtprotocolo.setVisibility(View.VISIBLE);
        },800);

    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */