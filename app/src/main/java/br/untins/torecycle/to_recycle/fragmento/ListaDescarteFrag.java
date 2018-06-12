package br.untins.torecycle.to_recycle.fragmento;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.untins.torecycle.to_recycle.R;
import br.untins.torecycle.to_recycle.activity.DetailsActivity;
import br.untins.torecycle.to_recycle.modelo.DescarteModel;
import br.untins.torecycle.to_recycle.modelo.ItemDescarte;
import br.untins.torecycle.to_recycle.modelo.ItemResiduo;
import br.untins.torecycle.to_recycle.modelo.TipoResiduo;


public class ListaDescarteFrag extends Fragment {



    ArrayList<DescarteModel> dataSource = null;
    View viewFragment = null;
    RecyclerView lista = null;
    FloatingActionButton botaoFlutuante = null;
    private DatabaseReference mDatabase;



    public ListaDescarteFrag() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.fragment_lista_descarte, null);
        mDatabase = FirebaseDatabase.getInstance().getReference();


/*
        //Acesso ao WebService
        BuscaDadosWebServices busca = new BuscaDadosWebServices(getContext());
        //BuscaDadosWebServices busca = new BuscaDadosWebServices();
        busca.execute("https://drive.google.com/drive/folders/1DFGu3qzzzfxgSyB4KprzepZFGW1fF5I2");

*/
        dataSource = new ArrayList<>();

        lista = (RecyclerView)viewFragment.findViewById(R.id.listaDescarte);
        lista.setLayoutManager(new LinearLayoutManager(getActivity()));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        mDatabase.child("Brasil").child("Tocantins").child("Palmas").child("Descarte").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot objSnapShot: dataSnapshot.getChildren()){

                            for(DataSnapshot objSnapShot2: objSnapShot.getChildren()){
                                DescarteModel descarte = objSnapShot2.getValue(DescarteModel.class);

                                dataSource.add(descarte);

                                Log.i("TAG","");
                            }
                        }

                        AdaptadorDescarte adapt = new AdaptadorDescarte(getActivity(), dataSource);
                        adapt.notifyDataSetChanged();
                        lista.setAdapter(adapt);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("LOG", String.valueOf(databaseError));
                        //handle databaseError
                    }
                });

        botaoFlutuante = viewFragment.findViewById(R.id.descarte_flutuante);
        botaoFlutuante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CadastroDescarteFrag(), "CadastroDescarte").commit();
            }
        });

        return viewFragment;

    }




    public class AdaptadorDescarte extends RecyclerView.Adapter<ItemDescarte>{

        Context contexto = null;
        ArrayList<DescarteModel> lista = null;
        private AlertDialog alerta;

        public AdaptadorDescarte(Context contexto, ArrayList<DescarteModel> lista){

            this.contexto = contexto;
            this.lista = lista;
        }

        //METODO CHAMADO N VEZES PARA INFLAR O XML DA CELULA E RETORNAR UM OBJETO DE LAYOUT
        /* Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view. */
        //@NonNull
        @Override
        public ItemDescarte onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View celula = LayoutInflater.from(contexto).inflate(R.layout.item_descarte, parent,false );
            ItemDescarte item = new ItemDescarte(celula);
            return item;
        }

        /*
         * Método que recebe o ViewHolder e a posição da lista.
         * Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
         * É onde a mágica acontece!
         * */
        @Override
        public void onBindViewHolder(@NonNull ItemDescarte holder, final int position) {
            DescarteModel item = lista.get(position);

            holder.getTextoMaterial().setText(item.getMaterial());
            holder.getTextoDescricao().setText(item.getDescricao());

            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    //defini um fragmento a ser chamado futuramente
                    CadastroDescarteFrag proximoFrag = new CadastroDescarteFrag();

                    //chamada da proxima tela que contem o mapa com a localização atual do dispositivo
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, proximoFrag).commit();

                }
            });

           holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                    builder.setTitle("Titulo");
                    //define a mensagem
                    builder.setMessage("Apagar " + lista.get(position).getMaterial());
                    //define um botão como positivo
                    builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //NetworkUtils.Apagar(lista.get(position));
                            Toast.makeText(contexto, lista.get(position).getMaterial() +" Apagada", Toast.LENGTH_SHORT).show();

                        }
                    });
                    //define um botão como negativo.
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    //cria o AlertDialog
                    alerta = builder.create();
                    //Exibe
                    alerta.show();

                    return true;
                }

            });

        }


        //METODO QUE DETERMINA QUANTOS ITENS VAI TER NA LISTA
        /*
         * Método que deverá retornar quantos itens há na lista.
         * Aconselha-se verificar se a lista não está nula como no exemplo,
         * pois ao tentar recuperar a quantidade da lista nula pode gerar
         * um erro em tempo de execução (NullPointerException).
         * */
        @Override
        public int getItemCount() {

            return (lista != null)? lista.size() : 0;
        }


    }
}


