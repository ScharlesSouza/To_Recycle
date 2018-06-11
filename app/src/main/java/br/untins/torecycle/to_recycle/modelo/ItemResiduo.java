package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class ItemResiduo extends RecyclerView.ViewHolder {




    private TextView textoMaterial = null;


    public ItemResiduo(View view){
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
