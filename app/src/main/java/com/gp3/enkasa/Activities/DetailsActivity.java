package com.gp3.enkasa.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.TextView;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

public class DetailsActivity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        mFoto=(ImageView) findViewById(R.id.image_alojamiento);

        mName=(TextView) findViewById(R.id.name_alojamiento);
        mDireccion=(TextView) findViewById(R.id.address_alojamiento);
        mMail=(TextView) findViewById(R.id.mail_alojamiento);
        mTel=(TextView) findViewById(R.id.tel_alojamiento);
        mCodPostal=(TextView) findViewById(R.id.cp_alojamiento);
        mCertificado=(CheckBox) findViewById(R.id.checkBox_certificado);
        mClub=(CheckBox) findViewById(R.id.checkBox_club);
        mAutocaravana=(CheckBox) findViewById(R.id.checkBox_autocaravana);
        mRestaurante=(CheckBox) findViewById(R.id.checkBox_restaurante);
        mTienda=(CheckBox) findViewById(R.id.checkBox_tienda);
        mDescripcion=(TextView) findViewById(R.id.summary_alojamiento);
        mReservar=(Button) findViewById(R.id.button_reservar);
        mReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        int id;
        id = getIntent().getIntExtra(AlojamientosActivity.INTENT_DETALLE_ID,0);
        mTraducciones =  AlojamientosActivity.jsonData.getData().getTraduccionByID(id);

        if (savedInstanceState != null){
             id = savedInstanceState.getInt(AlojamientosActivity.INTENT_DETALLE_ID);
            mTraducciones =  AlojamientosActivity.jsonData.getData().getTraduccionByID(id);

        }

        mName.setText(mTraducciones.getNombre());
        mDireccion.setText(mTraducciones.getDireccion());
        mMail.setText(mTraducciones.getEmail());
        mTel.setText(mTraducciones.getTelefono());
        mCodPostal.setText(mTraducciones.getCodPostal());
        mDescripcion.setText(mTraducciones.getDescripcion());

        if (mTraducciones.getCertificado()==1){
            mCertificado.setChecked(true);
        }
        if (mTraducciones.getClub()==1){
            mClub.setChecked(true);
        }
        if (mTraducciones.getAutocarabana()==1){
            mAutocaravana.setChecked(true);
        }
        if (mTraducciones.getRestaurante()==1){
            mRestaurante.setChecked(true);
        }
        if (mTraducciones.getTienda()==1){
            mTienda.setChecked(true);
        }
    }

}
