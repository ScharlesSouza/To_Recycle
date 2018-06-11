package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class ItemDescarte extends RecyclerView.ViewHolder {




    private TextView textoMaterial = null;
    private TextView textoDescricao = null;


    public ItemDescarte(View view){
        super(view);

        setTextoMaterial((TextView)view.findViewById(R.id.textViewMaterial));
        setTextoDescricao((TextView)view.findViewById(R.id.textViewDescricao));

    }


    public TextView getTextoDescricao() {  return textoDescricao;    }

    public void setTextoDescricao(TextView textoDescricao) {    this.textoDescricao = textoDescricao;   }

    public TextView getTextoMaterial() {
        return textoMaterial;
    }

    public void setTextoMaterial(TextView textoMaterial) {
        this.textoMaterial = textoMaterial;
    }
}
