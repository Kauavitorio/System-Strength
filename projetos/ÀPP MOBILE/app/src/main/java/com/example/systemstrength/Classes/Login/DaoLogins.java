package com.example.systemstrength.Classes.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.systemstrength.Classes.Clientes.DtoClientes;

import java.util.ArrayList;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class DaoLogins extends SQLiteOpenHelper {
    private final String TABELA = "TB_FUNCIONARIOS";

    public DaoLogins(@Nullable Context context) {
        super(context, "DB_LOGINS", null, 2);
    }


    //  Create Data Base for Login's Activity
    @Override
    public void onCreate(SQLiteDatabase db) {
        String comando = "CREATE TABLE " + TABELA + "(" +
                "ID INTEGER PRIMARY KEY," +
                "CPFFUNC VARCHAR(14) NOT NULL," +
                "NOMEFUNC VARCHAR(100) NOT NULL," +
                "EMAILFUNC VARCHAR(50) NOT NULL," +
                "ENDERECOFUNC VARCHAR(50)," +
                "TELEFONEFUNC VARCHAR(15)," +
                "CARGOFUNC VARCHAR(50) NOT NULL," +
                "ULTIMAREUNIAOFUNC VARCHAR(50)," +
                "SENHAFUNC VARCHAR(40) NOT NULL)";

        db.execSQL(comando);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //  Method create new account in database
    public long cadastrar(DtoLogins funcionario){
        ContentValues values = new ContentValues();
        values.put("CPFFUNC", funcionario.getCpffunc());
        values.put("NOMEFUNC", funcionario.getNomefunc());
        values.put("EMAILFUNC", funcionario.getEmailfunc());
        values.put("ENDERECOFUNC", funcionario.getEnderecofunc());
        values.put("TELEFONEFUNC", funcionario.getTelefonefunc());
        values.put("CARGOFUNC", funcionario.getCargofunc());
        values.put("SENHAFUNC", funcionario.getSenhafunc());

        return getWritableDatabase().insert(TABELA, null, values);
    }

    //  Method login in database
    public boolean onLogin(String cpf,String senha){
        String comando = "SELECT * FROM " + TABELA + " WHERE CPFFUNC =? and SENHAFUNC =?  ";
        String[] parametros = {cpf,senha};
        Cursor cursor = getWritableDatabase().rawQuery(comando, parametros);

        return cursor.moveToNext();
    }

    //  Method Verification Name in database
    public DtoLogins verificarusuario(String cpf){
        String comando = "SELECT * FROM " + TABELA + " WHERE CPFFUNC =?";
        String[] parametros = {cpf};
        Cursor cursor = getWritableDatabase().rawQuery(comando, parametros);
        DtoLogins dtoLogins = new DtoLogins();

        while (cursor.moveToNext()) {
            dtoLogins.setId(cursor.getInt(0));
            dtoLogins.setCpffunc(cursor.getString(1));
            dtoLogins.setNomefunc(cursor.getString(2));
            dtoLogins.setEmailfunc(cursor.getString(3));
            dtoLogins.setEnderecofunc(cursor.getString(4));
            dtoLogins.setTelefonefunc(cursor.getString(5));
            dtoLogins.setCargofunc(cursor.getString(6));
            dtoLogins.setUltamareufunc(cursor.getString(7));
            dtoLogins.setSenhafunc(cursor.getString(8));


        }
        return dtoLogins;
    }

    public int atualizar(DtoLogins dtoLogins) {
        ContentValues values = new ContentValues();
        values.put("CPFFUNC", dtoLogins.getCpffunc());
        values.put("NOMEFUNC", dtoLogins.getNomefunc());
        values.put("EMAILFUNC", dtoLogins.getEmailfunc());
        values.put("ENDERECOFUNC", dtoLogins.getEnderecofunc());
        values.put("TELEFONEFUNC", dtoLogins.getTelefonefunc());
        values.put("CARGOFUNC", dtoLogins.getCargofunc());
        values.put("ULTIMAREUNIAOFUNC", dtoLogins.getUltamareufunc());

        String id = "id=?";
        String[] args = {dtoLogins.getId()+""};

        return getWritableDatabase().update(TABELA, values, id, args);
    }

    public int atualizaradm(DtoLogins dtoLogins) {
        ContentValues values = new ContentValues();
        values.put("CPFFUNC", dtoLogins.getCpffunc());
        values.put("NOMEFUNC", dtoLogins.getNomefunc());
        values.put("EMAILFUNC", dtoLogins.getEmailfunc());
        values.put("ENDERECOFUNC", dtoLogins.getEnderecofunc());
        values.put("TELEFONEFUNC", dtoLogins.getTelefonefunc());
        values.put("CARGOFUNC", dtoLogins.getCargofunc());
        values.put("ULTIMAREUNIAOFUNC", dtoLogins.getUltamareufunc());
        values.put("SENHAFUNC", dtoLogins.getSenhafunc());

        String id = "id=?";
        String[] args = {dtoLogins.getId()+""};

        return getWritableDatabase().update(TABELA, values, id, args);
    }

    //  Method for look all user on DataBase
    public ArrayList<DtoLogins> consultartodos() {
        String comando = "SELECT * FROM " + TABELA;
        Cursor cursor = getWritableDatabase().rawQuery(comando, null);
        ArrayList<DtoLogins> arrayListuser = new ArrayList<>();

        while (cursor.moveToNext()){
            DtoLogins dtoLogins = new DtoLogins();
            dtoLogins.setId(cursor.getInt(0));
            dtoLogins.setNomefunc(cursor.getString(1));
            dtoLogins.setCpffunc(cursor.getString(2));
            dtoLogins.setEmailfunc(cursor.getString(3));
            dtoLogins.setEnderecofunc(cursor.getString(4));
            dtoLogins.setTelefonefunc(cursor.getString(5));
            dtoLogins.setCargofunc(cursor.getString(6));
            dtoLogins.setUltamareufunc(cursor.getString(7));
            dtoLogins.setSenhafunc(cursor.getString(8));

            arrayListuser.add(dtoLogins);
        }
        return  arrayListuser;
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */
