package com.gp3.enkasa.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.R;

import java.util.UUID;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private static final String EXTRA_ALOJAMIENTOS = MapsActivity.class.getName() + ".EXTRA_ALOJAMIENTOS";
    private GoogleMap mMap;
    private Alojamientos mAlojamientos;
    private Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mAlojamientos = AlojamientosActivity.jsonData.getData().getTraduccionByID(getIntent().getIntExtra(EXTRA_ALOJAMIENTOS, 0));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //AlojamientosActivity.jsonData.getData().getTraducciones(Al)
    }



    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTOS, id);
        return intent;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] coordenadas = new String[2];
        //String prueba =mAlojamientos.getLatlong();
       // Toast.makeText(getApplicationContext(), prueba, Toast.LENGTH_SHORT).show();

        coordenadas=mAlojamientos.getLatlong().split(",");
        Log.d("MapaActivity",coordenadas[0]+ coordenadas[1]);
        LatLng target = new LatLng(Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1]));
        MarkerOptions options = new MarkerOptions();
        options.position(target).title(mAlojamientos.getNombre());

        mMap.addMarker(options);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 13f));
    }
}