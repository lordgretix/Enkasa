package com.gp3.enkasa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gp3.enkasa.Model.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

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


        mBtnLogin = (CardView) findViewById(R.id.btnLogin);
        mTxtUserName = (EditText) findViewById(R.id.txtUsername);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);
        mLblRegister = (TextView) findViewById(R.id.lblRegister);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(mTxtUserName.getText().toString(), mTxtPassword.getText().toString());
            }

        });
    }

    private void logIn(final String username, final String password){

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");


        progressDialog.show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://kasserver.synology.me/etazi/get_json.php");
                    String params = "db=reto_gp3&users_table=usuarios&username_field=nombre&password_field=password&username="+username+"&password="+password+"&data_table[]=alojamientos&get_user=true&hash=sha256";
                    byte[] postDataBytes = params.getBytes("UTF-8");

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(postDataBytes);

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    StringBuilder response= new StringBuilder();
                    String buff;
                    while((buff = in.readLine())!=null){
                        response.append(buff);
                    }

                    Data data =  new Gson().fromJson(response.toString(), Data.class);

                    System.out.println("Data has error: "+data.hashError());
                    if(data.hashError()){
                        Toast.makeText(getApplicationContext(), R.string.login_status_failed, Toast.LENGTH_SHORT).show();
                        mTxtUserName.setError(getResources().getString(R.string.login_status_failed));
                    }else{
                        Toast.makeText(getApplicationContext(), R.string.login_status_logged, Toast.LENGTH_SHORT).show();
                        mTxtUserName.setError(getResources().getString(R.string.login_status_logged));

                        createAlojamientos(data);
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    progressDialog.dismiss();
                }
            }
        }, 2000);

    }

    private void createAlojamientos(Data data){
        Intent intent = new Intent(this, AlojamientosActivity.class);
        intent.putExtra("DATA", data);
    }
}
