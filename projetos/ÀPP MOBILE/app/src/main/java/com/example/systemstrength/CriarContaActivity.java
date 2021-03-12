package com.example.systemstrength;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.systemstrength.Classes.Login.DaoLogins;
import com.example.systemstrength.Classes.Login.DtoLogins;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class CriarContaActivity extends AppCompatActivity {
    CardView cardviewbtncadastrar;
    Button btnvoltaraologin;
    EditText edittextsenhacadastro, edittextcargocadastro, edittexttelefonecadastro, edittextenderecocadastro, edittextemailcadastro, edittextcpfcadastro, edittextcadastronomefunc;
    TextView txtavisonomefunc, txtavisocpf, txtavisosenha;
    ImageView imgsenharigth, imgcpfrigth, imgolhoclosepasswordcadastro, imgolhoopenpasswordcadastro;
    int novotempodeanimacao  = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criarconta);
        //  Ids
        btnvoltaraologin = findViewById(R.id.btnvoltaraologin);
        cardviewbtncadastrar = findViewById(R.id.cardviewbtncadastrar);
        edittextsenhacadastro = findViewById(R.id.edittextsenhacadastro);
        edittextcargocadastro = findViewById(R.id.edittextcargocadastro);
        edittexttelefonecadastro = findViewById(R.id.edittexttelefonecadastro);
        edittextenderecocadastro = findViewById(R.id.edittextenderecocadastro);
        edittextemailcadastro = findViewById(R.id.edittextemailcadastro);
        edittextcpfcadastro = findViewById(R.id.edittextcpfcadastro);
        edittextcadastronomefunc = findViewById(R.id.edittextcadastronomefunc);
        txtavisonomefunc = findViewById(R.id.txtavisonomefunc);
        txtavisocpf = findViewById(R.id.txtavisocpf);
        txtavisosenha = findViewById(R.id.txtavisosenha);
        imgsenharigth = findViewById(R.id.imgsenharigth);
        imgcpfrigth = findViewById(R.id.imgcpfrigth);
        imgolhoclosepasswordcadastro = findViewById(R.id.imgolhoclosepasswordcadastro);
        imgolhoopenpasswordcadastro = findViewById(R.id.imgolhoopenpasswordcadastro);
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // Defining mask to editText
        edittextcpfcadastro.addTextChangedListener(MaskEditUtil.mask(edittextcpfcadastro, MaskEditUtil.FORMAT_CPF));
        edittexttelefonecadastro.addTextChangedListener(MaskEditUtil.mask(edittexttelefonecadastro, MaskEditUtil.FORMAT_FONE));

        // Defining some itens like GONE
        txtavisonomefunc.setVisibility(View.GONE);
        txtavisocpf.setVisibility(View.GONE);
        txtavisosenha.setVisibility(View.GONE);
        imgsenharigth.setVisibility(View.GONE);
        imgcpfrigth.setVisibility(View.GONE);
        imgolhoclosepasswordcadastro.setVisibility(View.GONE);
        imgolhoopenpasswordcadastro.setVisibility(View.GONE);

        //  When clicked  in this button will go to MainActivity and finish this Activty
        btnvoltaraologin.setOnClickListener(v ->{
            if (edittextcadastronomefunc.getText().length() > 0 || edittextcpfcadastro.getText().length() > 0 || edittextemailcadastro.getText().length() > 0 || edittextenderecocadastro.getText().length() > 0 || edittexttelefonecadastro.getText().length() > 0 || edittextcargocadastro.getText().length() > 0 || edittextsenhacadastro.getText().length() > 0){
                AlertDialog.Builder msgaviso = new AlertDialog.Builder(CriarContaActivity.this);
                msgaviso.setIcon(R.drawable.logosystemstrengthsemfundo);
                msgaviso.setTitle("Aviso");
                msgaviso.setMessage("Você iniciou um cadastro!\nSe você sair dessa tela ira perder seu progresso\nDesejá realmente sair?");
                msgaviso.setPositiveButton("Sim", (dialog, which) -> reduzirtempoeiramain());
                msgaviso.setNegativeButton("Não",null);
                msgaviso.show();
            }
            else{
                reduzirtempoeiramain();
            }
        });

        //  When you click on the open eye it will execute the defined commands
        imgolhoopenpasswordcadastro.setOnClickListener(v -> {
            edittextsenhacadastro.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imgolhoopenpasswordcadastro.setVisibility(View.GONE);
            imgolhoclosepasswordcadastro.setVisibility(View.VISIBLE);
            edittextsenhacadastro.setSelection(edittextsenhacadastro.getText().length());
        });

        //  When you click on the closed eye it will execute the defined commands
        imgolhoclosepasswordcadastro.setOnClickListener(v -> {
            edittextsenhacadastro.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imgolhoopenpasswordcadastro.setVisibility(View.VISIBLE);
            imgolhoclosepasswordcadastro.setVisibility(View.GONE);
            edittextsenhacadastro.setSelection(edittextsenhacadastro.getText().length());
        });

        //  Checking characters in real time edittextcadastronomefunc
        edittextcadastronomefunc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextcadastronomefunc.getText().length() < 8){
                    txtavisonomefunc.setVisibility(View.VISIBLE);
                }
                else {
                    txtavisonomefunc.setVisibility(View.GONE);
                }
            }
        });

        //  Checking characters in real time edittextcadastronomefunc
        edittextcpfcadastro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextcpfcadastro.getText().length() < 14){
                    txtavisocpf.setVisibility(View.VISIBLE);
                    imgcpfrigth.setVisibility(View.GONE);
                }
                else {
                    txtavisocpf.setVisibility(View.GONE);
                    imgcpfrigth.setVisibility(View.VISIBLE);
                }
            }
        });

        //  Checking characters in real time edittextsenhacadastro
        edittextsenhacadastro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextsenhacadastro.getText().length() > 0){
                    imgolhoclosepasswordcadastro.setVisibility(View.VISIBLE);
                }
                if (edittextsenhacadastro.getText().length() == 0){
                    imgolhoclosepasswordcadastro.setVisibility(View.GONE);
                }
                if (edittextsenhacadastro.getText().length() < 8){
                    txtavisosenha.setVisibility(View.VISIBLE);
                    imgsenharigth.setVisibility(View.GONE);
                }
                else {
                    txtavisosenha.setVisibility(View.GONE);
                    imgsenharigth.setVisibility(View.VISIBLE);
                }
            }
        });

        //  Defining commands when clicked in cardviewbtncadastrar
        cardviewbtncadastrar.setOnClickListener(v -> {

            //  Checking characters in edittextnomefunc and defining some commands
            if (edittextcadastronomefunc.getText().length() == 0 || edittextcadastronomefunc.getText().length() < 8){
                // Defining txtavisonomefunc like Visible
                txtavisonomefunc.setVisibility(View.VISIBLE);
                Toast.makeText(CriarContaActivity.this, "Campo NOME preenchido incorretamente!!\nEsta campo é obrigatório", Toast.LENGTH_SHORT).show();
                edittextcadastronomefunc.requestFocus();
                imm.showSoftInput(edittextcadastronomefunc, InputMethodManager.SHOW_IMPLICIT);
            }
            //  Checking characters in edittextcpfcadastro and defining some commands
            else if (edittextcpfcadastro.getText().length() == 0 || edittextcpfcadastro.getText().length() < 14){
                // Defining txtavisocpf like Visible
                txtavisocpf.setVisibility(View.VISIBLE);
                Toast.makeText(CriarContaActivity.this, "Campo CPF preenchido incorretamente!!\nEsta campo é obrigatório", Toast.LENGTH_SHORT).show();
                edittextcpfcadastro.requestFocus();
                imm.showSoftInput(edittextcpfcadastro, InputMethodManager.SHOW_IMPLICIT);
            }
            //  Checking characters in edittextemailcadastro and defining some commands
            else if (edittextemailcadastro.getText().length() == 0|| edittextemailcadastro.getText().length() < 5){
                    Toast.makeText(CriarContaActivity.this, "Campo EMAIL preenchido incorretamente!!\nEsta campo é obrigatório", Toast.LENGTH_SHORT).show();
                    txtavisocpf.setVisibility(View.GONE);
                    edittextemailcadastro.requestFocus();
                    imm.showSoftInput(edittextemailcadastro, InputMethodManager.SHOW_IMPLICIT);
            }
            //  Checking characters in edittextcargocadastro and defining some commands
            else if (edittextcargocadastro.getText().length() == 0 || edittextcargocadastro.getText().length() < 8){
                Toast.makeText(CriarContaActivity.this, "Campo CARGO preenchido incorretamente!!\nEsta campo é obrigatório", Toast.LENGTH_SHORT).show();
                edittextcargocadastro.requestFocus();
                imm.showSoftInput(edittextcargocadastro, InputMethodManager.SHOW_IMPLICIT);
            }
            //  Checking characters in edittextsenhacadastro and defining some commands
            else if (edittextsenhacadastro.getText().length() == 0 || edittextsenhacadastro.getText().length() < 8){
                txtavisosenha.setVisibility(View.VISIBLE);
                Toast.makeText(CriarContaActivity.this, "Campo CARGO preenchido incorretamente!!\nEsta campo é obrigatório", Toast.LENGTH_SHORT).show();
                edittextsenhacadastro.requestFocus();
                imm.showSoftInput(edittextsenhacadastro, InputMethodManager.SHOW_IMPLICIT);
            }
            //  If all edittext is alright will execute some commands
            else {

                DtoLogins dtoLogins = new DtoLogins();
                dtoLogins.setNomefunc(edittextcadastronomefunc.getText().toString());
                dtoLogins.setCpffunc(edittextcpfcadastro.getText().toString());
                dtoLogins.setEmailfunc(edittextemailcadastro.getText().toString());
                dtoLogins.setEnderecofunc(edittextenderecocadastro.getText().toString());
                dtoLogins.setTelefonefunc(edittexttelefonecadastro.getText().toString());
                dtoLogins.setCargofunc(edittextcargocadastro.getText().toString());
                dtoLogins.setSenhafunc(edittextsenhacadastro.getText().toString());
                DaoLogins daoLogins = new DaoLogins(CriarContaActivity.this);
                try {

                    long linhasinseridas = daoLogins.cadastrar(dtoLogins);

                    //  If linhasinseridas return > 0 will saying "Cadastrado com sucesso" and go to main activity
                    if (linhasinseridas > 0){
                        Snackbar.make(v,"Conta criada com sucesso!!\nSejá Bem-Vindo!!", BaseTransientBottomBar.LENGTH_LONG).show();
                        receberloginaposcadastro();
                    }
                    //  If linhasinseridas return < 0 will show msg saying falha
                    else {
                        Toast.makeText(CriarContaActivity.this, "Não foi possivel realizar o cadastro...\nTente novamente mais tarde", Toast.LENGTH_SHORT).show();
                    }
                }
                //  If there is an error in the execution of the code it will capture the error and present
                catch (Exception ex){
                    Toast.makeText(CriarContaActivity.this, "Erro ao cadastar:" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (edittextcadastronomefunc.getText().length() > 0 || edittextcpfcadastro.getText().length() > 0 || edittextemailcadastro.getText().length() > 0 || edittextenderecocadastro.getText().length() > 0 || edittexttelefonecadastro.getText().length() > 0 || edittextcargocadastro.getText().length() > 0 || edittextsenhacadastro.getText().length() > 0){
            AlertDialog.Builder msgaviso = new AlertDialog.Builder(CriarContaActivity.this);
            msgaviso.setIcon(R.drawable.logosystemstrengthsemfundo);
            msgaviso.setTitle("Aviso");
            msgaviso.setMessage("Você iniciou um cadastro!\nSe você sair dessa tela ira perder seu progresso\nDesejá realmente sair?");
            msgaviso.setPositiveButton("Sim", (dialog, which) -> reduzirtempoeiramain());
            msgaviso.setNegativeButton("Não",null);
            msgaviso.show();
        }
        else{
            reduzirtempoeiramain();
        }
    }

    private void reduzirtempoeiramain(){
        Intent voltarmain = new Intent(CriarContaActivity.this,MainActivity.class);
        voltarmain.putExtra("novotempo",novotempodeanimacao);
        startActivity(voltarmain);
        finish();
    }

    private void receberloginaposcadastro(){
        Intent voltarmain = new Intent(CriarContaActivity.this,MainActivity.class);
        voltarmain.putExtra("cpfcadastrado",edittextcpfcadastro.getText().toString());
        voltarmain.putExtra("senhacadastrado",edittextsenhacadastro.getText().toString());
        startActivity(voltarmain);
        finish();
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */