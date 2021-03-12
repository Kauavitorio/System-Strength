package com.example.systemstrength;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Classes.Agenda.DaoAgenda;
import com.example.systemstrength.Classes.Agenda.DtoAgenda;
import com.example.systemstrength.Classes.Login.DaoLogins;
import com.example.systemstrength.Classes.Login.DtoLogins;

import java.util.ArrayList;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class PrincipalActivity extends AppCompatActivity {
    LinearLayout linearbtnhomeprincipal, linearbtnagendaprincipal, linearbtnservicosprincipal, linearbtnclienteprincipal, header, btnvoltarfecharmenuinfo, linearlicenseprincipal;
    TextView txtnomeusu, txthoraatual, txtcargoatual, txtproximareuniao, txtplusclientes, txtquantiadeagenda, txtvcpossui, txtsemagenda, txtnomeusermoreinfo, cardviewbtnmaisinfo,txtemailuserinfo;
    //ImageView imgavatarusu;
    ConstraintLayout constraintlayoutperfilusu, cardviewlogoutinfo;
    LottieAnimationView animacaoservicoespricipal, animacaoagenda, animacaohaagenda, animacaosemagenda, imganimationinfo, animaionbtnvoltarinfo;
    ArrayList<DtoAgenda> arrayListagenda;
    CardView  btnlogout, cardviewbtnirparacontato, loadingparaclientes, loadingparaagenda, loadingparaservicos, cardviewbtnverclientes, cardviewbtnveragenda,cardviewbtnlermaisjava, cardviewbtnlermaiscsharp, cardviewbtnlermaisjavascript, cardviewbtnlermaishtml, cardviewbtnlermaiscss;
    Dialog popup;
    String cpfrecebido;
    // String horarecebida;
    int novotempodeanimacao = 10;

    //  cardviewbtnlermaisjava
    //  cardviewbtnlermaiscsharp
    //  cardviewbtnlermaisjavascript
    //  cardviewbtnlermaishtml
    //  cardviewbtnlermaiscss


    @SuppressWarnings("deprecation")
    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        linearbtnhomeprincipal = findViewById(R.id.linearbtnhomeprincipal);
        linearbtnagendaprincipal = findViewById(R.id.linearbtnagendaprincipal);
        linearbtnservicosprincipal = findViewById(R.id.linearbtnservicosprincipal);
        linearbtnclienteprincipal = findViewById(R.id.linearbtnclienteprincipal);
        linearlicenseprincipal = findViewById(R.id.linearlicenseprincipal);
        constraintlayoutperfilusu = findViewById(R.id.constraintlayoutperfilusu);
        cardviewbtnverclientes = findViewById(R.id.cardviewbtnverclientes);
        loadingparaclientes = findViewById(R.id.loadingparaclientes);
        loadingparaservicos = findViewById(R.id.loadingparaservicos);
        loadingparaagenda = findViewById(R.id.loadingparaagenda);
        txtplusclientes = findViewById(R.id.txtplusclientes);
        animacaoservicoespricipal = findViewById(R.id.animacaoservicoespricipal);
        animacaoagenda = findViewById(R.id.animacaoagenda);
        txtnomeusu = findViewById(R.id.txtnomeusu);
        txthoraatual = findViewById(R.id.txthoraatual);
        //imgavatarusu = findViewById(R.id.imgavatarusu);
        txtcargoatual = findViewById(R.id.txtcargoatual);
        txtproximareuniao = findViewById(R.id.txtproximareuniao);
        txtquantiadeagenda = findViewById(R.id.txtquantiadeagenda);
        animacaohaagenda = findViewById(R.id.animacaohaagenda);
        txtvcpossui = findViewById(R.id.txtvcpossui);
        txtsemagenda = findViewById(R.id.txtsemagenda);
        animacaosemagenda = findViewById(R.id.animacaosemagenda);
        cardviewbtnveragenda = findViewById(R.id.cardviewbtnveragenda);
        cardviewlogoutinfo = findViewById(R.id.cardviewlogoutinfo);
        header = findViewById(R.id.header);
        btnvoltarfecharmenuinfo = findViewById(R.id.btnvoltarfecharmenuinfo);
        btnlogout = findViewById(R.id.btnlogout);
        cardviewbtnmaisinfo = findViewById(R.id.cardviewbtnmaisinfo);
        txtnomeusermoreinfo = findViewById(R.id.txtnomeusermoreinfo);
        txtemailuserinfo = findViewById(R.id.txtemailuserinfo);
        imganimationinfo = findViewById(R.id.imganimationinfo);
        animaionbtnvoltarinfo = findViewById(R.id.animaionbtnvoltarinfo);
        cardviewbtnirparacontato = findViewById(R.id.cardviewbtnirparacontato);
        cardviewbtnlermaisjava = findViewById(R.id.cardviewbtnlermaisjava);
        cardviewbtnlermaiscsharp = findViewById(R.id.cardviewbtnlermaiscsharp);
        cardviewbtnlermaisjavascript = findViewById(R.id.cardviewbtnlermaisjavascript);
        cardviewbtnlermaishtml = findViewById(R.id.cardviewbtnlermaishtml);
        cardviewbtnlermaiscss = findViewById(R.id.cardviewbtnlermaiscss);
        popup = new Dialog(this);

        //  Defining somethings with GONE

        //  Taking the current time and placing it on TextView
        //horarecebida = new SimpleDateFormat("HH:mm").format(new Date());
        //txthoraatual.setText(horarecebida);

        //  Get Name of user Turn on this later
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebido = bundle.getString("cpfusu");
        DaoLogins daoLogins = new DaoLogins(PrincipalActivity.this);
        DtoLogins dtoLogins = daoLogins.verificarusuario(cpfrecebido);
        txtnomeusu.setText(dtoLogins.getNomefunc());
        txtnomeusermoreinfo.setText(dtoLogins.getNomefunc());
        txtemailuserinfo.setText(dtoLogins.getEmailfunc());
        txtcargoatual.setText(dtoLogins.getCargofunc());
        if (dtoLogins.getUltamareufunc() == null || dtoLogins.getUltamareufunc().equals("")){
            txtproximareuniao.setText("Não há reunião agendada");
        }else {
            txtproximareuniao.setText(dtoLogins.getUltamareufunc());
        }

        DaoAgenda daoAgenda = new DaoAgenda(PrincipalActivity.this);
        arrayListagenda = daoAgenda.consultarTodaagenda();
        if (arrayListagenda.size() > 0){
            animacaohaagenda.setVisibility(View.VISIBLE);
            txtquantiadeagenda.setVisibility(View.VISIBLE);
            txtvcpossui.setVisibility(View.VISIBLE);
            txtsemagenda.setVisibility(View.GONE);
            animacaosemagenda.setVisibility(View.GONE);
            if (arrayListagenda.size() == 1){
                txtquantiadeagenda.setText(arrayListagenda.size()+" Compromisso");
            }else{
                txtquantiadeagenda.setText(arrayListagenda.size()+" Compromissos");
            }
        }else {
            animacaohaagenda.setVisibility(View.GONE);
            txtquantiadeagenda.setVisibility(View.GONE);
            txtvcpossui.setVisibility(View.GONE);
            txtsemagenda.setVisibility(View.VISIBLE);
            animacaosemagenda.setVisibility(View.VISIBLE);
        }

        //  When click in this cardview will go to ContactActivity
        cardviewbtnirparacontato.setOnClickListener(v -> {
            Intent irparacontato = new Intent(PrincipalActivity.this,ContatoActivity.class);
            irparacontato.putExtra("cpfusu", cpfrecebido);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_para_cima, R.anim.mover_para_baixo);
            ActivityCompat.startActivity(PrincipalActivity.this,irparacontato, activityOptionsCompat.toBundle());
            //  startActivity(irparacontato);
            //  finish();
        });

        //  When you click in this liner will go to AgendaActivity
        cardviewbtnveragenda.setOnClickListener(v -> {
            Intent irparaagenda = new Intent(PrincipalActivity.this,AgendaActivity.class);
            irparaagenda.putExtra("cpfusu",cpfrecebido);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda, R.anim.mover_direita);
            ActivityCompat.startActivity(PrincipalActivity.this,irparaagenda, activityOptionsCompat.toBundle());
            finish();
            // startActivity(irparaagenda);
            // finish();
        });

        //  When click in this constraint will open menu off info
        constraintlayoutperfilusu.setOnClickListener(v -> {
            header.setVisibility(View.GONE);
            cardviewlogoutinfo.setVisibility(View.VISIBLE);
            DisableBtns();
            imganimationinfo.playAnimation();
            animaionbtnvoltarinfo.playAnimation();
        });

        //  When click in this cardview will to User Profile
        cardviewbtnmaisinfo.setOnClickListener(v -> {
            Intent irparaperfildeusuario = new Intent(PrincipalActivity.this,PerfilUsuarioActivity.class);
            irparaperfildeusuario.putExtra("cpfusu",cpfrecebido);
            startActivity(irparaperfildeusuario);
            finish();
        });

        //  Btn close menu
        btnvoltarfecharmenuinfo.setOnClickListener( v -> {
            header.setVisibility(View.VISIBLE);
            cardviewlogoutinfo.setVisibility(View.GONE);
            EnableBtns();
        });

        //  When click will show msg to confirm if really want to logout
        btnlogout.setOnClickListener(v -> {
            AlertDialog.Builder msg = new AlertDialog.Builder(PrincipalActivity.this);
            msg.setTitle("Log Out");
            msg.setMessage("Deseja realmente deslogar?");
            msg.setPositiveButton("Sim", (dialog, which) -> {
                Intent deslogar = new Intent(PrincipalActivity.this,MainActivity.class);
                deslogar.putExtra("novotempo",novotempodeanimacao);
                startActivity(deslogar);
                finish();
            });
            msg.setNegativeButton("Não", null);
            msg.show();
        });

        //  When you click in this liner will go to ClientesActivity
        cardviewbtnverclientes.setOnClickListener(v -> {
                Intent irparaclientes = new Intent(PrincipalActivity.this,ClientesActivity.class);
                irparaclientes.putExtra("cpfusu",cpfrecebido);
                startActivity(irparaclientes);
                finish();
        });

        //  When you click in this liner will see some message
        linearbtnhomeprincipal.setOnClickListener(v -> Toast.makeText(PrincipalActivity.this, "Você já está aqui!", Toast.LENGTH_SHORT).show());

        //  When you click in this liner will go to AgendaActivity
        linearbtnagendaprincipal.setOnClickListener(v -> {
            loadingparaagenda.setVisibility(View.VISIBLE);
            DisableBtns();
            animacaoagenda.playAnimation();
            new Handler().postDelayed(() -> {
                loadingparaagenda.setVisibility(View.GONE);
                animacaoagenda.pauseAnimation();
                Intent irparaagenda = new Intent(PrincipalActivity.this,AgendaActivity.class);
                irparaagenda.putExtra("cpfusu",cpfrecebido);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.mover_esquerda, R.anim.mover_direita);
                ActivityCompat.startActivity(PrincipalActivity.this,irparaagenda, activityOptionsCompat.toBundle());
                //  startActivity(irparaagenda);
                //  finish();
            },1300);
        });

        //  When you click in this liner will go to ServicosActivity
        linearbtnservicosprincipal.setOnClickListener(v ->{
            loadingparaservicos.setVisibility(View.VISIBLE);
            DisableBtns();
            animacaoservicoespricipal.setSpeed(2);
            new Handler().postDelayed(() -> {
                Intent irparaservicos = new Intent(PrincipalActivity.this,ServicosActivity.class);
                irparaservicos.putExtra("cpfusu",cpfrecebido);
                startActivity(irparaservicos);
                finish();
                loadingparaservicos.setVisibility(View.GONE);
            },1400);
        });

        //  When you click in this liner will go to ClientesActivity
        linearbtnclienteprincipal.setOnClickListener(v -> {
            loadingparaclientes.setVisibility(View.VISIBLE);
            DisableBtns();
            new Handler().postDelayed(() -> {
                Intent irparaclientes = new Intent(PrincipalActivity.this,ClientesActivity.class);
                irparaclientes.putExtra("cpfusu",cpfrecebido);
                startActivity(irparaclientes);
                finish();
            },1500);
        });

        //  When click here will open menu java
        cardviewbtnlermaisjava.setOnClickListener(v-> abririnfojava());

        //  When click here will open menu Csharp
        cardviewbtnlermaiscsharp.setOnClickListener(v -> abririnfojcsharp());

        //  When click here will open menu JS
        cardviewbtnlermaisjavascript.setOnClickListener(v -> abririnfojjs());

        //  When click here will open menu HTML
        cardviewbtnlermaishtml.setOnClickListener(v -> abririnfohtml());

        //  When click here will open menu CSS
        cardviewbtnlermaiscss.setOnClickListener(v -> abririnfocss());

        //  When click hero go to LicenseActivity
        linearlicenseprincipal.setOnClickListener(v -> {
            Intent irparalicensa = new Intent(PrincipalActivity.this,LicenseActivity.class);
            irparalicensa.putExtra("cpfusu",cpfrecebido);
            startActivity(irparalicensa);
            finish();
        });

    }

    private void abririnfojava(){
        LottieAnimationView btnvoltarjava;
        CardView cadviewfunc1, cadviewfunc2;
        popup.setContentView(R.layout.popupinfojava);
        btnvoltarjava = popup.findViewById(R.id.btnvoltarjava);
        cadviewfunc1 = popup.findViewById(R.id.cadviewfunc1);
        cadviewfunc2 = popup.findViewById(R.id.cadviewfunc2);

        cadviewfunc1.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc2.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });


        btnvoltarjava.setOnClickListener(v -> popup.dismiss());

        popup.show();
    }

    private void abririnfojcsharp(){
        LottieAnimationView btnvoltarcsharp;
        CardView cadviewfunc1, cadviewfunc2, cadviewfunc3, cadviewfunc4;
        popup.setContentView(R.layout.popupinfocsharp);
        btnvoltarcsharp = popup.findViewById(R.id.btnvoltarcsharp);
        cadviewfunc1 = popup.findViewById(R.id.cadviewfunc1);
        cadviewfunc2 = popup.findViewById(R.id.cadviewfunc2);
        cadviewfunc3 = popup.findViewById(R.id.cadviewfunc3);
        cadviewfunc4 = popup.findViewById(R.id.cadviewfunc4);

        cadviewfunc1.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc2.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc3.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc4.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });


        btnvoltarcsharp.setOnClickListener(v -> popup.dismiss());

        popup.show();
    }

    private void abririnfojjs(){
        LottieAnimationView btnvoltarjavascript;
        CardView cadviewfunc1, cadviewfunc2;
        popup.setContentView(R.layout.popupinfojavascript);
        btnvoltarjavascript = popup.findViewById(R.id.btnvoltarjavascript);
        cadviewfunc1 = popup.findViewById(R.id.cadviewfunc1);
        cadviewfunc2 = popup.findViewById(R.id.cadviewfunc2);

        cadviewfunc1.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc2.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });


        btnvoltarjavascript.setOnClickListener(v -> popup.dismiss());

        popup.show();
    }

    private void abririnfohtml(){
        LottieAnimationView btnvoltarhtml;
        CardView cadviewfunc1, cadviewfunc2;
        popup.setContentView(R.layout.popupinfohtml);
        btnvoltarhtml = popup.findViewById(R.id.btnvoltarhtml);
        cadviewfunc1 = popup.findViewById(R.id.cadviewfunc1);
        cadviewfunc2 = popup.findViewById(R.id.cadviewfunc2);

        cadviewfunc1.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc2.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });


        btnvoltarhtml.setOnClickListener(v -> popup.dismiss());

        popup.show();
    }

    private void abririnfocss(){
        LottieAnimationView btnvoltarcss;
        CardView cadviewfunc1, cadviewfunc2;
        popup.setContentView(R.layout.popupinfocss);
        btnvoltarcss = popup.findViewById(R.id.btnvoltarcss);
        cadviewfunc1 = popup.findViewById(R.id.cadviewfunc1);
        cadviewfunc2 = popup.findViewById(R.id.cadviewfunc2);

        cadviewfunc1.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });

        cadviewfunc2.setOnClickListener(v -> {
            Intent irparanossaequipe = new Intent(PrincipalActivity.this,NossaEquipeActivity.class);
            irparanossaequipe.putExtra("cpfusu",cpfrecebido);
            startActivity(irparanossaequipe);
        });


        btnvoltarcss.setOnClickListener(v -> popup.dismiss());

        popup.show();
    }

    private void DisableBtns(){
        linearbtnhomeprincipal.setEnabled(false);
        linearbtnagendaprincipal.setEnabled(false);
        linearbtnservicosprincipal.setEnabled(false);
        linearbtnclienteprincipal.setEnabled(false);
    }

    private void EnableBtns(){
        linearbtnhomeprincipal.setEnabled(true);
        linearbtnagendaprincipal.setEnabled(true);
        linearbtnservicosprincipal.setEnabled(true);
        linearbtnclienteprincipal.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (cardviewlogoutinfo.getVisibility() == View.VISIBLE){
            header.setVisibility(View.VISIBLE);
            cardviewlogoutinfo.setVisibility(View.GONE);
            EnableBtns();
        }else {
            finish();
        }
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */