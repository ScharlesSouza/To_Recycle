package br.untins.torecycle.to_recycle.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.untins.torecycle.to_recycle.R;


public class DetailsActivity extends AppCompatActivity {

    public TextView campoTexto , campoSubTitulo, campoConteudo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        campoTexto = (TextView)findViewById(R.id.textTitulo);
        campoSubTitulo = (TextView)findViewById(R.id.textSubTitulo);
        campoConteudo = (TextView)findViewById(R.id.textViewConteudo);


        String Nome = this.getIntent().getStringExtra("Nome");
        //String Detalhes = this.getIntent().getStringExtra("Nome");
        //String Data = this.getIntent().getStringExtra("Nome");
        campoTexto.setText(Nome);
        campoSubTitulo.setText(Nome);

    }
}
