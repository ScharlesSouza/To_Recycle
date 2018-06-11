package br.untins.torecycle.to_recycle.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.untins.torecycle.to_recycle.R;

public class LocalizacaoMapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Double latitude =null;
    //Double longitude = null;

    LatLng localizacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao_mapa);

        Bundle parametros = getIntent().getExtras();


        //verifica se os parametros passados entre as telas são diferente de nullo
        if(parametros!=null)
        {
            // Add as coordenadas vinda da tela de cadastro a uma variavel de coordenadas
            localizacao = new LatLng(parametros.getDouble("latitude"),parametros.getDouble("longitude"));

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng local;

        if(localizacao!=null){
            local= localizacao;
        }else{
            local= new LatLng(11.5448729, 104.8921668);
            Toast.makeText(this, " Localização deste dispositivo não encontrada", Toast.LENGTH_SHORT).show();
        }

        //marcador no mapa
        mMap.addMarker(new MarkerOptions().position(local).title("Local"));


        //posiciona o mapa na coordenada passada
        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(13).target(local).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //CameraUpdate atualizaVisaoCamera = CameraUpdateFactory.newLatLngZoom(localizacao, 6);
        //mMap.moveCamera(atualizaVisaoCamera);


    }


}
