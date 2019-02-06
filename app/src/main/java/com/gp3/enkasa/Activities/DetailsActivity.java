package com.gp3.enkasa.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String SAVE_DETAIL_ID = DetailsActivity.class.getName() + ".SAVE_DETAIL_ID";
    private int id_Alojamiento;

    private ImageView mFoto;
    private TextView mName;
    private TextView mDireccion;
    private TextView mMail;
    private TextView mTel;
    private TextView mCodPostal;
    private CheckBox mCertificado;
    private CheckBox mClub;
    private CheckBox mAutocaravana;
    private CheckBox mRestaurante;
    private CheckBox mTienda;
    private TextView mDescripcion;

    private Button mReservar;
    private Traducciones mTraducciones;
private Button btnMapa;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        mFoto = findViewById(R.id.image_alojamiento);

        mName = findViewById(R.id.name_alojamiento);
        mDireccion = findViewById(R.id.address_alojamiento);
        mMail = findViewById(R.id.mail_alojamiento);
        mTel = findViewById(R.id.tel_alojamiento);
        mCodPostal = findViewById(R.id.cp_alojamiento);
        mCertificado = findViewById(R.id.checkBox_certificado);
        mClub = findViewById(R.id.checkBox_club);
        mAutocaravana = findViewById(R.id.checkBox_autocaravana);
        mRestaurante = findViewById(R.id.checkBox_restaurante);
        mTienda = findViewById(R.id.checkBox_tienda);
        mDescripcion = findViewById(R.id.summary_alojamiento);
        mReservar = findViewById(R.id.button_reservar);
        mReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(DetailsActivity.this, ReservasActivity.class);
                mIntent.putExtra(SAVE_DETAIL_ID, id);
                startActivity(mIntent);
            }
        });


        if (savedInstanceState != null) {
            id = savedInstanceState.getInt(AlojamientosActivity.INTENT_DETALLE_ID);
            mTraducciones = AlojamientosActivity.jsonData.getData().getTraduccionByID(id);

        } else {
            id = getIntent().getIntExtra(AlojamientosActivity.INTENT_DETALLE_ID, 0);
            mTraducciones = AlojamientosActivity.jsonData.getData().getTraduccionByID(id);
        }

        cargarDetalle(id);


        btnMapa=findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Intent intent = MapsActivity.newIntent(getApplicationContext(),Alojamientos.get)
                Intent intent = new Intent (getApplicationContext(),MapsActivity.class);
            startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle guardaEstado) {
        super.onSaveInstanceState(guardaEstado);
        //lo "guardamos" el id en el Bundle
        guardaEstado.putInt(SAVE_DETAIL_ID, id);

    }

    @Override
    protected void onRestoreInstanceState(Bundle recuperaEstado) {
        super.onRestoreInstanceState(recuperaEstado);
        //recuperamos el String del Bundle
        id = recuperaEstado.getInt(SAVE_DETAIL_ID);
        cargarDetalle(id);
    }

    private void cargarDetalle(int id) {
        mTraducciones = AlojamientosActivity.jsonData.getData().getTraduccionByID(id);
        mName.setText(mTraducciones.getNombre());
        mDireccion.setText(mTraducciones.getDireccion());
        mMail.setText(mTraducciones.getEmail());
        mTel.setText(mTraducciones.getTelefono());
        mCodPostal.setText(String.valueOf(mTraducciones.getCodPostal()));
        mDescripcion.setText(mTraducciones.getDescripcion());
        mDescripcion.setMovementMethod(new ScrollingMovementMethod());
        if (mTraducciones.getCertificado() == 1) {
            mCertificado.setChecked(true);
        }
        if (mTraducciones.getClub() == 1) {
            mClub.setChecked(true);
        }
        if (mTraducciones.getAutocarabana() == 1) {
            mAutocaravana.setChecked(true);
        }
        if (mTraducciones.getRestaurante() == 1) {
            mRestaurante.setChecked(true);
        }
        if (mTraducciones.getTienda() == 1) {
            mTienda.setChecked(true);
        }
       /*
          Los posibles tipos de alojamientos:
          Albergues         Aterpetxeak
          Campings          Kanpinak
          Agroturismos      Nekazaritza-turismoak
          Casas Rurales     Agroturismos
          */
        String mTipo = mTraducciones.getTipo();
        if (mTipo.equals("Albergues") || mTipo.equals("Aterpetxeak")) {
            mFoto.setImageResource(R.drawable.ic_alberges_icon);
        } else if (mTipo.equals("Campings") || mTipo.equals("Kanpinak")) {
            mFoto.setImageResource(R.drawable.ic_camping_icon);
        } else if (mTipo.equals("Agroturismos") || mTipo.equals("Agroturismos")) {
            mFoto.setImageResource(R.drawable.ic_agroturismo_icon);
        } else {
            mFoto.setImageResource(R.drawable.ic_rural_icon);
        }
    }

}
