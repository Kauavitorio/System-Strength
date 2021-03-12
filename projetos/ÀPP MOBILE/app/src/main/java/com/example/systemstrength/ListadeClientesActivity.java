package com.example.systemstrength;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.systemstrength.Classes.Clientes.DaoClientes;
import com.example.systemstrength.Classes.Clientes.DtoClientes;

import java.util.ArrayList;

public class ListadeClientesActivity extends AppCompatActivity {
    LinearLayout linearvoltaraagenda;
    ArrayList<DtoClientes> arrayListclientes;
    ListView listviewclientes;
    DtoClientes clientes;
    String cpfrecebidodaagenda, cnpjclienteselecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listade_clientes);
        linearvoltaraagenda = findViewById(R.id.linearvoltaraagenda);
        listviewclientes = findViewById(R.id.listviewclientes);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cpfrecebidodaagenda = bundle.getString("cpfusu");

        DaoClientes daoClientes = new DaoClientes(ListadeClientesActivity.this);
        arrayListclientes = daoClientes.consultarTodos();
        atualizarlistview();

        listviewclientes.setOnItemClickListener((parent, view, position, id) -> {
            clientes = arrayListclientes.get(position);
            AlertDialog.Builder msg = new AlertDialog.Builder(ListadeClientesActivity.this);
            msg.setIcon(R.drawable.logosystemstrengthsemfundo);
            msg.setTitle("Cliente: " + clientes.getNomecliente());
            msg.setMessage("Deseja selecionar " + clientes.getNomecliente() + " ?");
            msg.setPositiveButton("Sim", (dialog, which) -> {
                cnpjclienteselecionado = clientes.getCnpjcliente();
                Intent voltaraocadastro = new Intent(ListadeClientesActivity.this,NovoAgendamentoActivity.class);
                voltaraocadastro.putExtra("cnjdaempresa",cnpjclienteselecionado);
                voltaraocadastro.putExtra("cpfusu",cpfrecebidodaagenda);
                startActivity(voltaraocadastro);
                finish();
            });
            msg.setNegativeButton("Não", null);
            msg.show();
        });

        listviewclientes.setOnItemLongClickListener((parent, view, position, id) -> {
            clientes = arrayListclientes.get(position);
            registerForContextMenu(listviewclientes);
            return false;
        });

        linearvoltaraagenda.setOnClickListener(v -> {
            Intent voltaraocadastro = new Intent(ListadeClientesActivity.this,NovoAgendamentoActivity.class);
            voltaraocadastro.putExtra("cpfusu",cpfrecebidodaagenda);
            startActivity(voltaraocadastro);
            finish();
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("•  " + clientes.getNomecliente() + "  •");
        menu.add(0,0,0,"Selecionar");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0){
            cnpjclienteselecionado = clientes.getCnpjcliente();
            Intent voltaraocadastro = new Intent(ListadeClientesActivity.this,NovoAgendamentoActivity.class);
            voltaraocadastro.putExtra("cnjdaempresa",cnpjclienteselecionado);
            voltaraocadastro.putExtra("cpfusu",cpfrecebidodaagenda);
            startActivity(voltaraocadastro);
            finish();
        }
        return super.onContextItemSelected(item);
    }

    //  Create method for update ListView
    private void atualizarlistview() {
        ArrayAdapter adapter = new ArrayAdapter(ListadeClientesActivity.this, android.R.layout.simple_list_item_1, arrayListclientes);
        listviewclientes.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent voltaraocadastro = new Intent(ListadeClientesActivity.this,NovoAgendamentoActivity.class);
        voltaraocadastro.putExtra("cpfusu",cpfrecebidodaagenda);
        startActivity(voltaraocadastro);
        finish();
    }
}