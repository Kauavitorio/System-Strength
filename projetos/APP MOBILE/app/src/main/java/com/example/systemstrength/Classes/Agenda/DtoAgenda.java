package com.example.systemstrength.Classes.Agenda;

public class DtoAgenda {
    int id;
    String dataagendamento, horaagendamento, descricaoagendamento, localdoagendamento, nomecliente, cnpjcliente;

    public DtoAgenda(int id, String dataagendamento, String horaagendamento, String descricaoagendamento, String localdoagendamento, String nomecliente, String cnpjcliente) {
        this.id = id;
        this.dataagendamento = dataagendamento;
        this.horaagendamento = horaagendamento;
        this.descricaoagendamento = descricaoagendamento;
        this.localdoagendamento = localdoagendamento;
        this.nomecliente = nomecliente;
        this.cnpjcliente = cnpjcliente;
    }

    public DtoAgenda(){}

    @Override
    public String toString() {
        return "\n" + "• Cliente: " + nomecliente + "\n" + "• Horário: " + horaagendamento + "\n" +"• Data: " + dataagendamento + "\n" +"• Local: " + localdoagendamento + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataagendamento() {
        return dataagendamento;
    }

    public void setDataagendamento(String dataagendamento) {
        this.dataagendamento = dataagendamento;
    }

    public String getHoraagendamento() {
        return horaagendamento;
    }

    public void setHoraagendamento(String horaagendamento) {
        this.horaagendamento = horaagendamento;
    }

    public String getDescricaoagendamento() {
        return descricaoagendamento;
    }

    public void setDescricaoagendamento(String descricaoagendamento) {
        this.descricaoagendamento = descricaoagendamento;
    }

    public String getLocaldoagendamento() {
        return localdoagendamento;
    }

    public void setLocaldoagendamento(String localdoagendamento) {
        this.localdoagendamento = localdoagendamento;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getCnpjcliente() {
        return cnpjcliente;
    }

    public void setCnpjcliente(String cnpjcliente) {
        this.cnpjcliente = cnpjcliente;
    }
}
