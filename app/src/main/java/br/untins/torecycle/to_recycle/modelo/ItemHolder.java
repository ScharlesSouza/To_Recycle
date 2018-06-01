package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class ItemHolder extends RecyclerView.ViewHolder {




    private TextView textoMaterial = null;


    public ItemHolder(View view){
        super(view);

        setTextoMaterial((TextView)view.findViewById(R.id.textViewMaterial));


    }

    public TextView getTextoMaterial() {
        return textoMaterial;
    }

    public void setTextoMaterial(TextView textoMaterial) {
        this.textoMaterial = textoMaterial;
    }
}
