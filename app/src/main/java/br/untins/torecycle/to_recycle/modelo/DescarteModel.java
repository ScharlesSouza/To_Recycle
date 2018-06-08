package br.untins.torecycle.to_recycle.modelo;


import java.util.Date;

public class DescarteModel {

    public double latitude;
    public double longitude;
    public String descricao;
    public String datacadastro;

    public DescarteModel(double latitude, double longitude, String descricao, String datacadastro) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
        this.datacadastro = datacadastro;
    }
}