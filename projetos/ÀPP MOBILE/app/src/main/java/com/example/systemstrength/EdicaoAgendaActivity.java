package com.example.systemstrength;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Classes.Agenda.DaoAgenda;
import com.example.systemstrength.Classes.Agenda.DtoAgenda;
import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

import java.util.ArrayList;

public class EdicaoAgendaActivity extends AppCompatActivity {
    EditText edittextclienteedit, edittextcnpjclienteedit, edittextdataedit, edittexthoraedit, edittextlocaledit, edittextdescedit;
    LinearLayout linearvoltaraagendaedit, btnendclienteedit, linearselecionaroutroclienteedit;
    CardView cardviewbtnsalvareditagenda;
    TextView txtsalvaredit;
    LottieAnimationView setinhaanimadaedit, animacaobolinhaedit, animacaocheckedit;
    Dialog listapopup;
    String cpfrecebidodaagenda;
    DtoClientes clientes;
    ArrayList<DtoClientes> arrayListclientes;
    int idagendaasereditada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_agenda);
        linearvoltaraagendaedit = findViewById(R.id.linearvoltaraagendaedit);
        setinhaanimadaedit = findViewById(R.id.setinhaanimadaedit);
        edittextclienteedit = findViewById(R.id.edittextclienteedit);
        edittextcnpjclienteedit = findViewById(R.id.edittextcnpjclienteedit);
        edittextdataedit = findViewById(R.id.edittextdataedit);
        edittexthoraedit = findViewById(R.id.edittexthoraedit);
        edittextlocaledit = findViewById(R.id.edittextlocaledit);
        edittextdescedit = findViewById(R.id.edittextdescedit);
        btnendclienteedit = findViewById(R.id.btnendclienteedit);
        linearselecionaroutroclienteedit = findViewById(R.id.linearselecionaroutroclienteedit);
        cardviewbtnsalvareditagenda = findViewById(R.id.cardviewbtnsalvareditagenda);
        animacaobolinhaedit = findViewById(R.id.animacaobolinhaedit);
        animacaocheckedit = findViewById(R.id.animacaocheckedit);
        txtsalvaredit = findViewById(R.id.txtsalvaredit);
        listapopup = new Dialog(this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //  Set mask on edittext
        edittextdataedit.addTextChangedListener(MaskEditUtil.mask(edittextdataedit, MaskEditUtil.FORMAT_DATE));
        edittexthoraedit.addTextChangedListener(MaskEditUtil.mask(edittexthoraedit, MaskEditUtil.FORMAT_HOUR));

        //  Get some information
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idagendaasereditada = bundle.getInt("idagendaasereditada");
        cpfrecebidodaagenda = bundle.getString("cpfusu");

        //  Set information of agenda tha will be change
        DaoAgenda daoAgenda = new DaoAgenda(EdicaoAgendaActivity.this);
        DtoAgenda dtoAgenda = daoAgenda.consultaragendamentoporid(idagendaasereditada);
        edittextclienteedit.setText(dtoAgenda.getNomecliente());
        edittextcnpjclienteedit.setText(dtoAgenda.getCnpjcliente());
        edittextdataedit.setText(dtoAgenda.getDataagendamento());
        edittexthoraedit.setText(dtoAgenda.getHoraagendamento());
        edittextlocaledit.setText(dtoAgenda.getLocaldoagendamento());
        edittextdescedit.setText(dtoAgenda.getDescricaoagendamento());

        //  Verification in real time what happening on edittext
        edittextlocaledit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edittextlocaledit.getText() == null || edittextlocaledit.getText().length() == 0){
                    btnendclienteedit.setVisibility(View.VISIBLE);
                }else{
                    btnendclienteedit.setVisibility(View.GONE);
                }
            }
        });

        //  When click in this cardview go try to save what change for Agenda
        cardviewbtnsalvareditagenda.setOnClickListener(v -> {
            if (edittextclienteedit.getText().length() == 0){
                msgselecioneocliente();
            }else if(edittextcnpjclienteedit.getText().length() == 0){
                msgselecioneocliente();
            }else if(edittextdataedit.getText().length() == 0 || edittextdataedit.getText().length() < 10){
                Toast.makeText(EdicaoAgendaActivity.this, "Campo DATA preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextdataedit.requestFocus();
                imm.showSoftInput(edittextdataedit, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittexthoraedit.getText().length() == 0 || edittexthoraedit.getText().length() < 5){
                Toast.makeText(EdicaoAgendaActivity.this, "Campo HORA preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittexthoraedit.requestFocus();
                imm.showSoftInput(edittexthoraedit, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextlocaledit.getText().length() == 0 || edittextlocaledit.getText().length() < 5){
                Toast.makeText(EdicaoAgendaActivity.this, "Campo LOCAL preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextlocaledit.requestFocus();
                imm.showSoftInput(edittextlocaledit, InputMethodManager.SHOW_IMPLICIT);
            }else if(edittextdescedit.getText().length() == 0 || edittextdescedit.getText().length() < 10){
                Toast.makeText(EdicaoAgendaActivity.this, "Campo DESCRIÇÃO preenchido incorretamente!!", Toast.LENGTH_SHORT).show();
                edittextdescedit.requestFocus();
                imm.showSoftInput(edittextdescedit, InputMethodManager.SHOW_IMPLICIT);
            }else {
                try {
                    DtoAgenda dtoAgendaedicao = new DtoAgenda();
                    DaoAgenda agendaedicao = new DaoAgenda(EdicaoAgendaActivity.this);
                    dtoAgendaedicao.setId(idagendaasereditada);
                    dtoAgendaedicao.setNomecliente(edittextclienteedit.getText().toString());
                    dtoAgendaedicao.setCnpjcliente(edittextcnpjclienteedit.getText().toString());
                    dtoAgendaedicao.setDataagendamento(edittextdataedit.getText().toString());
                    dtoAgendaedicao.setHoraagendamento(edittexthoraedit.getText().toString());
                    dtoAgendaedicao.setLocaldoagendamento(edittextlocaledit.getText().toString());
                    dtoAgendaedicao.setDescricaoagendamento(edittextdescedit.getText().toString());
                    long linhaseditadas = agendaedicao.editaragenda(dtoAgendaedicao);
                    animacaobolinhaedit.setVisibility(View.VISIBLE);
                    txtsalvaredit.setVisibility(View.GONE);
                    new Handler().postDelayed(() -> {
                        if (linhaseditadas > 0){
                            animacaocheckedit.setVisibility(View.VISIBLE);
                            animacaobolinhaedit.setVisibility(View.GONE);
                            txtsalvaredit.setVisibility(View.GONE);
                            new Handler().postDelayed(()-> {
                                Toast.makeText(this, "Salvo com sucesso!!", Toast.LENGTH_SHORT).show();
                                Intent voltaraagenda = new Intent(EdicaoAgendaActivity.this,AgendaActivity.class);
                                voltaraagenda.putExtra("cpfusu",cpfrecebidodaagenda);
                                startActivity(voltaraagenda);
                                finish();
                            },900);
                        }else {
                            Toast.makeText(this, "Falha ao editar!!\nContate um gerente", Toast.LENGTH_SHORT).show();
                        }
                    },1000);
                }catch (Exception ex){
                    Toast.makeText(this, "Erro ao editar: "+ ex, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> {
                        Intent voltaraagenda = new Intent(EdicaoAgendaActivity.this,AgendaActivity.class);
                        voltaraagenda.putExtra("cpfusu",cpfrecebidodaagenda);
                        startActivity(voltaraagenda);
                        finish();
                    },700);
                }
            }
        });

        //  When click in this linear you will open the pop up to choice the client
        linearselecionaroutroclienteedit.setOnClickListener(v -> abrirlistadeclientes());

        //  Get address of client
        btnendclienteedit.setOnClickListener(v -> {
            edittextlocaledit.setText(dtoAgenda.getLocaldoagendamento());
            btnendclienteedit.setVisibility(View.GONE);
        });

        //  When click in this Linear you will be back to Agenda
        linearvoltaraagendaedit.setOnClickListener(v -> {
            setinhaanimadaedit.playAnimation();
            new Handler().postDelayed(() -> {
                Intent voltaraagenda = new Intent(EdicaoAgendaActivity.this,AgendaActivity.class);
                voltaraagenda.putExtra("cpfusu",cpfrecebidodaagenda);
                startActivity(voltaraagenda);
                finish();
            },700);
        });
    }

    //  Method for open pop up to make a choice client
    public void abrirlistadeclientes() {
        LinearLayout btncloselista;
        ListView listaviewclienteeditagenda;
        listapopup.setContentView(R.layout.popuplistadecliente);
        btncloselista = listapopup.findViewById(R.id.btncloselista);
        listaviewclienteeditagenda = listapopup.findViewById(R.id.listaviewclienteeditagenda);

        DaoClientes daoClientes = new DaoClientes(EdicaoAgendaActivity.this);
        arrayListclientes = daoClientes.consultarTodos();
        ArrayAdapter adapter = new ArrayAdapter(EdicaoAgendaActivity.this, android.R.layout.simple_list_item_1, arrayListclientes);
        listaviewclienteeditagenda.setAdapter(adapter);

        listaviewclienteeditagenda.setOnItemClickListener((parent, view, position, id) -> {
            clientes =  arrayListclientes.get(position);
            edittextclienteedit.setText(clientes.getNomecliente());
            edittextcnpjclienteedit.setText(clientes.getCnpjcliente());
            listapopup.dismiss();
        });

        btncloselista.setOnClickListener(v -> listapopup.dismiss());

        listapopup.show();
    }

    private void msgselecioneocliente(){
        AlertDialog.Builder msgselectclient = new AlertDialog.Builder(EdicaoAgendaActivity.this);
        msgselectclient.setIcon(R.drawable.logo);
        msgselectclient.setTitle("Cliente não selecionado!!");
        msgselectclient.setMessage("Necessario selecionar o cliente para gerar um novo agendamento");
        msgselectclient.setPositiveButton("Selecionar Cliente", (dialog, which) -> abrirlistadeclientes());
        msgselectclient.setNeutralButton("Cancelar",null);
        msgselectclient.show();
    }

    @Override
    public void onBackPressed() {
        Intent voltaraagenda = new Intent(EdicaoAgendaActivity.this,AgendaActivity.class);
        voltaraagenda.putExtra("cpfusu",cpfrecebidodaagenda);
        startActivity(voltaraagenda);
        finish();
    }
}