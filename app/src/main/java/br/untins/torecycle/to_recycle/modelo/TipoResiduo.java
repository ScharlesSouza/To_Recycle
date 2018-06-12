package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class TipoResiduo {


    String material;
    String Descricao;
    String urlImagem;
    String cor;


    public TipoResiduo(String sMaterial, String sDescricao, String imagem){

        setMaterial(sMaterial);
        setDescricao(sDescricao);
        setUrlImagem(imagem);

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

    public String getDescricao() {  return Descricao;  }

    public void setDescricao(String descricao) {    Descricao = descricao;   }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


}
