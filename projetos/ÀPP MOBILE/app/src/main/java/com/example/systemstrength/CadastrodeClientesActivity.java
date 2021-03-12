package com.example.systemstrength;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

public class CadastrodeClientesActivity extends AppCompatActivity {
    LinearLayout linearvoltaraoclientes;
    LottieAnimationView setinhaanimada,  checkedanimation2, checkedanimation1, animacaoerro;
    CardView cardviewbtnsalvarnovocliente;
    TextView txtcadastrarbtnsalvar;
    EditText edittextnomedocliente, edittextenddocliente, edittextphonedocliente, edittextemaildocliente, edittextcnpjdocliente;
    String cpfrecebidodosclientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrode_clientes);
        linearvoltaraoclientes = findViewById(R.id.linearvoltaraoclientes);
        setinhaanimada = findViewById(R.id.setinhaanimada);
        checkedanimation2 = findViewById(R.id.checkedanimation2);
        checkedanimation1 = findViewById(R.id.checkedanimation1);
        animacaoerro = findViewById(R.id.animacaoerro);
        txtcadastrarbtnsalvar = findViewById(R.id.txtcadastrarbtnsalvar);
        cardviewbtnsalvarnovocliente = findViewById(R.id.cardviewbtnsalvarnovocliente);
        edittextnomedocliente = findViewById(R.id.edittextnomedocliente);
        edittextenddocliente = findViewById(R.id.edittextenddocliente);
        edittextphonedocliente = findViewById(R.id.edittextphonedocliente);
        edittextemaildocliente = findViewById(R.id.edittextemaildocliente);
        edittextcnpjdocliente = findViewById(R.id.edittextcnpjdocliente);
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        edittextcnpjdocliente.addTextChangedListener(MaskEditUtil.mask(edittextcnpjdocliente, MaskEditUtil.FORMAT_CNPJ));
        edittextphonedocliente.addTextChangedListener(MaskEditUtil.mask(edittextphonedocliente, MaskEditUtil.FORMAT_FONE));


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodosclientes = bundle.getString("cpfusu");

        cardviewbtnsalvarnovocliente.setOnClickListener(v -> {
            if (edittextnomedocliente.getText().length() < 5 || edittextnomedocliente.getText().length() == 0){
                Toast.makeText(CadastrodeClientesActivity.this, "Campo NOME preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextnomedocliente.requestFocus();
                imm.showSoftInput(edittextnomedocliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextenddocliente.getText().length() < 5 || edittextenddocliente.getText().length() == 0){
                Toast.makeText(CadastrodeClientesActivity.this, "Campo ENDEREÇO preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextenddocliente.requestFocus();
                imm.showSoftInput(edittextenddocliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextphonedocliente.getText().length() < 15 || edittextphonedocliente.getText().length() == 0){
                Toast.makeText(CadastrodeClientesActivity.this, "Campo TELEFONE preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextphonedocliente.requestFocus();
                imm.showSoftInput(edittextphonedocliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextemaildocliente.getText().length() < 5 || edittextemaildocliente.getText().length() == 0){
                Toast.makeText(CadastrodeClientesActivity.this, "Campo EMAIL preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextemaildocliente.requestFocus();
                imm.showSoftInput(edittextemaildocliente, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextcnpjdocliente.getText().length() < 18 || edittextcnpjdocliente.getText().length() == 0){
                Toast.makeText(CadastrodeClientesActivity.this, "Campo CNPJ preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextcnpjdocliente.requestFocus();
                imm.showSoftInput(edittextcnpjdocliente, InputMethodManager.SHOW_IMPLICIT);
            }else {
                try {
                    DtoClientes dtoClientes = new DtoClientes();
                    DaoClientes daoClientes = new DaoClientes(CadastrodeClientesActivity.this);
                    dtoClientes.setNomecliente(edittextnomedocliente.getText().toString());
                    dtoClientes.setEnderecocliente(edittextenddocliente.getText().toString());
                    dtoClientes.setTelefonecliente(edittextphonedocliente.getText().toString());
                    dtoClientes.setEmailcliente(edittextemaildocliente.getText().toString());
                    dtoClientes.setCnpjcliente(edittextcnpjdocliente.getText().toString());
                    long linhasinseridas = daoClientes.cadastrar(dtoClientes);
                    if (linhasinseridas > 0){
                        animacaoloadingcadastro();
                        new Handler().postDelayed(() -> {
                            checkedanimation1.setVisibility(View.GONE);
                            checkedanimation2.setVisibility(View.VISIBLE);
                            checkedanimation2.playAnimation();
                            new Handler().postDelayed(() -> {
                                Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent voltaraosclientes = new Intent(CadastrodeClientesActivity.this, ClientesActivity.class);
                                voltaraosclientes.putExtra("cpfusu",cpfrecebidodosclientes);
                                startActivity(voltaraosclientes);
                                finish();
                            },2000);
                        },1000);
                    }
                    else {
                        animacaoloadingcadastro();
                        new Handler().postDelayed(() -> {
                            checkedanimation1.setVisibility(View.GONE);
                            animacaoerro.setVisibility(View.VISIBLE);
                            animacaoerro.playAnimation();
                            Toast.makeText(this, "Erro ao cadastrar!\nContate um gerente", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {
                                Intent voltaraosclientes = new Intent(CadastrodeClientesActivity.this, ClientesActivity.class);
                                voltaraosclientes.putExtra("cpfusu",cpfrecebidodosclientes);
                                startActivity(voltaraosclientes);
                                finish();
                            },2000);
                        },1000);
                    }
                }catch (Exception ex){
                    Toast.makeText(this, "Erro ao cadastrar: "+ ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        linearvoltaraoclientes.setOnClickListener(v -> {
            setinhaanimada.playAnimation();
            new Handler().postDelayed(() -> {
                Intent voltaraosclientes = new Intent(CadastrodeClientesActivity.this,ClientesActivity.class);
                voltaraosclientes.putExtra("cpfusu",cpfrecebidodosclientes);
                startActivity(voltaraosclientes);
                finish();
            },900);
        });
    }

    private void animacaoloadingcadastro() {
        txtcadastrarbtnsalvar.setVisibility(View.GONE);
        checkedanimation1.setVisibility(View.VISIBLE);
        checkedanimation1.playAnimation();
    }

    @Override
    public void onBackPressed() {
        Intent voltaraosclientes = new Intent(CadastrodeClientesActivity.this,ClientesActivity.class);
        voltaraosclientes.putExtra("cpfusu",cpfrecebidodosclientes);
        startActivity(voltaraosclientes);
        finish();
    }
}