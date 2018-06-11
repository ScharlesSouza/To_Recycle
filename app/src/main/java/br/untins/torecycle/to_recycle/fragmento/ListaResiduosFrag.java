package br.untins.torecycle.to_recycle.fragmento;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

import br.untins.torecycle.to_recycle.R;
import br.untins.torecycle.to_recycle.activity.DetailsActivity;
import br.untins.torecycle.to_recycle.modelo.ItemResiduo;
import br.untins.torecycle.to_recycle.modelo.TipoResiduo;


public class ListaResiduosFrag extends Fragment {

    //private RecyclerView meuRecycleView;
    //private ArrayList<TipoResiduo> lista = null;

    ArrayList<TipoResiduo> dataSource = null;
    View viewFragment = null;
    RecyclerView lista = null;



    public ListaResiduosFrag() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.fragment_lista_residuos, null);
/*
        //Acesso ao WebService
        BuscaDadosWebServices busca = new BuscaDadosWebServices(getContext());
        //BuscaDadosWebServices busca = new BuscaDadosWebServices();
        busca.execute("https://drive.google.com/drive/folders/1DFGu3qzzzfxgSyB4KprzepZFGW1fF5I2");

*/
        dataSource = new ArrayList<>();


        //Adicionando fixamente os itens, exemplo

        dataSource.add(new TipoResiduo("Papel"));
        dataSource.add(new TipoResiduo("Plástico"));
        dataSource.add(new TipoResiduo("Metal"));
        dataSource.add(new TipoResiduo("Vidro"));
        dataSource.add(new TipoResiduo("Orgânico"));
        dataSource.add(new TipoResiduo("Lixo não Reciclável"));
        dataSource.add(new TipoResiduo("Eletronico"));
        dataSource.add(new TipoResiduo("Hospitalar"));
        dataSource.add(new TipoResiduo("Entulho"));
        dataSource.add(new TipoResiduo("Doações"));


        lista = (RecyclerView)viewFragment.findViewById(R.id.listaPrincipal);
        lista.setLayoutManager(new LinearLayoutManager(getActivity()));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        AdaptadorResiduos adapt = new AdaptadorResiduos(getActivity(), dataSource);
        adapt.notifyDataSetChanged();
        lista.setAdapter(adapt);

        return viewFragment;

    }



    /*

                                                    //Parametros/Progesso/Resultado
        public class BuscaDadosWebServices extends AsyncTask<String, Void, ArrayList<TipoResiduo>> {

            private Context meuContexto;
            private View minhaView;

            public BuscaDadosWebServices(Context contexto){
                meuContexto = contexto;
                //minhaView = view;

            }

            public BuscaDadosWebServices(){

            }




            //Representa o codigo a ser executado antes da thread inicializar
            //Executado na MAIN THREAD
            @Override
            protected void onPreExecute() {
                Log.i("INFO", "ANTES DA THREAD");
            }





            //Metodo chamado durante execução da thread
            //Onde o processamento paralelo é executado
            //Retorna um objeto qualquer pelo POST EXECUTE

            @Override
            protected ArrayList<TipoResiduo> doInBackground(String... strings) {

                ArrayList<TipoResiduo> vetorCarros = null;

                Log.i("INFO", "DURANTE DA THREAD");
                String jSon = "";
                Gson gson= new Gson();
                RecyclerView lista = null;
                try {
                    OkHttpClient cliente = new OkHttpClient() ;
                    Request requisicao = new Request.Builder().url(strings[0]).build();
                    Response resposta =  cliente.newCall(requisicao).execute();
                    jSon = resposta.body().string();

                    vetorCarros = gson.fromJson(jSon, new TypeToken<ArrayList<TipoResiduo>>(){         }.getType());



                } catch (IOException e) {
                    e.printStackTrace();
                    int error = 0;
                }


                return vetorCarros;
            }




            //Chamado após a execução da thread
            //Executa na MAIN THREAD
            protected void onPostExecute(ArrayList<TipoResiduo> materialLixo) {

                Log.i("INFO", "DEPOIS DA THREAD");


                RecyclerView listaRecycleView = null;
                listaRecycleView = (RecyclerView) viewFragment.findViewById(R.id.listaPrincipal);
                listaRecycleView.setLayoutManager(new LinearLayoutManager(meuContexto));
                listaRecycleView.setItemAnimator(new DefaultItemAnimator());
                listaRecycleView.setHasFixedSize(true);


                AdaptadorDescarte adapt = new AdaptadorDescarte(meuContexto, materialLixo);
                listaRecycleView.setAdapter(adapt);


            }


        }

        */

    public class AdaptadorResiduos extends RecyclerView.Adapter<ItemResiduo>{

        Context contexto = null;
        ArrayList<TipoResiduo> lista = null;
        private AlertDialog alerta;

        public AdaptadorResiduos(Context contexto, ArrayList<TipoResiduo> lista){

            this.contexto = contexto;
            this.lista = lista;
        }

        //METODO CHAMADO N VEZES PARA INFLAR O XML DA CELULA E RETORNAR UM OBJETO DE LAYOUT
        /* Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view. */
        //@NonNull
        @Override
        public ItemResiduo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View celula = LayoutInflater.from(contexto).inflate(R.layout.item_residuo, parent,false );
            ItemResiduo item = new ItemResiduo(celula);
            return item;
        }
        /*
         * Método que recebe o ViewHolder e a posição da lista.
         * Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
         * É onde a mágica acontece!
         * */
        @Override
        public void onBindViewHolder(@NonNull ItemResiduo holder, final int position) {
            TipoResiduo item = lista.get(position);

            holder.getTextoMaterial().setText(item.getMaterial());

            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    contexto = view.getContext();
                    Intent intent = new Intent(contexto, DetailsActivity.class);
                    String posicao = Integer.toString(position);
                    Log.d("posicao", posicao);
                    intent.putExtra("Nome", lista.get(position).getMaterial());



                    contexto.startActivity(intent);


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

