package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

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

import com.example.systemstrength.Classes.Login.DaoLogins;
import com.example.systemstrength.Classes.Login.DtoLogins;

public class GerenciarUserActivity extends AppCompatActivity {
    LinearLayout linearbtnvoltargerenciaruser;
    EditText edittextpesquisarcpfusuariogerenciar, editeditcpfperfilgerenciar, editeditnomeperfilgerenciar, editeditemailperfilgerenciar, editeditenderecoperfilgerenciar, editedittelefoneperfilgerenciar, editeditcargoperfilgerenciar, editeditproximareuniaoperfilgerenciar, editeditsenhaperfilgerenciar;
    TextView txtnomefuncperfilgerenciar, txtcpffuncperfilgerenciar, txtendfuncperfilgerenciar, txtphonefuncperfilgerenciar, txtemailfuncperfilgerenciar, txtcargofuncperfilgerenciar, txtproximareuniaoperfilgerenciar;
    CardView cardviewiniciareditgerenciar, cardviewbtnsalvareditperfilgerenciar, cardviewbtnvoltarcardperfilgerenciar;
    ConstraintLayout constraintcardviewperfilgerenciar, constraintbaseeditperfilgerenciar;
    String cpfdousuario, senhadousuarioasereditada, cpfrecebidodoperfil, cpfrecebidoedit;
    int iddousuarioasereditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_user);
        linearbtnvoltargerenciaruser = findViewById(R.id.linearbtnvoltargerenciaruser);
        //  Ids
        edittextpesquisarcpfusuariogerenciar = findViewById(R.id.edittextpesquisarcpfusuariogerenciar);
        txtnomefuncperfilgerenciar = findViewById(R.id.txtnomefuncperfilgerenciar);
        txtcpffuncperfilgerenciar = findViewById(R.id.txtcpffuncperfilgerenciar);
        txtendfuncperfilgerenciar = findViewById(R.id.txtendfuncperfilgerenciar);
        txtemailfuncperfilgerenciar = findViewById(R.id.txtemailfuncperfilgerenciar);
        txtcargofuncperfilgerenciar = findViewById(R.id.txtcargofuncperfilgerenciar);
        txtproximareuniaoperfilgerenciar = findViewById(R.id.txtproximareuniaoperfilgerenciar);
        txtphonefuncperfilgerenciar = findViewById(R.id.txtphonefuncperfilgerenciar);
        cardviewiniciareditgerenciar = findViewById(R.id.cardviewiniciareditgerenciar);
        constraintcardviewperfilgerenciar = findViewById(R.id.constraintcardviewperfilgerenciar);
        constraintbaseeditperfilgerenciar = findViewById(R.id.constraintbaseeditperfilgerenciar);
        editeditcpfperfilgerenciar = findViewById(R.id.editeditcpfperfilgerenciar);
        editeditnomeperfilgerenciar = findViewById(R.id.editeditnomeperfilgerenciar);
        editeditemailperfilgerenciar = findViewById(R.id.editeditemailperfilgerenciar);
        editeditenderecoperfilgerenciar = findViewById(R.id.editeditenderecoperfilgerenciar);
        editedittelefoneperfilgerenciar = findViewById(R.id.editedittelefoneperfilgerenciar);
        editeditcargoperfilgerenciar = findViewById(R.id.editeditcargoperfilgerenciar);
        editeditproximareuniaoperfilgerenciar = findViewById(R.id.editeditproximareuniaoperfilgerenciar);
        editeditsenhaperfilgerenciar = findViewById(R.id.editeditsenhaperfilgerenciar);
        cardviewbtnsalvareditperfilgerenciar = findViewById(R.id.cardviewbtnsalvareditperfilgerenciar);
        cardviewbtnvoltarcardperfilgerenciar = findViewById(R.id.cardviewbtnvoltarcardperfilgerenciar);

        // Defining mask to editText
        edittextpesquisarcpfusuariogerenciar.addTextChangedListener(MaskEditUtil.mask(edittextpesquisarcpfusuariogerenciar, MaskEditUtil.FORMAT_CPF));
        editeditcpfperfilgerenciar.addTextChangedListener(MaskEditUtil.mask(editeditcpfperfilgerenciar, MaskEditUtil.FORMAT_CPF));
        editedittelefoneperfilgerenciar.addTextChangedListener(MaskEditUtil.mask(editedittelefoneperfilgerenciar, MaskEditUtil.FORMAT_FONE));
        editeditproximareuniaoperfilgerenciar.addTextChangedListener(MaskEditUtil.mask(editeditproximareuniaoperfilgerenciar, MaskEditUtil.FORMAT_DATETIME));

        //  Defining msg Alert
        //  AlertDialog.Builder msg = new AlertDialog.Builder(GerenciarUserActivity.this);

        //  Defining with gone some Thing
        cardviewiniciareditgerenciar.setVisibility(View.GONE);

        //  Get information of another screen
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodoperfil = bundle.getString("cpfuseradm");

        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //  Set commands for do in real time when insert some inside edittext
        edittextpesquisarcpfusuariogerenciar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextpesquisarcpfusuariogerenciar.getText().length() == 14){
                    cpfdousuario = edittextpesquisarcpfusuariogerenciar.getText().toString();
                    DaoLogins daoLogins = new DaoLogins(GerenciarUserActivity.this);
                    DtoLogins dtousuariorecebido = daoLogins.verificarusuario(cpfdousuario);
                    iddousuarioasereditado = dtousuariorecebido.getId();
                    txtnomefuncperfilgerenciar.setText(dtousuariorecebido.getNomefunc());
                    cpfrecebidoedit = dtousuariorecebido.getCpffunc();
                    txtcpffuncperfilgerenciar.setText(cpfrecebidoedit);
                    txtendfuncperfilgerenciar.setText(dtousuariorecebido.getEnderecofunc());
                    txtphonefuncperfilgerenciar.setText(dtousuariorecebido.getTelefonefunc());
                    txtemailfuncperfilgerenciar.setText(dtousuariorecebido.getEmailfunc());
                    txtcargofuncperfilgerenciar.setText(dtousuariorecebido.getCargofunc());
                    txtproximareuniaoperfilgerenciar.setText(dtousuariorecebido.getUltamareufunc());
                    senhadousuarioasereditada = dtousuariorecebido.getSenhafunc();
                    cardviewiniciareditgerenciar.setVisibility(View.VISIBLE);
                }
                else {
                    txtnomefuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtcpffuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtendfuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtphonefuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtemailfuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtcargofuncperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    txtproximareuniaoperfilgerenciar.setText(String.format("%s...", edittextpesquisarcpfusuariogerenciar.getText()));
                    cardviewiniciareditgerenciar.setVisibility(View.GONE);
                }
            }
        });

        //  When click in this Cardview will defining text for Edittext and show ConstraintLayout
        cardviewiniciareditgerenciar.setOnClickListener(v -> {
            constraintcardviewperfilgerenciar.setVisibility(View.GONE);
            constraintbaseeditperfilgerenciar.setVisibility(View.VISIBLE);
            editeditcpfperfilgerenciar.setText(txtcpffuncperfilgerenciar.getText());
            editeditnomeperfilgerenciar.setText(txtnomefuncperfilgerenciar.getText());
            editeditemailperfilgerenciar.setText(txtemailfuncperfilgerenciar.getText());
            editeditenderecoperfilgerenciar.setText(txtendfuncperfilgerenciar.getText());
            editedittelefoneperfilgerenciar.setText(txtphonefuncperfilgerenciar.getText());
            editeditcargoperfilgerenciar.setText(txtcargofuncperfilgerenciar.getText());
            editeditproximareuniaoperfilgerenciar.setText(txtproximareuniaoperfilgerenciar.getText());
            editeditsenhaperfilgerenciar.setText(senhadousuarioasereditada);
        });

        //  When click in this Cardview will return for first cardview ConstraintLayout
        cardviewbtnvoltarcardperfilgerenciar.setOnClickListener(v -> {
            constraintcardviewperfilgerenciar.setVisibility(View.VISIBLE);
            constraintbaseeditperfilgerenciar.setVisibility(View.GONE);
            cardviewiniciareditgerenciar.setVisibility(View.VISIBLE);
        });

        //  When CLick in this cardview will do some validation and try edit user
        cardviewbtnsalvareditperfilgerenciar.setOnClickListener(v -> {
            if (editeditcpfperfilgerenciar.getText().length() < 14 || editeditcpfperfilgerenciar.getText().length() == 0){
                Toast.makeText(GerenciarUserActivity.this, "Preencha corretamente o campo: CPF", Toast.LENGTH_SHORT).show();
                editeditcpfperfilgerenciar.requestFocus();
                imm.showSoftInput(editeditcpfperfilgerenciar, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditnomeperfilgerenciar.getText().length() < 8 || editeditnomeperfilgerenciar.getText().length() == 0){
                Toast.makeText(GerenciarUserActivity.this, "Preencha corretamente o campo: NOME", Toast.LENGTH_SHORT).show();
                editeditnomeperfilgerenciar.requestFocus();
                imm.showSoftInput(editeditnomeperfilgerenciar, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditemailperfilgerenciar.getText().length() < 5 || editeditemailperfilgerenciar.getText().length() == 0){
                Toast.makeText(GerenciarUserActivity.this, "Preencha corretamente o campo: EMAIL", Toast.LENGTH_SHORT).show();
                editeditemailperfilgerenciar.requestFocus();
                imm.showSoftInput(editeditemailperfilgerenciar, InputMethodManager.SHOW_IMPLICIT);
            }
            else if (editeditcargoperfilgerenciar.getText().length() < 8 || editeditcargoperfilgerenciar.getText().length() == 0){
                Toast.makeText(GerenciarUserActivity.this, "Preencha corretamente o campo: CARGO", Toast.LENGTH_SHORT).show();
                editeditcargoperfilgerenciar.requestFocus();
                imm.showSoftInput(editeditcargoperfilgerenciar, InputMethodManager.SHOW_IMPLICIT);
            }else if (editeditsenhaperfilgerenciar.getText().length() == 0 || editeditsenhaperfilgerenciar.getText().length() < 8){
                Toast.makeText(GerenciarUserActivity.this, "Preencha corretamente o campo: SENHA", Toast.LENGTH_SHORT).show();
                editeditsenhaperfilgerenciar.requestFocus();
                imm.showSoftInput(editeditsenhaperfilgerenciar, InputMethodManager.SHOW_IMPLICIT);
            } else {
                try {
                    DtoLogins  dtoLoginsalterar=  new DtoLogins();
                    DaoLogins daologinsatualizar = new DaoLogins(GerenciarUserActivity.this);
                    dtoLoginsalterar.setId(iddousuarioasereditado);
                    dtoLoginsalterar.setCpffunc(editeditcpfperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setNomefunc(editeditnomeperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setEmailfunc(editeditemailperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setEnderecofunc(editeditenderecoperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setTelefonefunc(editedittelefoneperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setCargofunc(editeditcargoperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setUltamareufunc(editeditproximareuniaoperfilgerenciar.getText().toString());
                    dtoLoginsalterar.setSenhafunc(editeditsenhaperfilgerenciar.getText().toString());
                    long linhasalteradas = daologinsatualizar.atualizaradm(dtoLoginsalterar);
                    if (linhasalteradas > 0){
                        Toast.makeText(this, "Usuário Atualizado com sucesso!!", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(() -> {
                            Intent voltarparaperfil = new Intent(GerenciarUserActivity.this, PerfilUsuarioActivity.class);
                            voltarparaperfil.putExtra("cpfusu", cpfrecebidodoperfil);
                            startActivity(voltarparaperfil);
                            finish();
                        },200);
                    }else{
                        Toast.makeText(GerenciarUserActivity.this, "Falha ao atualizar...", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(GerenciarUserActivity.this, "Erro ao atualizar: " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //  When CLick in this Linear will close that Activity and go to PerfilUsuarioActivity
        linearbtnvoltargerenciaruser.setOnClickListener(v -> {
            Intent voltarparaperfil = new Intent(GerenciarUserActivity.this, PerfilUsuarioActivity.class);
            voltarparaperfil.putExtra("cpfusu", cpfrecebidodoperfil);
            startActivity(voltarparaperfil);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent voltarparaperfil = new Intent(GerenciarUserActivity.this, PerfilUsuarioActivity.class);
        voltarparaperfil.putExtra("cpfusu", cpfrecebidodoperfil);
        startActivity(voltarparaperfil);
        finish();
    }
}