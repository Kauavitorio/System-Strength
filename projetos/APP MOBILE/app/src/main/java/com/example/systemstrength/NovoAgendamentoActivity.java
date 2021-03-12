package com.example.systemstrength;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.example.systemstrength.Classes.Agenda.DaoAgenda;
import com.example.systemstrength.Classes.Agenda.DtoAgenda;
import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

public class NovoAgendamentoActivity extends AppCompatActivity {
    LinearLayout linearvoltaraagenda, linearselecionaroutrocliente, btnendcliente;
    LottieAnimationView animacaovoltaraagenda, loadingcadastro, animacaocorreto;
    TextView txtsalvaragenda;
    CardView cardviewbtnescolhercliente, cardviewbtnsalvarnovoagendamento;
    EditText edittextclientenovoagendamento, edittextcnpjclientenovoagendamento, edittextdatanovoagendamento, edittexthoranovoagendamento, edittextlocalnovoagendamento, edittextdescnovoagendamento;
    String cpfrecebidodaagenda;
    String clienteescolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_agendamento);
        linearvoltaraagenda = findViewById(R.id.linearvoltaraagenda);
        linearselecionaroutrocliente = findViewById(R.id.linearselecionaroutrocliente);
        animacaovoltaraagenda = findViewById(R.id.animacaovoltaraagenda);
        cardviewbtnescolhercliente = findViewById(R.id.cardviewbtnescolhercliente);
        cardviewbtnsalvarnovoagendamento = findViewById(R.id.cardviewbtnsalvarnovoagendamento);
        edittextclientenovoagendamento = findViewById(R.id.edittextclientenovoagendamento);
        edittextcnpjclientenovoagendamento = findViewById(R.id.edittextcnpjclientenovoagendamento);
        edittextdatanovoagendamento = findViewById(R.id.edittextdatanovoagendamento);
        edittexthoranovoagendamento = findViewById(R.id.edittexthoranovoagendamento);
        edittextlocalnovoagendamento = findViewById(R.id.edittextlocalnovoagendamento);
        edittextdescnovoagendamento = findViewById(R.id.edittextdescnovoagendamento);
        loadingcadastro = findViewById(R.id.loadingcadastro);
        animacaocorreto = findViewById(R.id.animacaocorreto);
        txtsalvaragenda = findViewById(R.id.txtsalvaragenda);
        btnendcliente = findViewById(R.id.btnendcliente);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        linearselecionaroutrocliente.setVisibility(View.GONE);

        edittextcnpjclientenovoagendamento.addTextChangedListener(MaskEditUtil.mask(edittextcnpjclientenovoagendamento, MaskEditUtil.FORMAT_CNPJ));
        edittextdatanovoagendamento.addTextChangedListener(MaskEditUtil.mask(edittextdatanovoagendamento, MaskEditUtil.FORMAT_DATE));
        edittexthoranovoagendamento.addTextChangedListener(MaskEditUtil.mask(edittexthoranovoagendamento, MaskEditUtil.FORMAT_HOUR));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodaagenda = bundle.getString("cpfusu");
        if (bundle.getString("cnjdaempresa") == null){
            clienteescolhido = null;
        }else{
            clienteescolhido = bundle.getString("cnjdaempresa");
        }

        validacaoerecebimentodadoscliente();

        btnendcliente.setOnClickListener(v -> receberendcliente());

        cardviewbtnescolhercliente.setOnClickListener(v -> {
            Intent irparalistadeclientes = new Intent(NovoAgendamentoActivity.this,ListadeClientesActivity.class);
            irparalistadeclientes.putExtra("cpfusu",cpfrecebidodaagenda);
            startActivity(irparalistadeclientes);
            finish();
        });

        linearselecionaroutrocliente.setOnClickListener(v -> {
            Intent irparalistadeclientes = new Intent(NovoAgendamentoActivity.this,ListadeClientesActivity.class);
            irparalistadeclientes.putExtra("cpfusu",cpfrecebidodaagenda);
            startActivity(irparalistadeclientes);
            finish();
        });

        edittextlocalnovoagendamento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextlocalnovoagendamento.getText().length() <= 0) {
                    btnendcliente.setVisibility(View.VISIBLE);
                } else {
                    btnendcliente.setVisibility(View.GONE);
                }
            }
        });

        cardviewbtnsalvarnovoagendamento.setOnClickListener(v -> {
            if (edittextclientenovoagendamento.getText().length() == 0){
                msgselecioneocliente();
            }else if(edittextcnpjclientenovoagendamento.getText().length() == 0){
                msgselecioneocliente();
            }else if(edittextdatanovoagendamento.getText().length() == 0 || edittextdatanovoagendamento.getText().length() < 10){
                Toast.makeText(NovoAgendamentoActivity.this, "Campo DATA preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextdatanovoagendamento.requestFocus();
                imm.showSoftInput(edittextdatanovoagendamento, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittexthoranovoagendamento.getText().length() == 0 || edittexthoranovoagendamento.getText().length() < 5){
                Toast.makeText(NovoAgendamentoActivity.this, "Campo HORA preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittexthoranovoagendamento.requestFocus();
                imm.showSoftInput(edittexthoranovoagendamento, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextlocalnovoagendamento.getText().length() == 0 || edittextlocalnovoagendamento.getText().length() < 5){
                Toast.makeText(NovoAgendamentoActivity.this, "Campo LOCAL preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextlocalnovoagendamento.requestFocus();
                imm.showSoftInput(edittextlocalnovoagendamento, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextdescnovoagendamento.getText().length() == 0 || edittextdescnovoagendamento.getText().length() < 10){
                Toast.makeText(NovoAgendamentoActivity.this, "Campo DESCRIÇÃO preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextdescnovoagendamento.requestFocus();
                imm.showSoftInput(edittextdescnovoagendamento, InputMethodManager.SHOW_IMPLICIT);
            }else {
                try {
                    txtsalvaragenda.setVisibility(View.GONE);
                    loadingcadastro.setVisibility(View.VISIBLE);
                    loadingcadastro.playAnimation();
                    DtoAgenda dtoAgenda = new DtoAgenda();
                    DaoAgenda daoAgenda = new DaoAgenda(NovoAgendamentoActivity.this);
                    dtoAgenda.setNomecliente(edittextclientenovoagendamento.getText().toString());
                    dtoAgenda.setCnpjcliente(edittextcnpjclientenovoagendamento.getText().toString());
                    dtoAgenda.setDataagendamento(edittextdatanovoagendamento.getText().toString());
                    dtoAgenda.setHoraagendamento(edittexthoranovoagendamento.getText().toString());
                    dtoAgenda.setLocaldoagendamento(edittextlocalnovoagendamento.getText().toString());
                    dtoAgenda.setDescricaoagendamento(edittextdescnovoagendamento.getText().toString());
                    new Handler().postDelayed(() -> {
                        long linasinseridas = daoAgenda.cadastrar(dtoAgenda);
                        if (linasinseridas > 0){
                            loadingcadastro.setVisibility(View.GONE);
                            loadingcadastro.pauseAnimation();
                            animacaocorreto.setVisibility(View.VISIBLE);
                            animacaocorreto.playAnimation();
                            new Handler().postDelayed(() -> {
                                Toast.makeText(this, "Cadastrado com sucessso!!", Toast.LENGTH_SHORT).show();
                                Intent voltaraosclientes = new Intent(NovoAgendamentoActivity.this,AgendaActivity.class);
                                voltaraosclientes.putExtra("cpfusu",cpfrecebidodaagenda);
                                startActivity(voltaraosclientes);
                                finish();
                            },1000);
                        }else{
                            Toast.makeText(this, "Falha ao cadastrar!!\nContate um administrador", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {
                                Intent voltaraosclientes = new Intent(NovoAgendamentoActivity.this,AgendaActivity.class);
                                voltaraosclientes.putExtra("cpfusu",cpfrecebidodaagenda);
                                startActivity(voltaraosclientes);
                                finish();
                            },1000);
                        }
                    },1500);
                }catch (Exception ex){
                    Toast.makeText(this, "Erro ao cadastrar: "+ ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        linearvoltaraagenda.setOnClickListener(v -> {
            animacaovoltaraagenda.playAnimation();
            new Handler().postDelayed(() -> {
                Intent voltaraosclientes = new Intent(NovoAgendamentoActivity.this,AgendaActivity.class);
                voltaraosclientes.putExtra("cpfusu",cpfrecebidodaagenda);
                startActivity(voltaraosclientes);
                finish();
            },900);
        });
    }

    private void validacaoerecebimentodadoscliente() {
        if (clienteescolhido == null){
            cardviewbtnescolhercliente.setVisibility(View.VISIBLE);
            btnendcliente.setVisibility(View.GONE);
        }else{
            cardviewbtnescolhercliente.setVisibility(View.GONE);
            DaoClientes daoClientes = new DaoClientes(NovoAgendamentoActivity.this);
            DtoClientes dtoClientes = daoClientes.consultarcliente(clienteescolhido);
            edittextclientenovoagendamento.setText(dtoClientes.getNomecliente());
            edittextcnpjclientenovoagendamento.setText(dtoClientes.getCnpjcliente());
            linearselecionaroutrocliente.setVisibility(View.VISIBLE);
            btnendcliente.setVisibility(View.VISIBLE);
        }
    }

    private void receberendcliente(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        DaoClientes daoClientes = new DaoClientes(NovoAgendamentoActivity.this);
        DtoClientes dtoClientes = daoClientes.consultarcliente(clienteescolhido);
        edittextlocalnovoagendamento.setText(dtoClientes.getEnderecocliente());
        edittextdescnovoagendamento.requestFocus();
        imm.showSoftInput(edittextdescnovoagendamento, InputMethodManager.SHOW_IMPLICIT);
    }

    private void msgselecioneocliente(){
        AlertDialog.Builder msgselectclient = new AlertDialog.Builder(NovoAgendamentoActivity.this);
        msgselectclient.setIcon(R.drawable.logo);
        msgselectclient.setTitle("Cliente não selecionado!!");
        msgselectclient.setMessage("Necessario selecionar o cliente para gerar um novo agendamento");
        msgselectclient.setPositiveButton("Selecionar Cliente", (dialog, which) -> {
            Intent irparalistadeclientes = new Intent(NovoAgendamentoActivity.this,ListadeClientesActivity.class);
            irparalistadeclientes.putExtra("cpfusu",cpfrecebidodaagenda);
            startActivity(irparalistadeclientes);
            finish();
        });
        msgselectclient.setNeutralButton("Cancelar",null);
        msgselectclient.show();
    }

    @Override
    public void onBackPressed() {
        Intent voltaraosclientes = new Intent(NovoAgendamentoActivity.this,AgendaActivity.class);
        voltaraosclientes.putExtra("cpfusu",cpfrecebidodaagenda);
        startActivity(voltaraosclientes);
        finish();
    }
}