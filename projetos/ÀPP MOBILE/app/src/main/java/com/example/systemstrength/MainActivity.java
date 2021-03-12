package com.example.systemstrength;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Classes.Login.DaoLogins;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class MainActivity extends AppCompatActivity {
    //  Handler More used
    Handler apareceropcoes = new Handler ();
    Handler tempodeloading = new Handler ();
    Handler tempoloadinglogin = new Handler ();
    Handler tempoavisocor = new Handler ();
    RelativeLayout relativeprincipallogin, relativeinferiorlogin, relativeimgsystem;
    Button btncriarconta, btnesqueciasenha, btnlogaragora;
    ImageView imgolhoopenpassword, imgolhoclosepassword;
    EditText edittextsenha, edittextcpffunc;
    LottieAnimationView progressloadinglogin;
    TextView txttitulocpf, textviewsenha;
    int Tempodeanimacao = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ids
        relativeprincipallogin = findViewById(R.id.relativeprincipallogin);
        relativeinferiorlogin = findViewById(R.id.relativeinferiorlogin);
        relativeimgsystem = findViewById(R.id.relativeimgsystem);
        btncriarconta = findViewById(R.id.btncriarconta);
        btnlogaragora = findViewById(R.id.btnlogaragora);
        btnesqueciasenha = findViewById(R.id.btnesqueciasenha);
        imgolhoopenpassword = findViewById(R.id.imgolhoopenpassword);
        imgolhoclosepassword = findViewById(R.id.imgolhoclosepassword);
        edittextsenha = findViewById(R.id.edittextsenha);
        edittextcpffunc = findViewById(R.id.edittextcpffunc);
        progressloadinglogin = findViewById(R.id.progressloadinglogin);
        txttitulocpf = findViewById(R.id.txttitulocpf);
        textviewsenha = findViewById(R.id.textviewsenha);
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        edittextcpffunc.addTextChangedListener(MaskEditUtil.mask(edittextcpffunc, MaskEditUtil.FORMAT_CPF));

        //  Verification in Intent for to see if is null
        Intent intent = getIntent();
        if (intent == null){
            Tempodeanimacao = 2000 ;
        }
        else {
            Bundle bundle = intent.getExtras();
            if (bundle == null){
                Tempodeanimacao = 2000 ;
            }else{
                Tempodeanimacao = bundle.getInt("novotempo");
                edittextsenha.setText(bundle.getString("senhacadastrado"));
                edittextcpffunc.setText(bundle.getString("cpfcadastrado"));
            }
        }

        verificarnet();

        //  when starting an activity the ImageView and ProgressBar will be GONE
        imgolhoclosepassword.setVisibility(View.GONE);
        progressloadinglogin.setVisibility(View.GONE);

        //This is delay for timeout
        apareceropcoes.postDelayed(() -> {
            //My two Relative Layouts
            relativeprincipallogin.setVisibility(View.VISIBLE);
            relativeinferiorlogin.setVisibility(View.VISIBLE);
            /*Intent irparalocaldetest = new Intent(MainActivity.this,PrincipalActivity.class);
            startActivity(irparalocaldetest);
            finish();*/
        },Tempodeanimacao);

        //  Button CriarConta Press
        btncriarconta.setOnClickListener(v -> {
            irparaloading();
            tempodeloading.postDelayed(() -> {
                Intent irparacriarconta = new Intent(MainActivity.this,CriarContaActivity.class);
                startActivity(irparacriarconta);
                finish();
            },300);
        });

        //  Butoon Esqueci a Senha Press
        btnesqueciasenha.setOnClickListener(v -> {
            irparaloading();
            tempodeloading.postDelayed(() -> {
                Intent irparaesqueciasenha = new Intent(MainActivity.this,MenuEsqueciASenhaActivity.class);
                startActivity(irparaesqueciasenha);
            },400);
        });

        //  When you click on the open eye it will execute the defined commands
        imgolhoopenpassword.setOnClickListener(v -> {
            edittextsenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgolhoopenpassword.setVisibility(View.GONE);
            imgolhoclosepassword.setVisibility(View.VISIBLE);
            edittextsenha.setSelection(edittextsenha.getText().length());
        });

        //  When you click on the closed eye it will execute the defined commands
        imgolhoclosepassword.setOnClickListener(v -> {
            edittextsenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgolhoopenpassword.setVisibility(View.VISIBLE);
            imgolhoclosepassword.setVisibility(View.GONE);
            edittextsenha.setSelection(edittextsenha.getText().length());
        });

        //  When making a change in the text of edittext it will execute a sequence of commands in real time
        edittextsenha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextsenha.getText() == null || edittextsenha.getText().length() == 0){
                    imgolhoopenpassword.setVisibility(View.GONE);
                    imgolhoclosepassword.setVisibility(View.GONE);
                    edittextsenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                if (edittextsenha.getText().length() > 0){
                    imgolhoclosepassword.setVisibility(View.VISIBLE);
                }

            }
        });

        //  Clicking on the Logar Agora now will execute a series of defined commands
        btnlogaragora.setOnClickListener(v -> {
            if (edittextcpffunc.getText() == null || edittextcpffunc.getText().length() < 5){
                Toast.makeText(MainActivity.this, "Preencha corretamente o campo: CPF\nCaracteres necessários: 11", Toast.LENGTH_SHORT).show();
                edittextcpffunc.requestFocus();
                imm.showSoftInput(edittextcpffunc, InputMethodManager.SHOW_IMPLICIT);
                tempoavisocor.postDelayed(() -> {
                    txttitulocpf.setTextColor(Color.RED);
                    tempoavisocor.postDelayed(() -> txttitulocpf.setTextColor(Color.WHITE),300);
                },50);
            }
            else if (edittextsenha.getText() == null || edittextsenha.getText().length() < 8){
                Toast.makeText(MainActivity.this, "Preencha corretamente o campo: Senha\nMinimo de caracteres: 8", Toast.LENGTH_SHORT).show();
                edittextsenha.requestFocus();
                imm.showSoftInput(edittextsenha, InputMethodManager.SHOW_IMPLICIT);
                tempoavisocor.postDelayed(() -> {
                    textviewsenha.setTextColor(Color.RED);
                    tempoavisocor.postDelayed(() -> textviewsenha.setTextColor(Color.WHITE),300);
                },50);
            }
            else {
                edittextcpffunc.setEnabled(false);
                edittextsenha.setEnabled(false);
                relativeprincipallogin.setVisibility(View.GONE);
                relativeinferiorlogin.setVisibility(View.GONE);
                new Handler().postDelayed(() -> {
                    progressloadinglogin.setVisibility(View.VISIBLE);
                    progressloadinglogin.playAnimation();
                },150);
                tempoloadinglogin.postDelayed(() -> {
                    DaoLogins daoLogins = new DaoLogins(MainActivity.this);
                    String cpf = edittextcpffunc.getText().toString();
                    String senha = edittextsenha.getText().toString();
                    boolean sucesso = daoLogins.onLogin(cpf,senha);
                    if (sucesso){
                        Intent irparaprincipal = new Intent(MainActivity.this,PrincipalActivity.class);
                        irparaprincipal.putExtra("cpfusu", cpf);
                        startActivity(irparaprincipal);
                        finish();
                    }
                    else {
                        edittextcpffunc.setEnabled(true);
                        edittextsenha.setEnabled(true);
                        edittextcpffunc.setText("");
                        edittextsenha.setText("");
                        relativeprincipallogin.setVisibility(View.VISIBLE);
                        relativeinferiorlogin.setVisibility(View.VISIBLE);
                        progressloadinglogin.setVisibility(View.GONE);
                        AlertDialog.Builder aviso = new AlertDialog.Builder(MainActivity.this);
                        aviso.setTitle("Eita.. :(");
                        aviso.setIcon(R.mipmap.ic_launcher_newlogo);
                        aviso.setMessage("CPF OU SENHA informados não estão corretos ou não estão cadastrados\n\nVerifique o CPF e a SENHA!");
                        aviso.setPositiveButton("OK", null);
                        aviso.show();
                    }
                },1050);
            }
        });

    }

    public void verificarnet(){
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cn.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            btncriarconta.setEnabled(true);
            btnesqueciasenha.setEnabled(true);
            btnlogaragora.setEnabled(true);
        }
        else{
            btncriarconta.setEnabled(true);
            btnesqueciasenha.setEnabled(true);
            btnlogaragora.setEnabled(true);
            btnlogaragora.setTextColor(Color.RED);
            btnesqueciasenha.setTextColor(Color.RED);
            btncriarconta.setTextColor(Color.RED);
            AlertDialog.Builder aviso = new AlertDialog.Builder(MainActivity.this);
            aviso.setTitle("Opss.. :(");
            aviso.setIcon(R.mipmap.ic_launcher_newlogo);
            aviso.setMessage("Você está sem conexão a internet\nNão será possivel realizar algumas ações!");
            aviso.setNeutralButton("Verificar novamente", (dialog, which) -> {
                Intent irparaloading = new Intent(MainActivity.this,LoadingActivity.class);
                startActivity(irparaloading);
                finish();
                tempodeloading.postDelayed(() -> {
                    Intent voltarmain = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(voltarmain);
                },800);
            });
            aviso.setPositiveButton("OK", null);
            aviso.show();
        }
    }

    private void irparaloading(){
        Intent irparaloading = new Intent(MainActivity.this,LoadingActivity.class);
        startActivity(irparaloading);
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */