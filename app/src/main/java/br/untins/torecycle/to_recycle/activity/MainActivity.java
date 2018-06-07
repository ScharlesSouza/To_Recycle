package br.untins.torecycle.to_recycle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.untins.torecycle.to_recycle.R;
import br.untins.torecycle.to_recycle.fragmento.CadastroDescarteFrag;
import br.untins.torecycle.to_recycle.fragmento.ListaResiduosFrag;
import br.untins.torecycle.to_recycle.fragmento.PevMapaFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //carrega o framento na view que recebera os containers
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ListaResiduosFrag()).commit();

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
            //carrega o framento na view que recebera os containers
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new br.untins.torecycle.to_recycle.fragmento.SobreFrag()).commit();
        } else if (id == R.id.nav_orgaos) {
            // Handle the camera action
        } else if (id == R.id.nav_leis) {
            // Handle the camera action
        } else if (id == R.id.nav_loja) {
            // Handle the camera action
        } else if (id == R.id.nav_parceiros) {
            // Handle the camera action
        } else if (id == R.id.nav_catadores) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PevMapaFrag()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_descarte) {
            //carrega o framento na view que recebera os containers
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CadastroDescarteFrag()).commit();
        } else if (id == R.id.nav_Locais) {
            // Handle the camera action
            Context contexto = this;
            Intent intent = new Intent(contexto, PevMapaActivity.class);
            contexto.startActivity(intent);
        } else if (id == R.id.nav_reciclaveis) {
            //carrega o framento na view que recebera os containers
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ListaResiduosFrag()).commit();

        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
