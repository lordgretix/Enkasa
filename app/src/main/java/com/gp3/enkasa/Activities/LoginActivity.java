package com.gp3.enkasa.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private static final int REGISTER = 0;
    public static final String ALOJAMIENTOS = LoginActivity.class.getName()+".ALOJAMIENTOS";

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
    }

    private void logIn(final String username, final String password, final boolean hash){

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        progressDialog.show();

        try {
            String params = "db=reto_gp3&users_table=usuarios&username_field=nombre&password_field=password&username="+username+"&password="+password+"&data_table[]=alojamientos&data_table[]=traducciones&get_user=true";

            if(hash) params+="&hash=sha256";

            JsonData jsonData = Connection.retriveData(params);

            System.out.println("JsonData has error: "+ jsonData.hashError());
            if(jsonData.hashError()){
                Toast.makeText(getApplicationContext(), R.string.login_status_failed, Toast.LENGTH_SHORT).show();
                mTxtUserName.setError(getResources().getString(R.string.login_status_failed));
            }else{
                Toast.makeText(getApplicationContext(), R.string.login_status_logged, Toast.LENGTH_SHORT).show();
                mTxtUserName.setError(getResources().getString(R.string.login_status_logged));

                System.out.println("DATA: "+  new Gson().toJson(jsonData));

                AlojamientosActivity.jsonData=jsonData;
                createLugaressActivity(jsonData);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            progressDialog.dismiss();
        }

        /*
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    String params = "db=reto_gp3&users_table=usuarios&username_field=nombre&password_field=password&username="+username+"&password="+password+"&data_table[]=alojamientos&data_table[]=traducciones&get_user=true";

                    if(hash) params+="&hash=sha256";

                    JsonData jsonData = Connection.retriveData(params);

                    System.out.println("JsonData has error: "+ jsonData.hashError());
                    if(jsonData.hashError()){
                        Toast.makeText(getApplicationContext(), R.string.login_status_failed, Toast.LENGTH_SHORT).show();
                        mTxtUserName.setError(getResources().getString(R.string.login_status_failed));
                    }else{
                        Toast.makeText(getApplicationContext(), R.string.login_status_logged, Toast.LENGTH_SHORT).show();
                        mTxtUserName.setError(getResources().getString(R.string.login_status_logged));

                        System.out.println("DATA: "+  new Gson().toJson(jsonData));

                        createLugaressActivity(jsonData);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    //progressDialog.dismiss();
                }
            }
        }, 1500);
        */

    }

    private void createLugaressActivity(JsonData jsonData){
        Intent intent = new Intent(this, AlojamientosActivity.class);
        startActivity(intent);
        System.out.println("-------------------------------------------------------------------------------------");
        //finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REGISTER){
            if(resultCode==RegisterActivity.REGISTER_SUCCESS){
                Toast.makeText(this, "Resgistrado", Toast.LENGTH_SHORT ).show();

                User user = new Gson().fromJson(data.getExtras().getString(RegisterActivity.REGISTER_USER), JsonData.class).getUser();

                logIn(user.getUsername(), user.getPassword(), false);

            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT ).show();
            }
        }
    }
}
