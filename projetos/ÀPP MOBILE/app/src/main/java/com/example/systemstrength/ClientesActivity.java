package com.example.systemstrength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.systemstrength.Adapters.AdapterClientes;
import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {
    LinearLayout linearbtnhomeclientes, linearbtnclientes, linearbtnservicoscliente, linearbtncontatoclientes;
    CardView cardviewloadingprincipal, cardviewalertyouarehere, cardviewanimacaocadastronovocliente, cardviewcadastrarnovocliente, cardviewanimationdelete, loadingparaservicoscliente, loadingparaagendaclientes;
    LottieAnimationView smileanimationalert, animationdelete;
    ConstraintLayout basenaoaclientescadastrado;
    ListView listviewclientes;
    ArrayList<DtoClientes> arrayListclientes;
    AdapterClientes myadapter;
    DaoClientes daoClientes = new DaoClientes(ClientesActivity.this);
    DtoClientes clientes;
    String cpfrecebidodaprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        linearbtnhomeclientes = findViewById(R.id.linearbtnhomeclientes);
        linearbtnclientes = findViewById(R.id.linearbtnclientes);
        linearbtnservicoscliente = findViewById(R.id.linearbtnservicoscliente);
        linearbtncontatoclientes = findViewById(R.id.linearbtncontatoclientes);
        animationdelete = findViewById(R.id.animationdelete);
        cardviewloadingprincipal = findViewById(R.id.cardviewloadingprincipal);
        cardviewalertyouarehere = findViewById(R.id.cardviewalertyouarehere);
        cardviewanimationdelete = findViewById(R.id.cardviewanimationdelete);
        smileanimationalert = findViewById(R.id.smileanimationalert);
        listviewclientes = findViewById(R.id.listviewclientes);
        cardviewcadastrarnovocliente = findViewById(R.id.cardviewcadastrarnovocliente);
        cardviewanimacaocadastronovocliente = findViewById(R.id.cardviewanimacaocadastronovocliente);
        loadingparaservicoscliente = findViewById(R.id.loadingparaservicoscliente);
        loadingparaagendaclientes = findViewById(R.id.loadingparaagendaclientes);
        basenaoaclientescadastrado = findViewById(R.id.basenaoaclientescadastrado);

        //  Get Some information
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodaprincipal = bundle.getString("cpfusu");

        DaoClientes daoClientes = new DaoClientes(ClientesActivity.this);
        arrayListclientes = daoClientes.consultarTodos();
        atualizarlistview();

        //  When you click here will see msg to call company
        listviewclientes.setOnItemClickListener((parent, view, position, id) -> {
            clientes = arrayListclientes.get(position);
            AlertDialog.Builder msg = new AlertDialog.Builder(ClientesActivity.this);
            msg.setIcon(R.drawable.logosystemstrengthsemfundo);
            msg.setTitle("Ligar para: "+ clientes.getNomecliente());
            msg.setMessage("Deseja ligar para essa organização?");
            msg.setPositiveButton("Sim", (dialog, which) -> {
                Intent callfor = new Intent(Intent.ACTION_DIAL);
                callfor.setData(Uri.parse("tel:"+ clientes.getTelefonecliente()));
                startActivity(callfor);
            });
            msg.setNegativeButton("Não",null);
            msg.show();
        });

        //  When click here will se menu context
        listviewclientes.setOnItemLongClickListener((parent, view, position, id) -> {
            clientes = arrayListclientes.get(position);
            registerForContextMenu(listviewclientes);
            return false;
        });

        //  When you click in this cardview go to CadastrarCliente
        cardviewcadastrarnovocliente.setOnClickListener(v ->{
            cardviewanimacaocadastronovocliente.setVisibility(View.VISIBLE);
            cardviewcadastrarnovocliente.setVisibility(View.GONE);
            new Handler().postDelayed(() -> {
                Intent irparacadastrodecliente = new Intent(ClientesActivity.this,CadastrodeClientesActivity.class);
                irparacadastrodecliente.putExtra("cpfusu",cpfrecebidodaprincipal);
                startActivity(irparacadastrodecliente);
                finish();
            },1000);
        });

        //  When you click in this linear go do one animation and go to ServicosActivity
        linearbtnservicoscliente.setOnClickListener(v -> {
            loadingparaservicoscliente.setVisibility(View.VISIBLE);
            basenaoaclientescadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                Intent irparaservicos = new Intent(ClientesActivity.this,ServicosActivity.class);
                irparaservicos.putExtra("cpfusu", cpfrecebidodaprincipal);
                startActivity(irparaservicos);
                finish();
            },1400);
        });

        //  When you click in this linear go do one animation and go to AgendaActivity
        linearbtncontatoclientes.setOnClickListener(v -> {
            loadingparaagendaclientes.setVisibility(View.VISIBLE);
            basenaoaclientescadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                Intent irparaagenda = new Intent(ClientesActivity.this,AgendaActivity.class);
                irparaagenda.putExtra("cpfusu", cpfrecebidodaprincipal);
                startActivity(irparaagenda);
                finish();
            },1300);
        });


        //  When you click in this linear go do one animation and go to PrincipalActivity
        linearbtnhomeclientes.setOnClickListener(v ->{
            cardviewloadingprincipal.setVisibility(View.VISIBLE);
            basenaoaclientescadastrado.setVisibility(View.GONE);
            DesableBtns();
            new Handler().postDelayed(() -> {
                Intent voltarparaprincipal = new Intent(ClientesActivity.this,PrincipalActivity.class);
                voltarparaprincipal.putExtra("cpfusu",cpfrecebidodaprincipal);
                startActivity(voltarparaprincipal);
                finish();
            },1500);
        });

        //  When Click in this linear will show one msg and do one animation
        linearbtnclientes.setOnClickListener(v -> {
            Toast.makeText(this, "Você já esta aqui :)", Toast.LENGTH_SHORT).show();
            cardviewalertyouarehere.setVisibility(View.VISIBLE);
            smileanimationalert.playAnimation();
            new Handler().postDelayed(() -> {
                cardviewalertyouarehere.setVisibility(View.GONE);
                smileanimationalert.pauseAnimation();

            },2000);
        });
    }

    //  Create method for update  ListView
    private void atualizarlistview() {
        if (arrayListclientes.size() > 0){
            basenaoaclientescadastrado.setVisibility(View.GONE);
            listviewclientes.setVisibility(View.VISIBLE);
            myadapter = new AdapterClientes(ClientesActivity.this, R.layout.adapterclientes, arrayListclientes);
            listviewclientes.setAdapter(myadapter);
        }else {
            basenaoaclientescadastrado.setVisibility(View.VISIBLE);
            listviewclientes.setVisibility(View.GONE);
        }
    }

    //  Create method for delete some client of  ListView
    private void excluir() {
        AlertDialog.Builder msg = new AlertDialog.Builder(ClientesActivity.this);
        msg.setMessage("Confirma a exclusão?");
        msg.setPositiveButton("Sim", (dialogInterface, i) -> {
            int deletados = daoClientes.excluir(clientes);
            if (deletados>0){
                cardviewanimationdelete.setVisibility(View.VISIBLE);
                animationdelete.playAnimation();
                new Handler().postDelayed(() -> {
                    Toast.makeText(ClientesActivity.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    arrayListclientes = daoClientes.consultarTodos();
                    atualizarlistview();
                    cardviewanimationdelete.setVisibility(View.GONE);
                    animationdelete.pauseAnimation();
                },2000);
            } else {
                Toast.makeText(ClientesActivity.this, "Erro ao Excluir!", Toast.LENGTH_SHORT).show();
            }
        });
        msg.setNegativeButton("Não", null);
        msg.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("•  " + clientes.getNomecliente() + "  •");
        menu.add(0,0,0,"Detalhes / Alterar");
        menu.add(0,1,1,"Novo Compromisso");
        menu.add(0,2,2,"Excluir Organização");
        menu.add(0,3,3,"Ligar");
        menu.add(0,4,4,"Contratos");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0){
            Intent irparaagenda = new Intent(ClientesActivity.this, DetalhesActivity.class);
            irparaagenda.putExtra("cpfusu",cpfrecebidodaprincipal);
            irparaagenda.putExtra("cnjdaempresa",clientes.getCnpjcliente());
            startActivity(irparaagenda);
            finish();
        }else if(item.getItemId() == 1){
            Intent irparaagenda = new Intent(ClientesActivity.this, NovoAgendamentoActivity.class);
            irparaagenda.putExtra("cpfusu",cpfrecebidodaprincipal);
            irparaagenda.putExtra("cnjdaempresa",clientes.getCnpjcliente());
            startActivity(irparaagenda);
            finish();
        }else if(item.getItemId() == 2){
            excluir();
        }else if (item.getItemId() == 3){
            Intent callfor = new Intent(Intent.ACTION_DIAL);
            callfor.setData(Uri.parse("tel:"+ clientes.getTelefonecliente()));
            startActivity(callfor);
        }else if (item.getItemId() == 4) {
            Intent irparacontratos = new Intent(ClientesActivity.this, ContratosActivity.class);
            irparacontratos.putExtra("cpfusu",cpfrecebidodaprincipal);
            startActivity(irparacontratos);
        }
        return super.onContextItemSelected(item);
    }

    public void DesableBtns(){
        linearbtnclientes.setEnabled(false);
        linearbtnhomeclientes.setEnabled(false);
        linearbtncontatoclientes.setEnabled(false);
        linearbtnservicoscliente.setEnabled(false);
    }
}