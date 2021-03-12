package com.example.systemstrength.Classes.Clientes;

/**
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 **/

public class DtoClientes {
    int id;
    String nomecliente, enderecocliente, telefonecliente, emailcliente, cnpjcliente;

    public DtoClientes(int id, String nomecliente, String enderecocliente, String telefonecliente, String emailcliente, String cnpjcliente) {
        this.id = id;
        this.nomecliente = nomecliente;
        this.enderecocliente = enderecocliente;
        this.telefonecliente = telefonecliente;
        this.emailcliente = emailcliente;
        this.cnpjcliente = cnpjcliente;
    }

    public DtoClientes(){}

    @Override
    public String toString() {
        return "\n" + "• " + nomecliente + "\n" +"• " + emailcliente + "\n" + "• " + cnpjcliente + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getEnderecocliente() {
        return enderecocliente;
    }

    public void setEnderecocliente(String enderecocliente) {
        this.enderecocliente = enderecocliente;
    }

    public String getTelefonecliente() {
        return telefonecliente;
    }

    public void setTelefonecliente(String telefonecliente) {
        this.telefonecliente = telefonecliente;
    }

    public String getEmailcliente() {
        return emailcliente;
    }

    public void setEmailcliente(String emailcliente) {
        this.emailcliente = emailcliente;
    }

    public String getCnpjcliente() {
        return cnpjcliente;
    }

    public void setCnpjcliente(String cnpjcliente) {
        this.cnpjcliente = cnpjcliente;
    }
}

/*
 *  Copyright (c) 2020 System Strength
 *  Official repository https://github.com/System-Strength/Mobile
 *  Responsible developer: https://github.com/Kauavitorio
 */
