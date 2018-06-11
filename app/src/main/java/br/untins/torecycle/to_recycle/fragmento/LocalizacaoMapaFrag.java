package br.untins.torecycle.to_recycle.fragmento;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.untins.torecycle.to_recycle.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalizacaoMapaFrag extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng localizacao;

    //CONSTRUTOR DA CLASSE
    public LocalizacaoMapaFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_localizacao_mapa, container, false);

        Bundle parametros;
        parametros = getArguments();
        if(parametros!=null)
        {
            // Add as coordenadas a uma variavel de coordenadas
            localizacao = new LatLng(parametros.getDouble("latitude"),parametros.getDouble("longitude"));

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapaLocalizacao);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng local;

        if(localizacao!=null){
            local= localizacao;
        }else{
            local= new LatLng(11.5448729, 104.8921668);
            Toast.makeText(getContext(), " Localização deste dispositivo não encontrada", Toast.LENGTH_SHORT).show();
        }

        MarkerOptions options = new MarkerOptions();
        options.position(local).title("Local");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local,13));

    }
}
