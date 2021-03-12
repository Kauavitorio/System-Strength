package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Classes.Login.DaoLogins;
import com.example.systemstrength.Classes.Login.DtoLogins;

public class PerfilUsuarioActivity extends AppCompatActivity {
    LinearLayout linearbtnvoltarperfil;
    TextView txtnomefuncperfil, txtcpffuncperfil, txtendfuncperfil, txtphonefuncperfil, txtemailfuncperfil, txtcargofuncperfil, txtproximareuniaoperfil;
    String cpfrecebidoprincipal;
    LottieAnimationView setinhaanimadaperfil;
    ConstraintLayout constraintbasecardperfil, constraintbaseeditperfil;
    CardView cardviewbtniniciaredit, cardviewbtnsalvareditperfil, cardviewbtnvoltarcardperfil, cardviewadmperfil;
    EditText editeditcpfperfil, editeditnomeperfil, editeditemailperfil, editeditenderecoperfil, editedittelefoneperfil, editeditcargoperfil, editeditproximareuniaoperfil;
    int idparaalterar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        //  Ids
        linearbtnvoltarperfil = findViewById(R.id.linearbtnvoltarperfil);
        txtnomefuncperfil = findViewById(R.id.txtnomefuncperfil);
        txtcpffuncperfil = findViewById(R.id.txtcpffuncperfil);
        txtendfuncperfil = findViewById(R.id.txtendfuncperfil);
        txtphonefuncperfil = findViewById(R.id.txtphonefuncperfil);
        txtemailfuncperfil = findViewById(R.id.txtemailfuncperfil);
        txtcargofuncperfil = findViewById(R.id.txtcargofuncperfil);
        setinhaanimadaperfil = findViewById(R.id.setinhaanimadaperfil);
        txtproximareuniaoperfil = findViewById(R.id.txtproximareuniaoperfil);
        cardviewbtniniciaredit = findViewById(R.id.cardviewbtniniciaredit);
        cardviewbtnvoltarcardperfil = findViewById(R.id.cardviewbtnvoltarcardperfil);
        cardviewbtnsalvareditperfil = findViewById(R.id.cardviewbtnsalvareditperfil);
        constraintbasecardperfil = findViewById(R.id.constraintbasecardperfil);
        constraintbaseeditperfil = findViewById(R.id.constraintbaseeditperfil);
        editeditcpfperfil = findViewById(R.id.editeditcpfperfil);
        editeditnomeperfil = findViewById(R.id.editeditnomeperfil);
        editeditemailperfil = findViewById(R.id.editeditemailperfil);
        editeditenderecoperfil = findViewById(R.id.editeditenderecoperfil);
        editedittelefoneperfil = findViewById(R.id.editedittelefoneperfil);
        editeditcargoperfil = findViewById(R.id.editeditcargoperfil);
        cardviewadmperfil = findViewById(R.id.cardviewadmperfil);
        editeditproximareuniaoperfil = findViewById(R.id.editeditproximareuniaoperfil);
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //  Defining with gone some Thing
        cardviewadmperfil.setVisibility(View.GONE);

        // Defining mask to editText
        editeditcpfperfil.addTextChangedListener(MaskEditUtil.mask(editeditcpfperfil, MaskEditUtil.FORMAT_CPF));
        editedittelefoneperfil.addTextChangedListener(MaskEditUtil.mask(editedittelefoneperfil, MaskEditUtil.FORMAT_FONE));
        editeditproximareuniaoperfil.addTextChangedListener(MaskEditUtil.mask(editeditproximareuniaoperfil, MaskEditUtil.FORMAT_DATETIME));

