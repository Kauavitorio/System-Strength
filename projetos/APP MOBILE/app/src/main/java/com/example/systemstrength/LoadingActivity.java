package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },1500);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Aguarde até o loading estiver completo", Toast.LENGTH_SHORT).show();
    }
}

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/