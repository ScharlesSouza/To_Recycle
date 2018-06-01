package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class LixoItemLista {


    String material;
    String cor;




    public LixoItemLista( String sMaterial){

       setMaterial(sMaterial);


    }



    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


}