        //  Get information of another screen
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidoprincipal = bundle.getString("cpfusu");
        DaoLogins daoLogins = new DaoLogins(PerfilUsuarioActivity.this);
        DtoLogins dtoLogins = daoLogins.verificarusuario(cpfrecebidoprincipal);
        txtnomefuncperfil.setText(dtoLogins.getNomefunc());
        txtcargofuncperfil.setText(dtoLogins.getCargofunc());
        txtcpffuncperfil.setText(dtoLogins.getCpffunc());
        txtemailfuncperfil.setText(dtoLogins.getEmailfunc());
        idparaalterar = dtoLogins.getId();
        if (dtoLogins.getCpffunc().equals("433.333.498-50") || dtoLogins.getCpffunc().equals("508.204.608-11")){
            cardviewadmperfil.setVisibility(View.VISIBLE);
        } else {
            cardviewadmperfil.setVisibility(View.GONE);
        }
        if (dtoLogins.getUltamareufunc() == null || dtoLogins.getUltamareufunc().equals("")){
            txtproximareuniaoperfil.setText("Não há reunião agendada!");
        }else {
            txtproximareuniaoperfil.setText(dtoLogins.getUltamareufunc());
        }
        if (dtoLogins.getTelefonefunc() == null || dtoLogins.getTelefonefunc().equals("")){
            txtphonefuncperfil.setText("Telefone não cadastrado!");
        }else {
            txtphonefuncperfil.setText(dtoLogins.getTelefonefunc());
        }
        if (dtoLogins.getEnderecofunc() == null || dtoLogins.getEnderecofunc().equals("")){
            txtendfuncperfil.setText("Endereço não cadastrado!");
        }else {
            txtendfuncperfil.setText(dtoLogins.getEnderecofunc());
        }

        cardviewbtniniciaredit.setOnClickListener(v ->
        {
            DaoLogins daoLoginsenviaredit = new DaoLogins(PerfilUsuarioActivity.this);
            DtoLogins dtoLoginsenviaredit = daoLoginsenviaredit.verificarusuario(cpfrecebidoprincipal);
            constraintbasecardperfil.setVisibility(View.GONE);
            constraintbaseeditperfil.setVisibility(View.VISIBLE);
            idparaalterar = dtoLoginsenviaredit.getId();
            editeditcpfperfil.setText(dtoLoginsenviaredit.getCpffunc());
            editeditnomeperfil.setText(dtoLoginsenviaredit.getNomefunc());
            editeditemailperfil.setText(dtoLoginsenviaredit.getEmailfunc());
            if (dtoLoginsenviaredit.getEnderecofunc() == null || dtoLoginsenviaredit.getEnderecofunc().equals("")){
                editeditenderecoperfil.setText(null);
            }else{
                editeditenderecoperfil.setText(dtoLoginsenviaredit.getEnderecofunc());
            }
            if (dtoLogins.getTelefonefunc() == null || dtoLoginsenviaredit.getTelefonefunc().equals("")){
                editedittelefoneperfil.setText(null);
            }else{
                editedittelefoneperfil.setText(dtoLoginsenviaredit.getTelefonefunc());
            }
            editeditcargoperfil.setText(dtoLoginsenviaredit.getCargofunc());
            if (dtoLogins.getUltamareufunc() == null || dtoLoginsenviaredit.getUltamareufunc().equals("")){
                editeditproximareuniaoperfil.setText(null);
            }else{
                editeditproximareuniaoperfil.setText(dtoLoginsenviaredit.getUltamareufunc());
            }
        });

        cardviewbtnvoltarcardperfil.setOnClickListener(v -> {
            constraintbasecardperfil.setVisibility(View.VISIBLE);
            constraintbaseeditperfil.setVisibility(View.GONE);
            limparedit();
        });

