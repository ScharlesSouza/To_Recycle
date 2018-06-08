package br.untins.torecycle.to_recycle.fragmento;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.untins.torecycle.to_recycle.R;
import br.untins.torecycle.to_recycle.activity.LocalizacaoMapaActivity;
import br.untins.torecycle.to_recycle.modelo.DescarteModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroDescarteFrag extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    Spinner spinnerMaterial;
    Button btnCadastro;
    EditText campoDescricao;
    TextView txtLatitude, txtLongitude, txtCidade, txtEstado, txtPais;

    private Location location;
    private LocationManager locationManager;
    private Address endereco;


    private double latitude;
    private double longitude;

    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mDatabase;

    /*###################################################################################################*/
    //GET E SET DA LATITUDE
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    /*###################################################################################################*/

    //CONSTRUTOR
    public CadastroDescarteFrag() {
        // Required empty public constructor

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_cadastro, null);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        spinnerMaterial = (Spinner) viewFragment.findViewById(R.id.SpinnerMaterial);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.ListaMaterial, android.R.layout.simple_spinner_item);
        spinnerMaterial.setAdapter(adapter);

        campoDescricao = (EditText) viewFragment.findViewById(R.id.descricaoCadastro);


        //teste de localização
        txtLatitude = (TextView) getActivity().findViewById(R.id.textLatitude);
        txtLongitude = (TextView) getActivity().findViewById(R.id.textLongitude);
        txtCidade = (TextView) getActivity().findViewById(R.id.textCidade);
        txtEstado = (TextView) getActivity().findViewById(R.id.textEstado);
        txtPais = (TextView) getActivity().findViewById(R.id.textPais);


        //Metodo que verifica se o GPS ou WIFI esta habilitado e com permissão para pegar a localização, alem disso chama o metodo de atualização de localização
        startGettingLocations();

        //UTILIZA A LOCATION_API PARA PEGAR A LOCALIZAÇÃO ATUAL DO DISPOSITIVO
        callConnection();


        //chama o mapa com a localização atual do usuario
        Button btnLocalizacao = (Button) viewFragment.findViewById(R.id.btnLocalizacao);
        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle parametros = new Bundle();

                //chama a proxima tela
                Context contexto = null;
                contexto = getContext();
                Intent intent = new Intent(contexto, LocalizacaoMapaActivity.class);

                parametros.putDouble("latitude", latitude);
                parametros.putDouble("longitude", longitude);

                intent.putExtras(parametros);

                contexto.startActivity(intent);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LocalizacaoMapaFrag()).commit();
            }
        });


        // Chama a tela Cadastro Esvento
        Button btnCadastrar = (Button) viewFragment.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SQLiteDatabase db = getContext().openOrCreateDatabase("ToRecycle.db", Context.MODE_PRIVATE, null);
                //ContentValues ctv = new ContentValues();
                //ctv.put("descricao", campoDescricao.getText().toString());
                //ctv.put("material", spinnerMaterial.getSelectedItem().toString());
                //db.insert("doacoes", "id", ctv);

                SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");


                DescarteModel descarte = new DescarteModel(latitude, longitude, campoDescricao.getText().toString(), formataData.format(new Date()));
                mDatabase.child(endereco.getCountryName()).child(endereco.getAdminArea()).child(endereco.getLocality()).child(spinnerMaterial.getSelectedItem().toString())
                        .child(String.valueOf(new Date())).setValue(descarte);

                Toast.makeText(getContext(), " Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        //retorno da ViewCreate
        return viewFragment;
    }


    //METODO QUE BUSCA ENDEREÇO NA API DO GOOGLE A PARTIR DE UMA LOCALIZAÇÃO (LATITUDE, LONGITUDE)
    public Address buscarEnderecoGPS(double lati, double longi) throws IOException {
        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;

        geocoder = new Geocoder(getActivity().getApplicationContext());
        addresses = geocoder.getFromLocation(lati, longi, 1);
        if (addresses.size() > 0) {
            address = addresses.get(0);
        }
        return address;
    }

    //METODO QUE UTILIZA O SYCRONIZED DO JAVA, PARA FAZER UMA CONEXÃO
    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }


    private void startGettingLocations() {

        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean canGetLocation = true;
        int ALL_PERMISSIONS_RESULT = 101;
        long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;// Distance in meters
        long MIN_TIME_BW_UPDATES = 1000 * 10;// Time in milliseconds

        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> permissionsToRequest;

        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        //Check if GPS and Network are on, if not asks the user to turn on
        if (!isGPS && !isNetwork) {
            showSettingsAlert();
        } else {
            // check permissions

            // checa permissçoes para dispositivos que utilize android acima da versão L (LOLLIPOP)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    canGetLocation = false;
                }
            }
        }


        //Checks if FINE LOCATION and COARSE Location were granted
        if (ActivityCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(getActivity(), "Permissão negada", Toast.LENGTH_SHORT).show();
            return;
        }

        //Starts requesting location updates
        if (canGetLocation) {
            if (isGPS) {
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            } else if (isNetwork) {
                // from Network Provider

                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            }
        } else {
            Toast.makeText(getActivity(), "Não é possível obter a localização", Toast.LENGTH_SHORT).show();
        }
    }


    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (ActivityCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("GPS desativado!");
        alertDialog.setMessage("Ativar GPS?");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    /*##################################################################################################################################################################################################*/
    //SOBRECARGA DOS METODOS DO GOOGLE_API_CLIENT
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected(" + bundle + ")");

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location local = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        txtLatitude = (TextView) getActivity().findViewById(R.id.textLatitude);
        txtLongitude = (TextView) getActivity().findViewById(R.id.textLongitude);
        txtCidade = (TextView) getActivity().findViewById(R.id.textCidade);
        txtEstado = (TextView) getActivity().findViewById(R.id.textEstado);
        txtPais = (TextView) getActivity().findViewById(R.id.textPais);


        if (local != null){

            setLatitude(local.getLatitude());
            setLongitude(local.getLongitude());

            Log.i("LOG", "latitude: " + local.getLatitude());
            Log.i("LOG", "longitude: "+ local.getLongitude());
            txtLatitude.setText("Latitude: "+ local.getLatitude());
            txtLongitude.setText("Longitude: "+ local.getLongitude());

            try {
                endereco = buscarEnderecoGPS(local.getLatitude(), local.getLongitude());

                txtCidade.setText("Cidade: "+ endereco.getLocality());
                txtEstado.setText("Estado: "+endereco.getAdminArea());
                txtPais.setText("País: "+endereco.getCountryName());

            }catch (IOException e){
                Log.i("GPS", e.getMessage());
            }

        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectedSuspend(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }//FIM DOS METODOS DO GOOGLE_API_CLIENT


    /*################################################################################################################*/
    //SOBRECARGA DOS METODOS DA CLASSE LOCATIONLISTENER,PARA QUANDO A LOCALIZAÇÃO MUDAR
    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location local = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        txtLatitude = (TextView) getActivity().findViewById(R.id.textLatitude);
        txtLongitude = (TextView) getActivity().findViewById(R.id.textLongitude);
        txtCidade = (TextView) getActivity().findViewById(R.id.textCidade);
        txtEstado = (TextView) getActivity().findViewById(R.id.textEstado);
        txtPais = (TextView) getActivity().findViewById(R.id.textPais);


        if (local != null){
            Log.i("LOG", "latitude: " + local.getLatitude());
            Log.i("LOG", "longitude: "+ local.getLongitude());
            txtLatitude.setText("Latitude: "+ local.getLatitude());
            txtLongitude.setText("Longitude: "+ local.getLongitude());

            try {
                endereco = buscarEnderecoGPS(local.getLatitude(), local.getLongitude());

                txtCidade.setText("Cidade: "+ endereco.getLocality());
                txtEstado.setText("Estado: "+endereco.getAdminArea());
                txtPais.setText("País: "+endereco.getCountryName());

            }catch (IOException e){
                Log.i("GPS", e.getMessage());
            }

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }//FIM DOS METODOS DA CLASSE LOCATIONLISTENER




/*

    //GRAVAR OS PONTOS DAS LOCALIZAÇÕES AO MUDAR DE POSIÇÃO
    @Override
    public void onLocationChanged(Location location) {

        if (currentLocationMaker != null) {
            currentLocationMaker.remove();
        }
        //Add marker
        currentLocationLatLong = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLocationLatLong);
        markerOptions.title("Localização atual");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMaker = mMap.addMarker(markerOptions);

        //Move to new location
        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(currentLocationLatLong).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        DescarteModel locationData = new DescarteModel(location.getLatitude(), location.getLongitude());
        mDatabase.child("location").child(String.valueOf(new Date().getTime())).setValue(locationData);

        Toast.makeText(this, "Localização atualizada", Toast.LENGTH_SHORT).show();
        getMarkers();

    }

    //METHODO PARA BUSCAR CADA PONTO GRAVADO NO BANCO DE DADOS
    private void getMarkers(){

        mDatabase.child("location").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        if (dataSnapshot.getValue() != null)
                            getAllLocations((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    //METODO COM todos OS PONTOS GRAVADOS NO BANCO DE DADOS
    private void getAllLocations(Map<String,Object> locations) {


        for (Map.Entry<String, Object> entry : locations.entrySet()){

            Date newDate = new Date(Long.valueOf(entry.getKey()));
            Map singleLocation = (Map) entry.getValue();
            LatLng latLng = new LatLng((Double) singleLocation.get("latitude"), (Double)singleLocation.get("longitude"));
            addGreenMarker(newDate, latLng);

        }


    }

    private void addGreenMarker(Date newDate, LatLng latLng) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(dt.format(newDate));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerOptions);
    }

*/
}