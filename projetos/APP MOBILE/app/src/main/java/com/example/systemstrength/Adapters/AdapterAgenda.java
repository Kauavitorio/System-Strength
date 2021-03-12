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

import com.example.systemstrength.Classes.Agenda.DtoAgenda;
import com.example.systemstrength.R;

import java.util.List;

public class AdapterAgenda extends ArrayAdapter<DtoAgenda> {

    private final List<DtoAgenda> listaagenda;
    private final Context mycontext;

    public AdapterAgenda(@NonNull Context context, int resource, List<DtoAgenda> objects) {
        super(context, resource, objects);
        this.listaagenda = objects;
        this.mycontext = context;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mycontext).inflate(R.layout.adapteragenda,null);


        TextView txtnomeclienteagendalista = view.findViewById(R.id.txtnomeclienteagendalista);
        TextView txthoraagenda = view.findViewById(R.id.txthoraagenda);
        TextView txtdataagenda = view.findViewById(R.id.txtdataagenda);
        TextView txtlocalagenda = view.findViewById(R.id.txtlocalagenda);
        DtoAgenda dtoAgenda = listaagenda.get(position);
        txtnomeclienteagendalista.setText("• "+dtoAgenda.getNomecliente()+ " •");

        txthoraagenda.setText(dtoAgenda.getHoraagendamento());

        txtdataagenda.setText(dtoAgenda.getDataagendamento());

        txtlocalagenda.setText(dtoAgenda.getLocaldoagendamento());

        return view;
    }
}
