package com.gp3.enkasa.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.Connection;
import com.gp3.enkasa.Models.Json.Exceptions.JsonDataException;
import com.gp3.enkasa.R;

import java.io.IOException;

public class PerfilActivity extends AppCompatActivity {

    public static final int LANG_ES = 0;
    public static final int LANG_EUS = 1;
    public static final int LOGED_OUT = 2;

    private Spinner mIdioma;
    private Button mGuardar;
    private ImageView mCerrarseion;
    private TextView mNombre;
    private TextView mApellido;
    private TextView mMail;
    private EditText mPassword;
    private EditText mPasswordRep;
    private Switch mPasswordChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mIdioma = findViewById(R.id.spinner_idioma);
        mGuardar = findViewById(R.id.btnPerfilGuardar);
        mCerrarseion = findViewById(R.id.imgPerfilLogOut);
        mNombre = findViewById(R.id.perfil_name);
        mMail = findViewById(R.id.perfil_mail);
        mPassword = findViewById(R.id.txtPassword);
        mPasswordRep = findViewById(R.id.txtPasswordRepeat);
        mPasswordChange = findViewById(R.id.switchPerfilPasswordChange);

        mNombre.setText(AlojamientosActivity.jsonData.getUser().getNombre());
        mMail.setText(AlojamientosActivity.jsonData.getUser().getEmail());

        int selected = MainActivity.getCurrentLang() == "es" ? LANG_ES : LANG_EUS;

        mIdioma.setSelection(selected);

        mPasswordChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mPassword.setEnabled(isChecked);
                mPasswordRep.setEnabled(isChecked);
            }
        });

        mPasswordChange.setChecked(false);

        mGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPasswordChange.isChecked()) {

                    if (!validarPassword()) return;

                    try {
                        AlojamientosActivity.jsonData.getUser().setPassword(mPassword.getText().toString(), true);

                        String params = "json={" +
                                "'db':'reto_gp3'," +
                                "'user':'gp3'," +
                                "'password':'IFZWx5dEG12yt8QW'," +
                                "'tables':{" +
                                "'usuarios':{" +
                                "'action':'update'," +
                                "'values':[" +
                                "{" +
                                "'password':'" + AlojamientosActivity.jsonData.getUser().getPassword() + "'" +
                                "}" +
                                "]," +
                                "'where':[" +
                                "{" +
                                "'field':'id'," +
                                "'value':" + AlojamientosActivity.jsonData.getUser().getID() +
                                "}" +
                                "]" +
                                "}" +
                                "}" +
                                "}";

                        params = params.replaceAll("'", "\"");
                        Connection.pushData(params);

                        MainActivity.setStoredUser(getApplicationContext(), AlojamientosActivity.jsonData.getUser());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonDataException e) {
                        e.printStackTrace();
                    }

                }

                switch (mIdioma.getSelectedItemPosition()) {
                    case 0:
                        MainActivity.setLocale(getApplicationContext(), "es");
                        break;
                    case 1:
                        MainActivity.setLocale(getApplicationContext(), "eu");
                        break;
                }
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

    private Boolean validarPassword() {
        if (!mPassword.getText().toString().equals(mPasswordRep.getText().toString())) {
            mPassword.setError(getResources().getString(R.string.error_match_password));
            return false;
        }

        return true;
    }

}
