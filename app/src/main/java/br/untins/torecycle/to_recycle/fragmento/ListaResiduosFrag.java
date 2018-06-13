package br.untins.torecycle.to_recycle.fragmento;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

        dataSource.add(new TipoResiduo("Papel",
                "Papel - Tempo de decomposição (03 a 06 meses)" +
                        "\nA reciclagem do papel é de extrema importância para o meio ambiente. Como sabemos, o papel é produzido através da celulose de determinados tipos de árvores. Quando reciclamos o papel ou compramos papel reciclado estamos contribuindo com o meio ambiente, pois árvores deixaram de ser cortadas.",
                "https://lh6.googleusercontent.com/gfMLw4Lub59tFk3ERLHxXj8zvZDq2na0xPr9lwkGqWALN7wzO7euPZODA_vZ9VyKKFa-VaNdzfIK9pk_84iJhgl103RNbw=w808-h954"));
        dataSource.add(new TipoResiduo("Plástico",
                "Plastico - Tempo de decomposicao (450 anos)" +
                        "\nA reciclagem de plástico consiste no processo de reciclagem de artefatos fabricados a partir de resinas (polímeros), geralmente sintéticas e derivadas do petróleo.",
                "https://lh6.googleusercontent.com/uXm-JP8YABDqit6N91L4phc0PkCxC1uJpMLospD0HwRwCsFA9LycENiGwRW0dpDGVXuXxY5N4W3KnW4lVSss5H1qPD49Cw=w808-h954"));
        dataSource.add(new TipoResiduo("Metal",
                "Metal - Tempo de decomposição ( + 100 anos)" +
                        "\nO metal é um dos produtos mais utilizados nas tarefas do dia-a-dia. Encontramos embalagens de metais, fios e outros produtos metálicos em diversos produtos. Ao ser descartado por pessoas e empresas, pode passar por um processo de reciclagem que garante seu reaproveitamento na produção do metal reciclado. O aço após ser reciclado volta para a cadeia produtiva para ser transformado em latas e peças automotivas, por exemplo.",
                "https://lh5.googleusercontent.com/h05jsS5cN-wUbO0GUIV1rLVYr2jWAgg9Fd2f2ywPO1QTsfv-80XZODfurLCv8fqQMkUfoXneyQ4viYOVTmUNXT7eql_MDA=w808-h954"));
        dataSource.add(new TipoResiduo("Vidro",
                "Vidro - Tempo de decomposição (1 milhão de ano)" +
                        "\nO vidro é um dos produtos mais utilizados nas tarefas do dia-a-dia. Ao ser descartado por pessoas e empresas, pode passar por um processo de reciclagem que garante seu reaproveitamento na produção do vidro reciclado. O vidro reciclado tem praticamente todas as características do vidro comum. Ele pode ser reciclado muitas vezes sem perder sua características e qualidade.",
                "https://lh6.googleusercontent.com/sW36VZJNUV5swHJAszDWHT-njk8C4M-8YfW-JpmhKX8oPc9Eg88qfzKqDGWYWZqLeU5t5R96J73IMDQpPmz1zWXH9wBQ_g=w808-h954"));
        dataSource.add(new TipoResiduo("Orgânico",
                "Orgânico - Tempo de decomposição (1 a 3 meses)" +
                        "\nLixo orgânico é todo resíduo de origem vegetal ou animal, ou seja, todo lixo originário de um ser vivo. Este tipo de lixo é produzido nas residências, escolas, empresas e pela natureza.",
                "https://lh4.googleusercontent.com/fvusIqmJDSZhuO4ZvPFCA_vLBgKeO2fLBuJVA9mZSnsU6aBeonqCMarvZXRB3oZSFm_4DQDZNel31fUGFqDJ8GcayLtocw=w808-h954"));
        dataSource.add(new TipoResiduo("Lixo não Reciclável",
                "Lixo não Reciclável" +
                        "\nA reciclagem é um ato de extrema importância nos dias atuais. Além de ajudar na preservação do meio ambiente, gera renda para milhares de pessoas. Porém, por questões técnicas, nem todos os materiais descartados por pessoas ou indústrias podem passar pelo processo de reciclagem. Estes, após passarem por processos industriais, não podem ser reutilizados. Grande parte destes materiais não recicláveis tem como destino o lixo comum.\n" +
                        "Exemplos de lixo eletrônico:\n" +
                        "\n" +
                        "- Monitores de Computadores\n" +
                        "\n" +
                        "- Telefones Celulares e baterias\n" +
                        "\n" +
                        "- Computadores\n" +
                        "\n" +
                        "- Televisores\n" +
                        "\n" +
                        "- Câmeras Fotográficas\n" +
                        "\n" +
                        "- Impressoras",
                "https://lh3.googleusercontent.com/xP82Q_3LZ-QBvjgZmxDweYmeyEwVZzSx3YAEWpwscOwcyqF7nOvKoam1Mz63CPViQ4eJ-41MvmubmWiIkBINlFPcwrx3nQ=w808-h954"));
        dataSource.add(new TipoResiduo("Eletronico",
                "Lixo Eletrônico" +
                        "\nOs resíduos de serviços de saúde (RSS), comumente associados à denominação lixo hospitalar ou resíduo hospitalar, é o nome que se dá aos resíduos originários de ações médicas desenvolvidas em unidades de prestação de cuidados de saúde, em atividades de prevenção, diagnóstico, tratamento, reabilitação e investigação relacionada com seres humanos ou animais, em farmácias, em atividades médico-legais, de ensino e em quaisquer outras que envolvam procedimentos invasivos\n",
                "https://lh6.googleusercontent.com/FUXjgf907A-XSDPCbPCklcN20eruMwISORsYTzEnX2b3zVzxLzlA0vN9D66eCiOJ29mvRh7eJa_tGEtLEGpCE0lE1MTPZA=w808-h954"));
        dataSource.add(new TipoResiduo("Hospitalar",
                "","https://lh5.googleusercontent.com/9R-f6KfszrjP_HuXw571WfubTt2IPVKomimwzKW8zWM7EwxH9nyFyTVL2A68thGxq-iwJ6-3jH6qxC1VBgWDD5mIl7ZqeQ=w808-h954"));
        dataSource.add(new TipoResiduo("Entulho",
                "",""));
        dataSource.add(new TipoResiduo("Doações",
                "",""));


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
            if (item.getUrlImagem().isEmpty()) {
                holder.getImageMaterial().setImageResource(R.mipmap.ic_logo_to_recycle_2);
            } else{
                Picasso.with(contexto).load(item.getUrlImagem()).error(R.mipmap.ic_logo_to_recycle_2).into(holder.getImageMaterial());
            }



            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    contexto = view.getContext();
                    Intent intent = new Intent(contexto, DetailsActivity.class);
                    String posicao = Integer.toString(position);
                    Log.d("posicao", posicao);
                    intent.putExtra("Nome", lista.get(position).getMaterial());
                    intent.putExtra("descricao",lista.get(position).getDescricao());
                    intent.putExtra("imagem", lista.get(position).getUrlImagem());

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


