package com.example.systemstrength.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.systemstrength.Classes.Clientes.DtoClientes;
import com.example.systemstrength.R;

import java.util.List;


public class AdapterClientes extends ArrayAdapter<DtoClientes> {

    private final List<DtoClientes> mylist;
    private final Context mycontext;

    public AdapterClientes(@NonNull Context context, int resource, List<DtoClientes> objects) {
        super(context, resource, objects);
        this.mylist = objects;
        this.mycontext = context;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mycontext).inflate(R.layout.adapterclientes,null);

        DtoClientes dtoClientes = mylist.get(position);
        TextView txtnomecliente = view.findViewById(R.id.txtnomecliente);
        txtnomecliente.setText("• "+ dtoClientes.getNomecliente() + " •");
        TextView txtemailcliente = view.findViewById(R.id.txtemailcliente);
        txtemailcliente.setText("• "+ dtoClientes.getEmailcliente());
        TextView txtcnpjcliente = view.findViewById(R.id.txtcnpjcliente);
        txtcnpjcliente.setText("• "+ dtoClientes.getCnpjcliente());

        return  view;


    }
}
