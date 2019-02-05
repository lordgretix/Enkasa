package com.gp3.enkasa.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

public class DetailsActivity extends AppCompatActivity {

    private int id_Alojamiento;

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
    private TextView mResumen;

    private Button mReservar;
    private Traducciones mTraducciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        mName=findViewById(R.id.name_alojamiento);
        mDireccion=findViewById(R.id.address_alojamiento);
        mMail=findViewById(R.id.mail_alojamiento);
/*        mTel=findViewById(R.id.tel_alojamiento);
        mCodPostal=findViewById(R.id.cp_alojamiento);
        mCertificado=findViewById(R.id.checkBox_certificado);
        mClub=findViewById(R.id.checkBox_club);
        mAutocaravana=findViewById(R.id.checkBox_autocaravana);
        mRestaurante=findViewById(R.id.checkBox_restaurante);
        mTienda=findViewById(R.id.checkBox_tienda);
        mResumen=findViewById(R.id.summary_alojamiento);
        mReservar=findViewById(R.id.button_reservar);
        mReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/
        if (savedInstanceState != null){
            int id = savedInstanceState.getInt(AlojamientosActivity.INTENT_DETALLE_ID);
            mTraducciones =  AlojamientosActivity.jsonData.getData().getTraduccionByID(id);
        }

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        //mName.setText(mTraducciones.getNombre());
        return super.onCreateView(parent, name, context, attrs);
    }
}
