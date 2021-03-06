package br.untins.torecycle.to_recycle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.untins.torecycle.to_recycle.R;
import br.untins.torecycle.to_recycle.fragmento.CadastroDescarteFrag;
import br.untins.torecycle.to_recycle.fragmento.ListaDescarteFrag;
import br.untins.torecycle.to_recycle.fragmento.ListaResiduosFrag;
import br.untins.torecycle.to_recycle.fragmento.PevMapaFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    android.support.v4.app.FragmentManager fragMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
           */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragMgr = this.getSupportFragmentManager();
        //carrega o framento na view que recebera os containers
        fragMgr.beginTransaction().replace(R.id.fragmentContainer, new ListaResiduosFrag(), "ListaResiduo").commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sobre) {
            //Fragmento Sobre o aplicativo
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new br.untins.torecycle.to_recycle.fragmento.SobreFrag(), "Sobre").commit();
        } else if (id == R.id.nav_reciclaveis) {
            //Lista dos tipos de residuos
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ListaResiduosFrag(), "ListaResiduo").commit();
        } else if (id == R.id.nav_Locais) {
            //mapa dos pontos (locais) de entrega voluntaria - Activity
            Context contexto = this;
            Intent intent = new Intent(contexto, PevMapaActivity.class);
            contexto.startActivity(intent);

            //mapa dos pontos (locais) de entrega voluntaria -Fragmento
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PevMapaFrag(), "PevMapa").commit();

        } else if (id == R.id.nav_descarte) {
            //Lista dos descartes do usuarios do app
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ListaDescarteFrag(),"ListaDescarte").commit();
            //Cadastro de descarte
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CadastroDescarteFrag(), "CadastroDescarte").commit();

        } else if (id == R.id.nav_catadores) {

        } else if (id == R.id.nav_orgaos) {

        } else if (id == R.id.nav_parceiros) {

        } else if (id == R.id.nav_loja) {

        } else if (id == R.id.nav_leis) {

        }

    /*
        else if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
    */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public String pegaFragmentoAtual(){
        List<android.support.v4.app.Fragment> fragments = fragMgr.getFragments();
        Fragment visibleFragment = fragments.get(0);
        switch (visibleFragment.getTag()){
            case "Tag1":

                break;
            case "Tag2":

                break;

        }
        return "vazio";

    }
}
