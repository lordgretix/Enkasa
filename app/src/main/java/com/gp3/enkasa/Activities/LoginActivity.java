package com.gp3.enkasa.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Connection;
import com.gp3.enkasa.Models.Json.Models.User;
import com.gp3.enkasa.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private static final int REGISTER = 0;
    private static final String LOGIN_PREFERENCE = LoginActivity.class.getName()+".LOGIN_PREFERENCE";
    public static final String ALOJAMIENTOS = LoginActivity.class.getName()+".ALOJAMIENTOS";

    ProgressDialog progress;

    private CardView mBtnLogin;
    private EditText mTxtUserName;
    private EditText mTxtPassword;
    private TextView mLblRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Permisos de Red

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        mBtnLogin = findViewById(R.id.btnLogin);
        mTxtUserName = findViewById(R.id.txtUsername);
        mTxtPassword = findViewById(R.id.txtPassword);
        mLblRegister = findViewById(R.id.lblRegister);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(mTxtUserName.getText().toString(), mTxtPassword.getText().toString(), true);
            }

        });

        mLblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(register, REGISTER);
            }
        });

        User user = getStoredUser();

        System.out.println("");
        if(user!=null){
            logIn(user.getUsername(), user.getPassword(), false);
        }

    }

    private void logIn(final String username, final String password, final boolean hash){

        progress = new ProgressDialog(this);

        progress.setMessage("Iniciando seseion...");
        progress.setIndeterminate(true);

        progress.show();

        new Thread(new Runnable() {
            @Override
            public void run()
            {

                try {
                    String params = "db=reto_gp3&users_table=usuarios&username_field=usuario&password_field=password&username="+username+"&password="+password+"&data_table[]=alojamientos&data_table[]=traducciones&data_table[]=codigos_postales&get_user=true";

                    if(hash) params+="&hash=sha256";

                    JsonData jsonData = Connection.retriveData(params);

                    jsonData.getData().setAlojamientosToTraducciones();

                    AlojamientosActivity.jsonData=jsonData;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(AlojamientosActivity.jsonData.hashError()){
                                Toast.makeText(getApplicationContext(), R.string.login_status_failed, Toast.LENGTH_SHORT).show();
                                mTxtUserName.setError(getResources().getString(R.string.login_status_failed));
                            }else{
                                Toast.makeText(getApplicationContext(), R.string.login_status_logged, Toast.LENGTH_SHORT).show();
                                setStoredUser(AlojamientosActivity.jsonData.getUser());
                                createLugaressActivity();
                            }
                            progress.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();

    }

    private void createLugaressActivity(){
        Intent intent = new Intent(this, AlojamientosActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REGISTER){
            if(resultCode==RegisterActivity.REGISTER_SUCCESS){

                User user = new Gson().fromJson(data.getExtras().getString(RegisterActivity.REGISTER_USER), JsonData.class).getUser();

                logIn(user.getUsername(), user.getPassword(), false);
            }
        }
    }

    private void setStoredUser(User user){

        SharedPreferences.Editor editor = getSharedPreferences(LOGIN_PREFERENCE, 0).edit();
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        editor.commit();
    }

    private User getStoredUser(){

        User user = new User();

        SharedPreferences sp = getSharedPreferences(LOGIN_PREFERENCE, 0);
        user.setUsername(sp.getString("username", null));
        user.setPassword(sp.getString("password", null));

        return user.getUsername() != null && user.getPassword() != null ? user : null;
    }
}
