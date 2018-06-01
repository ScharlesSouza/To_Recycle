package br.untins.torecycle.to_recycle.fragmento;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.untins.torecycle.to_recycle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFrag extends Fragment {

    Spinner spinnerMaterial;
    Button btnCadastro;
    EditText campoDescricao;
    EditText campoLocalizacao;



    public CadastroFrag() {
        // Required empty public constructor

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_cadastro, null);

        spinnerMaterial = (Spinner) viewFragment.findViewById(R.id.SpinnerMaterial);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.ListaMaterial, android.R.layout.simple_spinner_item);
        spinnerMaterial.setAdapter(adapter);



        campoDescricao = (EditText)getActivity().findViewById(R.id.descricaoCadastro);
        campoLocalizacao = (EditText)getActivity().findViewById(R.id.localizacaoCadastro);


        // Chama a tela Cadastro Esvento
        Button btnCadastrar = (Button) viewFragment.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText txtDescricao = (EditText) viewFragment.findViewById(R.id.descricaoCadastro);
                //EditText txtLocalizacao = (EditText) viewFragment.findViewById(R.id.localizacaoCadastro);


                SQLiteDatabase db =   getContext().openOrCreateDatabase("ToRecycle.db", Context.MODE_PRIVATE, null);
                ContentValues ctv = new ContentValues();
                ctv.put("descricao", campoDescricao.getText().toString());
                ctv.put("localizacao", campoLocalizacao.getText().toString());
                ctv.put("material", spinnerMaterial.getSelectedItem().toString());

                db.insert("doacoes", "id", ctv);
                Toast.makeText(getContext(), " Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
            }
        });


        return viewFragment;

    }



    public Boolean cadastraDoacao(){




        return false;


    }


}