package br.untins.torecycle.to_recycle.modelo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;

public class ItemResiduo extends RecyclerView.ViewHolder {




    private TextView textoMaterial = null;
    private ImageView imageMaterial = null;


    public ItemResiduo(View view){
        super(view);

        setTextoMaterial((TextView)view.findViewById(R.id.textViewMaterial));
        setImageMaterial((ImageView) view.findViewById(R.id.imageViewResiduo));


    }

    public TextView getTextoMaterial() {
        return textoMaterial;
    }

    public void setTextoMaterial(TextView textoMaterial) {
        this.textoMaterial = textoMaterial;
    }

    public ImageView getImageMaterial() {
        return imageMaterial;
    }

    public void setImageMaterial(ImageView imageMaterial) {
        this.imageMaterial = imageMaterial;
    }
}