        cardviewbtnsalvareditperfil.setOnClickListener(v -> {
            if (editeditcpfperfil.getText().length() < 14 || editeditcpfperfil.getText().length() == 0){
                Toast.makeText(PerfilUsuarioActivity.this, "Obrigatório preencher  o campo: CPF", Toast.LENGTH_SHORT).show();
                editeditcpfperfil.requestFocus();
                imm.showSoftInput(editeditcpfperfil, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditnomeperfil.getText().length() < 0 || editeditnomeperfil.getText().length() == 0){
                Toast.makeText(PerfilUsuarioActivity.this, "Obrigatório preencher  o campo: NOME", Toast.LENGTH_SHORT).show();
                editeditnomeperfil.requestFocus();
                imm.showSoftInput(editeditnomeperfil, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditemailperfil.getText().length() < 0 || editeditemailperfil.getText().length() == 0){
                Toast.makeText(PerfilUsuarioActivity.this, "Obrigatório preencher  o campo: EMAIL", Toast.LENGTH_SHORT).show();
                editeditemailperfil.requestFocus();
                imm.showSoftInput(editeditemailperfil, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditcargoperfil.getText().length() < 0 || editeditcargoperfil.getText().length() == 0){
                Toast.makeText(PerfilUsuarioActivity.this, "Obrigatório preencher  o campo: CARGO", Toast.LENGTH_SHORT).show();
                editeditcargoperfil.requestFocus();
                imm.showSoftInput(editeditcargoperfil, InputMethodManager.SHOW_IMPLICIT);
            }else {
                try {
                    DtoLogins  dtoLoginsalterar=  new DtoLogins();
                    DaoLogins daologinsatualizar = new DaoLogins(PerfilUsuarioActivity.this);
                    dtoLoginsalterar.setId(idparaalterar);
                    dtoLoginsalterar.setCpffunc(editeditcpfperfil.getText().toString());
                    dtoLoginsalterar.setNomefunc(editeditnomeperfil.getText().toString());
                    dtoLoginsalterar.setEmailfunc(editeditemailperfil.getText().toString());
                    dtoLoginsalterar.setEnderecofunc(editeditenderecoperfil.getText().toString());
                    dtoLoginsalterar.setTelefonefunc(editedittelefoneperfil.getText().toString());
                    dtoLoginsalterar.setCargofunc(editeditcargoperfil.getText().toString());
                    dtoLoginsalterar.setUltamareufunc(editeditproximareuniaoperfil.getText().toString());
                    long linhasalteradas = daologinsatualizar.atualizar(dtoLoginsalterar);
                    if (linhasalteradas > 0){
                        cpfrecebidoprincipal = editeditcpfperfil.getText().toString();
                        Toast.makeText(PerfilUsuarioActivity.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        constraintbasecardperfil.setVisibility(View.VISIBLE);
                        constraintbaseeditperfil.setVisibility(View.GONE);
                        limparedit();
                        DaoLogins daoLogins1 = new DaoLogins(PerfilUsuarioActivity.this);
                        DtoLogins dtoLogins1 = daoLogins1.verificarusuario(cpfrecebidoprincipal);
                        txtnomefuncperfil.setText(dtoLogins1.getNomefunc());
                        txtcargofuncperfil.setText(dtoLogins1.getCargofunc());
                        txtcpffuncperfil.setText(dtoLogins1.getCpffunc());
                        txtemailfuncperfil.setText(dtoLogins1.getEmailfunc());
                        if (dtoLogins1.getUltamareufunc() == null || dtoLogins1.getUltamareufunc().equals("")){
                            txtproximareuniaoperfil.setText("Não há reunião agendada!");
                        }else {
                            txtproximareuniaoperfil.setText(dtoLogins1.getUltamareufunc());
                        }
                        if (dtoLogins1.getTelefonefunc() == null || dtoLogins1.getTelefonefunc().equals("")){
                            txtphonefuncperfil.setText("Telefone não cadastrado!");
                        }else {
                            txtphonefuncperfil.setText(dtoLogins1.getTelefonefunc());
                        }
                        if (dtoLogins1.getEnderecofunc() == null || dtoLogins1.getEnderecofunc().equals("")){
                            txtendfuncperfil.setText("Endereço não cadastrado!");
                        }else {
                            txtendfuncperfil.setText(dtoLogins1.getEnderecofunc());
                        }
                    }
                    else{
                        Toast.makeText(PerfilUsuarioActivity.this, "Falha ao atualizar...", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(PerfilUsuarioActivity.this, "Erro ao atualizar: " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        linearbtnvoltarperfil.setOnClickListener(v -> {
            setinhaanimadaperfil.playAnimation();
            new Handler().postDelayed(() -> {
                Intent voltarparaprincipal = new Intent(PerfilUsuarioActivity.this, PrincipalActivity.class);
                voltarparaprincipal.putExtra("cpfusu",cpfrecebidoprincipal);
                startActivity(voltarparaprincipal);
                finish();
            },900);
        });

        cardviewadmperfil.setOnClickListener(v -> {
            Intent irparagerenciamento = new Intent(PerfilUsuarioActivity.this,GerenciarUserActivity.class);
            irparagerenciamento.putExtra("cpfuseradm",cpfrecebidoprincipal);
            startActivity(irparagerenciamento);
            finish();
        });
    }

    public void limparedit(){
        editeditcpfperfil.setText(null);
        editeditnomeperfil.setText(null);
        editeditemailperfil.setText(null);
        editeditenderecoperfil.setText(null);
        editedittelefoneperfil.setText(null);
        editeditcargoperfil.setText(null);
        editeditproximareuniaoperfil.setText(null);
    }

    @Override
    public void onBackPressed() {
        Intent voltarparaprincipal = new Intent(PerfilUsuarioActivity.this, PrincipalActivity.class);
        voltarparaprincipal.putExtra("cpfusu",cpfrecebidoprincipal);
        startActivity(voltarparaprincipal);
        finish();
    }
}