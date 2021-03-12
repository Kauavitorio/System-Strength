package com.example.systemstrength.Classes.Login;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class DtoLogins {
    private int id;
    private String cpffunc, nomefunc, emailfunc, enderecofunc, telefonefunc, cargofunc, ultamareufunc, senhafunc;

    public DtoLogins(int id, String cpffunc, String nomefunc, String emailfunc, String enderecofunc, String telefonefunc, String cargofunc, String ultamareufunc, String senhafunc) {
        this.id = id;
        this.cpffunc = cpffunc;
        this.nomefunc = nomefunc;
        this.emailfunc = emailfunc;
        this.enderecofunc = enderecofunc;
        this.telefonefunc = telefonefunc;
        this.cargofunc = cargofunc;
        this.ultamareufunc = ultamareufunc;
        this.senhafunc = senhafunc;
    }

    public DtoLogins(){}

    @Override
    public String toString() {
        return "\n" + "• Nome: " + nomefunc + "\n" + "• CPF: " + cargofunc + "\n" +"• Email: " + emailfunc + "\n" +"• Telefone: " + telefonefunc + "\n";
    }

    //  Declaration to Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpffunc() {
        return cpffunc;
    }

    public void setCpffunc(String cpffunc) {
        this.cpffunc = cpffunc;
    }

    public String getNomefunc() {
        return nomefunc;
    }

    public void setNomefunc(String nomefunc) {
        this.nomefunc = nomefunc;
    }

    public String getEmailfunc() {
        return emailfunc;
    }

    public void setEmailfunc(String emailfunc) {
        this.emailfunc = emailfunc;
    }

    public String getEnderecofunc() {
        return enderecofunc;
    }

    public void setEnderecofunc(String enderecofunc) {
        this.enderecofunc = enderecofunc;
    }

    public String getTelefonefunc() {
        return telefonefunc;
    }

    public void setTelefonefunc(String telefonefunc) {
        this.telefonefunc = telefonefunc;
    }

    public String getCargofunc() {
        return cargofunc;
    }

    public void setCargofunc(String cargofunc) {
        this.cargofunc = cargofunc;
    }

    public String getUltamareufunc() {
        return ultamareufunc;
    }

    public void setUltamareufunc(String ultamareufunc) {
        this.ultamareufunc = ultamareufunc;
    }

    public String getSenhafunc() {
        return senhafunc;
    }

    public void setSenhafunc(String senhafunc) {
        this.senhafunc = senhafunc;
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */
