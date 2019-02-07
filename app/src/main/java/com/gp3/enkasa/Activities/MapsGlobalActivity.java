package com.gp3.enkasa.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.Models.Json.Models.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.gp3.enkasa.R;

public class MapsGlobalActivity extends FragmentActivity implements OnMapReadyCallback {

    private ArrayList<Alojamientos> alojamientos;
    private GoogleMap mMap;

    private Marker marcador;
    double lat = 0.0;
    double log = 0.0;
    String mensaje1 = "";
    String direccion = "";

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect,MapsGlobalActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        alojamientos=AlojamientosActivity.jsonData.getData().getAlojamientos();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Activar los permisos del gps cuando esten apagados
    private void locationStart() {
        LocationManager mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) ;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        return;

    }

    public void setLocalitation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if ((!list.isEmpty())) {
                    Address dirCalle = list.get(0);
                    direccion = (dirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Agregar el marcador en el mapa
    private void agregarMarcador(double lar, double log) {
        LatLng coordenadas = new LatLng(lar, log);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 14);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Direccion:" + direccion)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(miubicacion);

    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            log = location.getLongitude();
            agregarMarcador(lat, log);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
            setLocalitation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            mensaje1 = ("GPS Activado");
            mensaje();
        }

        @Override
        public void onProviderDisabled(String provider) {
            mensaje1 = ("GPS Desactivado");
            locationStart();
        }
    };
    //Obtener mi ubicacion
    private static int PETICION_PERMISO_UBICACION = 101;

    private void miUbicacion() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PETICION_PERMISO_UBICACION);

            return;
        }else{
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            actualizarUbicacion(location);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1200,0,locListener);
            // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0,locListener);
        }
    }

    public void mensaje(){
        Toast toast= Toast.makeText(this,mensaje1,Toast.LENGTH_SHORT);
        toast.show();
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
        miUbicacion();

        LatLng target = null;

        for(int i=0;i<24;i++){
            String[] coordenadas;
            coordenadas=alojamientos.get(i).getLatlong().split(",");
            if(alojamientos.get(i).getLatlong()==null){
                Toast toast= Toast.makeText(this,"No hay nada",Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Log.d("MapaActivity",coordenadas[0]+ coordenadas[1]);
                target = new LatLng(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));
                mMap.addMarker(new MarkerOptions()
                        .position(target)
                        .title("" +alojamientos.get(i).getNombre())
                        .snippet(alojamientos.get(i).getDireccion())
                        //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rural)));
                       .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
            }


        }
        //Poner el Zoom en la marca
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 7));
    }
}