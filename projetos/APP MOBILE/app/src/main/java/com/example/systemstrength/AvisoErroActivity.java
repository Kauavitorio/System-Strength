package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AvisoErroActivity extends AppCompatActivity {
    LinearLayout linearbtnvoltaravisoerro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_erro);
        linearbtnvoltaravisoerro = findViewById(R.id.linearbtnvoltaravisoerro);

        linearbtnvoltaravisoerro.setOnClickListener(v -> finish());

    }
}