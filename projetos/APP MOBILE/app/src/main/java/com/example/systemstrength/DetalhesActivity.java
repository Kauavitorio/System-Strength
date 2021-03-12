package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

public class DetalhesActivity extends AppCompatActivity {
    TextView txtsalvarbtncliente, txtnomecliente, txtendcliente, txtphonecliente, txtemailcliente, txtcnpcliente, txttitulonomeeditcliente, txttituloendeditcliente, txttitulotelefoneeditcliente, txttituloemaileditcliente, txttitulocnpjeditcliente;
    EditText edittextnomecliente, edittextenderecocliente, edittexttelefonecliente, edittextemailcliente, edittextcnpjcliente;
    LinearLayout linearvoltaraoclientesdetalhes, linearbtnstarteditcliente;
    LottieAnimationView setinhaanimadadetahles, animacaorigth, animacaobolinha, animacaoloaginerro;
    CardView cardviewbtnsalvaraltercliente;
    String cnpjrecebidodocliente, cpfusuariorecebidodosclientes;
    int idclienteparaatualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        //  Ids
        txtnomecliente = findViewById(R.id.txtnomecliente);
        txtendcliente = findViewById(R.id.txtendcliente);
        txtphonecliente = findViewById(R.id.txtphonecliente);
        txtemailcliente = findViewById(R.id.txtemailcliente);
        txtcnpcliente = findViewById(R.id.txtcnpcliente);
        txttitulonomeeditcliente = findViewById(R.id.txttitulonomeeditcliente);
        txttitulotelefoneeditcliente = findViewById(R.id.txttitulotelefoneeditcliente);
        txtsalvarbtncliente = findViewById(R.id.txtsalvarbtncliente);
        txttitulocnpjeditcliente = findViewById(R.id.txttitulocnpjeditcliente);
        txttituloemaileditcliente = findViewById(R.id.txttituloemaileditcliente);
        txttituloendeditcliente = findViewById(R.id.txttituloendeditcliente);
        edittextnomecliente = findViewById(R.id.edittextnomecliente);
        edittextcnpjcliente = findViewById(R.id.edittextcnpjcliente);
        edittextemailcliente = findViewById(R.id.edittextemailcliente);
        edittextenderecocliente = findViewById(R.id.edittextenderecocliente);
        edittexttelefonecliente = findViewById(R.id.edittexttelefonecliente);
        linearvoltaraoclientesdetalhes = findViewById(R.id.linearvoltaraoclientesdetalhes);
        linearbtnstarteditcliente = findViewById(R.id.linearbtnstarteditcliente);
        setinhaanimadadetahles = findViewById(R.id.setinhaanimadadetahles);
        cardviewbtnsalvaraltercliente = findViewById(R.id.cardviewbtnsalvaraltercliente);
        animacaorigth = findViewById(R.id.animacaorigth);
        animacaobolinha = findViewById(R.id.animacaobolinha);
        animacaoloaginerro = findViewById(R.id.animacaoloaginerro);
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //  Set mask for edittext
        edittextcnpjcliente.addTextChangedListener(MaskEditUtil.mask(edittextcnpjcliente, MaskEditUtil.FORMAT_CNPJ));
        edittexttelefonecliente.addTextChangedListener(MaskEditUtil.mask(edittexttelefonecliente, MaskEditUtil.FORMAT_FONE));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cnpjrecebidodocliente = bundle.getString("cnjdaempresa");
        cpfusuariorecebidodosclientes = bundle.getString("cpfusu");
        DaoClientes daoClientes = new DaoClientes(DetalhesActivity.this);
        DtoClientes dtoClientes = daoClientes.consultarcliente(cnpjrecebidodocliente);
        idclienteparaatualizar = dtoClientes.getId();
        txtnomecliente.setText(dtoClientes.getNomecliente());
        txtendcliente.setText(dtoClientes.getEnderecocliente());
        txtphonecliente.setText(dtoClientes.getTelefonecliente());
        txtemailcliente.setText(dtoClientes.getEmailcliente());
        txtcnpcliente.setText(dtoClientes.getCnpjcliente());

