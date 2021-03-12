package com.example.systemstrength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Adapters.AdapterAgenda;
import com.example.systemstrength.Classes.Agenda.DaoAgenda;
import com.example.systemstrength.Classes.Agenda.DtoAgenda;

import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity {
    LinearLayout linearbtnhomeagenda, linearbtncontatoagenda, linearbtnservicosagendaagenda, linearbtnclientesagenda;
    CardView cardviewloadingpricipalagenda, cardviewnovoagendamento, cardviewanimacaonovoagendamento, cardviewanimationdeleteagenda, loadingparaservicosagenda, loadingparaclientesagenda;
    ListView listviewagenda;
    ConstraintLayout basenaoaagendacadastrado;
    LottieAnimationView animationdeleteagenda, animacaoservicosagenda;
    ArrayList<DtoAgenda> arraylistagenda;
    AdapterAgenda myadapter;
    DtoAgenda agenda;
    DaoAgenda daoAgenda = new DaoAgenda(AgendaActivity.this);
    Dialog myDialog;
    String cpfrecebidobase;
    int idparasereditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        linearbtnhomeagenda = findViewById(R.id.linearbtnhomeagenda);
        cardviewloadingpricipalagenda = findViewById(R.id.cardviewloadingpricipalagenda);
        cardviewnovoagendamento = findViewById(R.id.cardviewnovoagendamento);
        cardviewanimacaonovoagendamento = findViewById(R.id.cardviewanimacaonovoagendamento);
        listviewagenda = findViewById(R.id.listviewagenda);
        cardviewanimationdeleteagenda = findViewById(R.id.cardviewanimationdeleteagenda);
        animationdeleteagenda = findViewById(R.id.animationdeleteagenda);
        linearbtncontatoagenda = findViewById(R.id.linearbtncontatoagenda);
        linearbtnservicosagendaagenda = findViewById(R.id.linearbtnservicosagendaagenda);
        loadingparaservicosagenda = findViewById(R.id.loadingparaservicosagenda);
        animacaoservicosagenda = findViewById(R.id.animacaoservicosagenda);
        linearbtnclientesagenda = findViewById(R.id.linearbtnclientesagenda);
        loadingparaclientesagenda = findViewById(R.id.loadingparaclientesagenda);
        basenaoaagendacadastrado = findViewById(R.id.basenaoaagendacadastrado);
        myDialog = new Dialog(this);

        //  Get some information
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidobase = bundle.getString("cpfusu");

        DaoAgenda daoAgenda= new DaoAgenda(AgendaActivity.this);
        arraylistagenda = daoAgenda.consultarTodaagenda();
        atualizarlistview();

        listviewagenda.setOnItemLongClickListener((parent, view, position, id) -> {
            agenda = arraylistagenda.get(position);
            registerForContextMenu(listviewagenda);
            return false;
        });

        //  When click in this linear you will go to New Agendamento
        cardviewnovoagendamento.setOnClickListener(v -> {
            cardviewnovoagendamento.setVisibility(View.GONE);
            cardviewanimacaonovoagendamento.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Intent irparanovoagendamento = new Intent(AgendaActivity.this,NovoAgendamentoActivity.class);
                irparanovoagendamento.putExtra("cpfusu",cpfrecebidobase);
                startActivity(irparanovoagendamento);
                finish();
            },1500);
        });

        //  When click in this linear you will go to Client Activity
        linearbtnclientesagenda.setOnClickListener(v -> {
            loadingparaclientesagenda.setVisibility(View.VISIBLE);
            basenaoaagendacadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                Intent irparaclientes = new Intent(AgendaActivity.this,ClientesActivity.class);
                irparaclientes.putExtra("cpfusu",cpfrecebidobase);
                startActivity(irparaclientes);
                finish();
            },1500);
        });

        //  When click in this linear you will go to Services Activity
        linearbtnservicosagendaagenda.setOnClickListener(v -> {
            loadingparaservicosagenda.setVisibility(View.VISIBLE);
            animacaoservicosagenda.setSpeed(2);
            basenaoaagendacadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                loadingparaservicosagenda.setVisibility(View.GONE);
                Intent irparaservicos = new Intent(AgendaActivity.this,ServicosActivity.class);
                irparaservicos.putExtra("cpfusu",cpfrecebidobase);
                startActivity(irparaservicos);
                finish();
            },1400);
        });

        //  When click in this linear you will see one msg
        linearbtncontatoagenda.setOnClickListener(v -> Toast.makeText(this, "Você já esta aqui :)", Toast.LENGTH_SHORT).show());

        //  When click in this linear you will go to Principal Activity
        linearbtnhomeagenda.setOnClickListener(v ->{
            cardviewloadingpricipalagenda.setVisibility(View.VISIBLE);
            basenaoaagendacadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                Intent voltarparaprincipal = new Intent(AgendaActivity.this,PrincipalActivity.class);
                voltarparaprincipal.putExtra("cpfusu",cpfrecebidobase);
                startActivity(voltarparaprincipal);
                finish();
            },1500);
        });
    }

    //  Create method for update  ListView
    private void atualizarlistview() {
        if (arraylistagenda.size() > 0){
            myadapter = new AdapterAgenda(AgendaActivity.this, R.layout.adapteragenda, arraylistagenda);
            listviewagenda.setAdapter(myadapter);
            basenaoaagendacadastrado.setVisibility(View.GONE);
            listviewagenda.setVisibility(View.VISIBLE);
        }else {
            basenaoaagendacadastrado.setVisibility(View.VISIBLE);
            listviewagenda.setVisibility(View.GONE);
        }
    }

    //  Create method for delete some client of  ListView
    private void excluir() {
        AlertDialog.Builder msg = new AlertDialog.Builder(AgendaActivity.this);
        msg.setMessage("Confirma a exclusão?");
        msg.setPositiveButton("Sim", (dialogInterface, i) -> {
            int deletados = daoAgenda.excluir(agenda);
            if (deletados>0){
                cardviewanimationdeleteagenda.setVisibility(View.VISIBLE);
                animationdeleteagenda.playAnimation();
                new Handler().postDelayed(() -> {
                    Toast.makeText(AgendaActivity.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    arraylistagenda = daoAgenda.consultarTodaagenda();
                    atualizarlistview();
                    cardviewanimationdeleteagenda.setVisibility(View.GONE);
                    animationdeleteagenda.pauseAnimation();
                },2000);
            } else {
                Toast.makeText(AgendaActivity.this, "Erro ao Excluir!", Toast.LENGTH_SHORT).show();
            }
        });
        msg.setNegativeButton("Não", null);
        msg.show();
    }

    private void editar(){
        Intent irparaedit = new Intent(AgendaActivity.this,EdicaoAgendaActivity.class);
        irparaedit.putExtra("idagendaasereditada", agenda.getId());
        irparaedit.putExtra("cpfusu",cpfrecebidobase);
        startActivity(irparaedit);
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("• " + agenda.getNomecliente()+ " •");
        menu.add(0,0,0,"Detalhes");
        menu.add(0,1,1,"Editar");
        menu.add(0,2,2,"Excluir");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0){
            aparecerpopup();
        }else if (item.getItemId()  == 1){
            editar();
        }else if (item.getItemId() == 2){
            excluir();
        }

        return super.onContextItemSelected(item);
    }

    public void aparecerpopup() {
        TextView txtnomeclientedetalhes, txtcnpjclientedetalhes, txtdatadetalhes, txthourdetalhes, txtlocaldetalhes, txtdescdetalhes;
        LinearLayout btnclosedetalhes;
        myDialog.setContentView(R.layout.popupdetalhesagenda);
        txtnomeclientedetalhes = myDialog.findViewById(R.id.txtnomeclientedetalhes);
        txtcnpjclientedetalhes = myDialog.findViewById(R.id.txtcnpjclientedetalhes);
        txtdatadetalhes = myDialog.findViewById(R.id.txtdatadetalhes);
        txthourdetalhes = myDialog.findViewById(R.id.txthourdetalhes);
        txtlocaldetalhes = myDialog.findViewById(R.id.txtlocaldetalhes);
        txtdescdetalhes = myDialog.findViewById(R.id.txtdescdetalhes);
        btnclosedetalhes = myDialog.findViewById(R.id.btnclosedetalhes);

        txtnomeclientedetalhes.setText(agenda.getNomecliente());
        txtcnpjclientedetalhes.setText(agenda.getCnpjcliente());
        txtdatadetalhes.setText(agenda.getDataagendamento());
        txthourdetalhes.setText(agenda.getHoraagendamento());
        txtlocaldetalhes.setText(agenda.getLocaldoagendamento());
        txtdescdetalhes.setText(agenda.getDescricaoagendamento());

        btnclosedetalhes.setOnClickListener(v1 -> myDialog.dismiss());

        myDialog.show();
    }

    public void DesableBtns(){
        linearbtnhomeagenda.setEnabled(false);
        linearbtncontatoagenda.setEnabled(false);
        linearbtnservicosagendaagenda.setEnabled(false);
        linearbtnclientesagenda.setEnabled(false);
    }
}