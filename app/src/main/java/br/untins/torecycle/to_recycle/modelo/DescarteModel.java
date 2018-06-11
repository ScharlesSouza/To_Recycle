package br.untins.torecycle.to_recycle.modelo;


import java.util.Date;

public class DescarteModel {

    public double latitude;
    public double longitude;
    public String descricao;
    public String datacadastro;
    public String material;

    public DescarteModel(double latitude, double longitude, String material, String descricao, String datacadastro) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
        this.datacadastro = datacadastro;
        this.setMaterial(material);
    }

    public DescarteModel(){


    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }



}