        //  When click in this card will verification in edittext and save changes
        cardviewbtnsalvaraltercliente.setOnClickListener(v -> {
            if (edittextnomecliente.getText().length() < 5 || edittextnomecliente.getText().length() == 0){
                Toast.makeText(DetalhesActivity.this, "Campo NOME preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextnomecliente.requestFocus();
                imm.showSoftInput(edittextnomecliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextenderecocliente.getText().length() < 5 || edittextenderecocliente.getText().length() == 0){
                Toast.makeText(DetalhesActivity.this, "Campo ENDEREÇO preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextenderecocliente.requestFocus();
                imm.showSoftInput(edittextenderecocliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittexttelefonecliente.getText().length() < 15 || edittexttelefonecliente.getText().length() == 0){
                Toast.makeText(DetalhesActivity.this, "Campo TELEFONE preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittexttelefonecliente.requestFocus();
                imm.showSoftInput(edittexttelefonecliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextemailcliente.getText().length() < 5 || edittextemailcliente.getText().length() == 0){
                Toast.makeText(DetalhesActivity.this, "Campo EMAIL preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextemailcliente.requestFocus();
                imm.showSoftInput(edittextemailcliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextcnpjcliente.getText().length() < 18 || edittextcnpjcliente.getText().length() == 0){
                Toast.makeText(DetalhesActivity.this, "Campo CNPJ preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextcnpjcliente.requestFocus();
                imm.showSoftInput(edittextcnpjcliente, InputMethodManager.SHOW_IMPLICIT);
            }else {
                try {
                    animacaobolinha.setVisibility(View.VISIBLE);
                    animacaorigth.playAnimation();
                    txtsalvarbtncliente.setVisibility(View.GONE);
                    new Handler().postDelayed(()-> {
                        DtoClientes dtoClientesatualizar = new DtoClientes();
                        DaoClientes daoClientesatualizar = new DaoClientes(DetalhesActivity.this);
                        dtoClientesatualizar.setId(idclienteparaatualizar);
                        dtoClientesatualizar.setNomecliente(edittextnomecliente.getText().toString());
                        dtoClientesatualizar.setEnderecocliente(edittextenderecocliente.getText().toString());
                        dtoClientesatualizar.setTelefonecliente(edittexttelefonecliente.getText().toString());
                        dtoClientesatualizar.setEmailcliente(edittextemailcliente.getText().toString());
                        dtoClientesatualizar.setCnpjcliente(edittextcnpjcliente.getText().toString());
                        long linhasinseridas = daoClientesatualizar.atualizar(dtoClientesatualizar);
                        if (linhasinseridas > 0){
                            animacaobolinha.setVisibility(View.GONE);
                            animacaorigth.pauseAnimation();
                            animacaorigth.setVisibility(View.VISIBLE);
                            animacaorigth.playAnimation();
                            new Handler().postDelayed(() -> {
                                Toast.makeText(this, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
                                Intent voltaraosclientes = new Intent(DetalhesActivity.this,ClientesActivity.class);
                                voltaraosclientes.putExtra("cpfusu",cpfusuariorecebidodosclientes);
                                startActivity(voltaraosclientes);
                                finish();
                            },2000);
                        }else{
                            animacaobolinha.setVisibility(View.GONE);
                            animacaorigth.pauseAnimation();
                            animacaoloaginerro.setVisibility(View.VISIBLE);
                            animacaoloaginerro.playAnimation();
                            txtsalvarbtncliente.setVisibility(View.VISIBLE);
                            Toast.makeText(this, "Falha ao atualizar\nContate um gerente!", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }catch (Exception ex){
                    Toast.makeText(this, "Erro ao atualizar: " + ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //  When click in this linear will enable change for client
        linearbtnstarteditcliente.setOnClickListener(v -> {
            linearbtnstarteditcliente.setVisibility(View.GONE);
            txttitulonomeeditcliente.setVisibility(View.VISIBLE);
            edittextnomecliente.setVisibility(View.VISIBLE);
            txttituloendeditcliente.setVisibility(View.VISIBLE);
            edittextenderecocliente.setVisibility(View.VISIBLE);
            txttitulotelefoneeditcliente.setVisibility(View.VISIBLE);
            edittexttelefonecliente.setVisibility(View.VISIBLE);
            txttituloemaileditcliente.setVisibility(View.VISIBLE);
            edittextemailcliente.setVisibility(View.VISIBLE);
            txttitulocnpjeditcliente.setVisibility(View.VISIBLE);
            edittextcnpjcliente.setVisibility(View.VISIBLE);
            cardviewbtnsalvaraltercliente.setVisibility(View.VISIBLE);


            edittextnomecliente.setText(txtnomecliente.getText());
            edittextenderecocliente.setText(txtendcliente.getText());
            edittexttelefonecliente.setText(txtphonecliente.getText());
            edittextemailcliente.setText(txtemailcliente.getText());
            edittextcnpjcliente.setText(txtcnpcliente.getText());

        });

        //  When Click in this linear go to Clientes and send cpf of client
        linearvoltaraoclientesdetalhes.setOnClickListener(v -> {
            setinhaanimadadetahles.playAnimation();
            new Handler().postDelayed(() -> {
                Intent voltaraosclientes = new Intent(DetalhesActivity.this,ClientesActivity.class);
                voltaraosclientes.putExtra("cpfusu",cpfusuariorecebidodosclientes);
                startActivity(voltaraosclientes);
                finish();
            },800);
        });

        //  When you edit name client in edittext will show in real time in textview
        edittextnomecliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (edittextnomecliente.getText().length() > 0){
                    txtnomecliente.setText(edittextnomecliente.getText());
                }else {
                    txtnomecliente.setText("Insira o nome do cliente");
                }
            }
        });

        //  When you edit address client in edittext will show in real time in textview
        edittextenderecocliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (edittextenderecocliente.getText().length() > 0){
                    txtendcliente.setText(edittextenderecocliente.getText());
                }else   txtendcliente.setText("Insira o endereço do cliente");
            }
        });

        //  When you edit phone client in edittext will show in real time in textview
        edittexttelefonecliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (edittexttelefonecliente.getText().length() > 0){
                    txtphonecliente.setText(edittexttelefonecliente.getText());
                }else txtphonecliente.setText("Insira o telefone do cliente");
            }
        });

        //  When you edit email client in edittext will show in real time in textview
        edittextemailcliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (edittextemailcliente.getText().length() > 0){
                    txtemailcliente.setText(edittextemailcliente.getText());
                }else txtemailcliente.setText("Insira o email do cliente");

            }
        });

        //  When you edit CNPJ client in edittext will show in real time in textview
        edittextcnpjcliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if (edittextcnpjcliente.getText().length() > 0){
                    txtcnpcliente.setText(edittextcnpjcliente.getText());
                }else txtcnpcliente.setText("Insira o CNPJ do cliente");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent voltaraosclientes = new Intent(DetalhesActivity.this,ClientesActivity.class);
        voltaraosclientes.putExtra("cpfusu",cpfusuariorecebidodosclientes);
        startActivity(voltaraosclientes);
        finish();
    }
}