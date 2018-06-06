package br.untins.torecycle.to_recycle.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.untins.torecycle.to_recycle.R;

public class LocalizacaoMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Double latitude =null;
    Double longitude = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localizacao_maps);

        Bundle parametros = getIntent().getExtras();

        if(parametros!=null)
        {
            latitude = parametros.getDouble("latitude");
            longitude = parametros.getDouble("longitude");

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


        // Add as coordenadas a uma variavel de coordenadas
        LatLng location = new LatLng(latitude, longitude);

        //marcador no mapa
        mMap.addMarker(new MarkerOptions().position(location).title("Local"));


        //posiciona o mapa na coordenada passada
        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(6).target(location).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //CameraUpdate atualizaVisaoCamera = CameraUpdateFactory.newLatLngZoom(location, 6);
        //mMap.moveCamera(atualizaVisaoCamera);


    }


}
