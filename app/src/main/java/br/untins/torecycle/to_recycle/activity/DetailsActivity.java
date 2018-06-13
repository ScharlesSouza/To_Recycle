package br.untins.torecycle.to_recycle.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.untins.torecycle.to_recycle.R;


public class DetailsActivity extends AppCompatActivity {

    public TextView campoTexto , campoSubTitulo, campoConteudo;
    public ImageView campoImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        campoTexto = (TextView)findViewById(R.id.textTitulo);
        campoSubTitulo = (TextView)findViewById(R.id.textSubTitulo);
        campoConteudo = (TextView)findViewById(R.id.textViewConteudo);
        campoImagem = (ImageView)findViewById(R.id.imageDetalheResiduo);


        String Nome = this.getIntent().getStringExtra("Nome");
        //String Detalhes = this.getIntent().getStringExtra("Nome");
        //String Data = this.getIntent().getStringExtra("Nome");
        campoTexto.setText(Nome);
        campoSubTitulo.setText(Nome);
        campoConteudo.setText(this.getIntent().getStringExtra("descricao"));

        if (this.getIntent().getStringExtra("imagem").isEmpty()) {
            campoImagem.setImageResource(R.mipmap.ic_logo_to_recycle_2);
        } else{
            Picasso.with(this).load(this.getIntent().getStringExtra("imagem")).error(R.mipmap.ic_logo_to_recycle_2).into(campoImagem);
        }

    }
}
