package com.gp3.enkasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gp3.enkasa.Model.Json.Connection;
import com.gp3.enkasa.Model.Json.JsonDataException;
import com.gp3.enkasa.Model.Json.User;
import java.util.regex.Pattern;
import android.util.Patterns;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    public static final int REGISTER_CANCEL = 0;
    public static final int REGISTER_SUCCESS = 1;

    public static final String REGISTER_USER = "com.gp3.enkasa.RegisterActivity.REGISTER_USER";

    private TextView mBtnRegister;
    private EditText mTxtNombre;
    private EditText mTxtApellidos;
    private EditText mTxtEmail;
    private EditText mTxtUsuario;
    private EditText mTxtPassword;
    private EditText mTxtPasswordRepeat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrarse);

        mBtnRegister = findViewById(R.id.btnRegisterLabel);
        mTxtNombre = findViewById(R.id.txtNombre);
        mTxtApellidos = findViewById(R.id.txtApellidos);
        mTxtEmail = findViewById(R.id.txtEmail);
        mTxtUsuario = findViewById(R.id.txtUsuario);
        mTxtPassword = findViewById(R.id.txtPassword);
        mTxtPasswordRepeat = findViewById(R.id.txtPasswordRepeat);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if(!validate()) return;

                    User user = new User();

                    user.setUsername(mTxtUsuario.getText().toString());
                    user.setNombre(mTxtNombre.getText().toString());
                    user.setApellidos(mTxtApellidos.getText().toString());
                    user.setEmail(mTxtEmail.getText().toString());
                    user.setPassword(mTxtPassword.getText().toString(), true);
                    user.setRole(3);

                    System.out.println("USUARIO: "+ new Gson().toJson(user));
                    
                    String params = "json={" +
                                "'db':'reto_gp3'," +
                                "'user':'gp3'," +
                                "'password':'IFZWx5dEG12yt8QW'," +
                                "'tables':{" +
                                    "'usuarios':{" +
                                        "'action':'insert'," +
                                        "'values':[" +
                                            user.toJson() +
                                        "]" +
                                    "}" +
                                "}" +
                            "}";
                    params=params.replaceAll("'", "\"");

                    System.out.println("JSON: "+params);
                    Connection.pushData(params);

                    Intent result = new Intent();
                    result.putExtra(REGISTER_USER, user);

                    setResult(REGISTER_SUCCESS, result);

                    finish();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JsonDataException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    // TODO: validar todos los input
    private boolean validate(){

        if(!mTxtPassword.getText().toString().equals(mTxtPasswordRepeat.getText().toString())){
            mTxtPassword.setError(getResources().getString(R.string.error_match_password));
            return false;
        }

        return true;
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
