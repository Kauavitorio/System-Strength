package com.example.systemstrength.Classes.Clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class DaoClientes extends SQLiteOpenHelper {
    private final String TABELA = "TB_CLIENTES";

    public DaoClientes(@Nullable Context context) {
        super(context, "DB_CLIENTES", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //  Create Data Base for Client's Activity
        String comando = "CREATE TABLE " + TABELA + "(" +
                "ID INTEGER PRIMARY KEY," +
                "NOMECLIENTE VARCHAR(100) NOT NULL," +
                "ENDERECOCLIENTE VARCHAR(50) NOT NULL," +
                "TELEFONECLIENTE VARCHAR(15) NOT NULL," +
                "EMAILCLIENTE VARCHAR(50) NOT NULL," +
                "CNPJCLIENTE CHAR(18) NOT NULL)";

        db.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long cadastrar(DtoClientes clientes){
        ContentValues values = new ContentValues();
        values.put("NOMECLIENTE", clientes.getNomecliente());
        values.put("ENDERECOCLIENTE", clientes.getEnderecocliente());
        values.put("TELEFONECLIENTE", clientes.getTelefonecliente());
        values.put("EMAILCLIENTE", clientes.getEmailcliente());
        values.put("CNPJCLIENTE", clientes.getCnpjcliente());

        return getWritableDatabase().insert(TABELA, null, values);
    }

    public ArrayList<DtoClientes> consultarTodos(){
        String comando = "SELECT * FROM " + TABELA;
        Cursor cursor = getWritableDatabase().rawQuery(comando, null);
        ArrayList<DtoClientes> ArrayListclientes = new ArrayList<>();

        while (cursor.moveToNext()){
            DtoClientes dtoClientes = new DtoClientes();
            dtoClientes.setId(cursor.getInt(0));
            dtoClientes.setNomecliente(cursor.getString(1));
            dtoClientes.setEnderecocliente(cursor.getString(2));
            dtoClientes.setTelefonecliente(cursor.getString(3));
            dtoClientes.setEmailcliente(cursor.getString(4));
            dtoClientes.setCnpjcliente(cursor.getString(5));

            ArrayListclientes.add(dtoClientes);
        }
        return  ArrayListclientes;
    }

    public DtoClientes consultarcliente(String cnpj){
        String comando = "SELECT * FROM " + TABELA + " WHERE  CNPJCLIENTE=?";
        String[] parametros = {cnpj};
        Cursor cursor = getWritableDatabase().rawQuery(comando, parametros);
        DtoClientes dtoClientes = new DtoClientes();

        while (cursor.moveToNext()){
            dtoClientes.setId(cursor.getInt(0));
            dtoClientes.setNomecliente(cursor.getString(1));
            dtoClientes.setEnderecocliente(cursor.getString(2));
            dtoClientes.setTelefonecliente(cursor.getString(3));
            dtoClientes.setEmailcliente(cursor.getString(4));
            dtoClientes.setCnpjcliente(cursor.getString(5));

        }
        return dtoClientes;
    }

    public  int excluir(DtoClientes clientes){
        String id = "id=?";
        String[] args  = {clientes.getId()+""};
        return  getWritableDatabase().delete(TABELA,id,args);
    }

    public int atualizar(DtoClientes dtoClientes){
        ContentValues values = new ContentValues();
        values.put("NOMECLIENTE",dtoClientes.getNomecliente());
        values.put("ENDERECOCLIENTE",dtoClientes.getEnderecocliente());
        values.put("TELEFONECLIENTE",dtoClientes.getTelefonecliente());
        values.put("EMAILCLIENTE",dtoClientes.getEmailcliente());
        values.put("CNPJCLIENTE",dtoClientes.getCnpjcliente());

        String id = "id=?";
        String[] args = {dtoClientes.getId()+""};

        return getWritableDatabase().update(TABELA,values,id,args);
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */
