package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NossaEquipeActivity extends AppCompatActivity {
    LinearLayout linearbtnvoltarnossaequipe;
    /*                                 Instagram                                  **/ /*              GitHub                                                                        **/  /**                                      Twitter                                                 **/
    CardView cardviewinstadanilo, cardViewinstakaua, cardViewinstamatheus, cardViewinstapedro, cardviewbtngithubdanilo, cardviewbtngithubkaua, cardviewbtngithubmatheus, cardviewbtngithubpedro, cardviewbtntwitterdanilo, cardviewbtntwitterkaua, cardviewbtntwittermatheus, cardviewbtntwitterpedro;
    String cpfrecebidoservicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nossa_equipe);
        linearbtnvoltarnossaequipe = findViewById(R.id.linearbtnvoltarnossaequipe);
        /*                               Instagram                               */
        cardviewinstadanilo = findViewById(R.id.cardviewinstadanilo);
        cardViewinstakaua = findViewById(R.id.cardViewinstakaua);
        cardViewinstamatheus = findViewById(R.id.cardViewinstamatheus);
        cardViewinstapedro = findViewById(R.id.cardViewinstapedro);
        /*                              GitHub                                   */
        cardviewbtngithubdanilo = findViewById(R.id.cardviewbtngithubdanilo);
        cardviewbtngithubkaua = findViewById(R.id.cardviewbtngithubkaua);
        cardviewbtngithubmatheus = findViewById(R.id.cardviewbtngithubmatheus);
        cardviewbtngithubpedro = findViewById(R.id.cardviewbtngithubpedro);
        /*                              Twitter                                  */
        cardviewbtntwitterdanilo = findViewById(R.id.cardviewbtntwitterdanilo);
        cardviewbtntwitterkaua = findViewById(R.id.cardviewbtntwitterkaua);
        cardviewbtntwittermatheus = findViewById(R.id.cardviewbtntwittermatheus);
        cardviewbtntwitterpedro = findViewById(R.id.cardviewbtntwitterpedro);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidoservicos = bundle.getString("cpfusu");

        //  When is clicked in this Linear Activity is finish
        linearbtnvoltarnossaequipe.setOnClickListener(v -> {
            Intent voltaraosservicos = new Intent(NossaEquipeActivity.this,ServicosActivity.class);
            voltaraosservicos.putExtra("cpfusu",cpfrecebidoservicos);
            startActivity(voltaraosservicos);
            finish();
        });

        //  When is clicked in this cadview go to Instagram Page of Danilo
        cardviewinstadanilo.setOnClickListener(v -> {
            Toast.makeText(this, "Este funcionário não contem instagram!!\nVocê será redirecionado ao instagram da System Strength", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(() -> {
                Uri insta = Uri.parse("https://www.instagram.com/systemstrength/");
                Intent instaintent = new Intent(Intent.ACTION_VIEW, insta);
                startActivity(instaintent);
            },900);
        });

        //  When is clicked in this cadview go to Instagram Page of Kauã
        cardViewinstakaua.setOnClickListener(v -> {
            Uri insta = Uri.parse("https://www.instagram.com/ka_vitorio/");
            Intent instaintent = new Intent(Intent.ACTION_VIEW, insta);
            startActivity(instaintent);
        });

        //  When is clicked in this cadview go to Instagram Page of Matheus
        cardViewinstamatheus.setOnClickListener(v -> {
            Uri insta = Uri.parse("https://www.instagram.com/matheusssleite/");
            Intent instaintent = new Intent(Intent.ACTION_VIEW, insta);
            startActivity(instaintent);
        });

        //  When is clicked in this cadview go to Instagram Page of Pedro
        cardViewinstapedro.setOnClickListener(v -> {
            Uri insta = Uri.parse("https://www.instagram.com/pedrohmmf03/");
            Intent instaintent = new Intent(Intent.ACTION_VIEW, insta);
            startActivity(instaintent);
        });

        //  When is clicked in this cadview go to GitHub Page of Danilo
        cardviewbtngithubdanilo.setOnClickListener(v -> {
            Uri git = Uri.parse("https://github.com/daniloosi");
            Intent gitintent = new Intent(Intent.ACTION_VIEW, git);
            startActivity(gitintent);
        });

        //  When is clicked in this cadview go to GitHub Page of Kauã
        cardviewbtngithubkaua.setOnClickListener(v -> {
            Uri git = Uri.parse("https://github.com/Kauavitorio");
            Intent gitintent = new Intent(Intent.ACTION_VIEW, git);
            startActivity(gitintent);
        });

        //  When is clicked in this cadview go to GitHub Page of Matheus
        cardviewbtngithubmatheus.setOnClickListener(v -> {
            Uri git = Uri.parse("https://github.com/Theus03");
            Intent gitintent = new Intent(Intent.ACTION_VIEW, git);
            startActivity(gitintent);
        });

        //  When is clicked in this cadview go to GitHub Page of Pedro
        cardviewbtngithubpedro.setOnClickListener(v -> {
        Uri git = Uri.parse("https://github.com/pedrohmmf");
            Intent gitintent = new Intent(Intent.ACTION_VIEW, git);
            startActivity(gitintent);
        });

        //  When is clicked in this cadview go to Twitter Page of Danilo
        cardviewbtntwitterdanilo.setOnClickListener(v -> {
            Uri twitter = Uri.parse("https://twitter.com/saairyu_");
            Intent twitterintent = new Intent(Intent.ACTION_VIEW, twitter);
            startActivity(twitterintent);
        });

        //  When is clicked in this cadview go to Twitter Page of Kauã
        cardviewbtntwitterkaua.setOnClickListener(v -> {
            Uri twitter = Uri.parse("https://twitter.com/kauavitorioofc");
            Intent gitintent = new Intent(Intent.ACTION_VIEW, twitter);
            startActivity(gitintent);
        });

        //  When is clicked in this cadview go to Twitter Page of Matheus
        cardviewbtntwittermatheus.setOnClickListener(v -> {
            Uri twitter = Uri.parse("https://twitter.com/Matheus30134191");
            Intent twitterintent = new Intent(Intent.ACTION_VIEW, twitter);
            startActivity(twitterintent);
        });

        //  When is clicked in this cadview go to Twitter Page of Pedro
        cardviewbtntwitterpedro.setOnClickListener(v -> {
            Toast.makeText(this, "Este funcionário não contem Twitter!!!\nVocê será redirecionado ao Twitter da System Strength", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(() -> {
                Uri twitter = Uri.parse("https://twitter.com/SystemStrength1");
                Intent twitterintent = new Intent(Intent.ACTION_VIEW, twitter);
                startActivity(twitterintent);
            },900);
        });
    }

    @Override
    public void onBackPressed() {
        Intent voltaraosservicos = new Intent(NossaEquipeActivity.this,ServicosActivity.class);
        voltaraosservicos.putExtra("cpfusu",cpfrecebidoservicos);
        startActivity(voltaraosservicos);
        finish();
    }
}