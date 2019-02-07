package com.gp3.enkasa.Activities;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.Models.Alojamientos;
import com.gp3.enkasa.R;

import java.util.Locale;

public class PerfilActivity extends AppCompatActivity {

    public static final int LANG_ES = 0;
    public static final int LANG_EUS = 1;
    public static final int LOGED_OUT = 2;

    private Spinner mIdioma;
    private Button mGuardar;
    private Button mCerrarseion;
    private TextView mNombre;
    private TextView mApellido;
    private TextView mMail;
    private EditText mPassword;
    private EditText mPasswordRep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mIdioma= findViewById(R.id.spinner_idioma);
        mGuardar = findViewById(R.id.btnPerfilGuardar);
        mCerrarseion=findViewById(R.id.btnPerfilLogOut);
        mNombre=findViewById(R.id.perfil_name);
        mApellido=findViewById(R.id.perfil_apellido);
        mMail=findViewById(R.id.perfil_mail);
        mPassword=findViewById(R.id.txtPassword);
        mPasswordRep=findViewById(R.id.txtPasswordRepeat);

        mNombre.setText(AlojamientosActivity.jsonData.getUser().getNombre());
        mApellido.setText(AlojamientosActivity.jsonData.getUser().getApellidos());
        mMail.setText(AlojamientosActivity.jsonData.getUser().getEmail());


        mIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (mIdioma.getSelectedItemPosition()){
                    case 0:
                        MainActivity.setLocale(getApplicationContext(), "es");
                        break;
                    case 1:
                        MainActivity.setLocale(getApplicationContext(),"eu");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(mIdioma.getSelectedItemPosition());
                finish();
            }
        });

        mCerrarseion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.removeStoredUser(getApplicationContext());
                setResult(LOGED_OUT);
                finish();
            }
        });
    }



}
