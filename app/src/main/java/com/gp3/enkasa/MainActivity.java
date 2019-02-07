package com.gp3.enkasa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.gp3.enkasa.Activities.LoginActivity;
import com.gp3.enkasa.Models.Json.Models.User;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static String CURRENT_LANG = "es";
    private static final String LOGIN_PREFERENCE = MainActivity.class.getName()+".LOGIN_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public static void setLocale(Context context, String lang){
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);

        CURRENT_LANG = lang.equals("eu") ? "eus" : lang;

    }

    public static String getCurrentLang() {
        return CURRENT_LANG;
    }

    public static void setStoredUser(Context context, User user){

        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN_PREFERENCE, 0).edit();
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        editor.commit();
    }

    public static User getStoredUser(Context context){

        User user = new User();

        SharedPreferences sp = context.getSharedPreferences(LOGIN_PREFERENCE, 0);
        user.setUsername(sp.getString("username", null));
        user.setPassword(sp.getString("password", null));

        return user.getUsername() != null && user.getPassword() != null ? user : null;
    }

    public static void removeStoredUser(Context context){

        SharedPreferences.Editor editor = context.getSharedPreferences(LOGIN_PREFERENCE, 0).edit();
        editor.clear();
        editor.commit();
    }
}
