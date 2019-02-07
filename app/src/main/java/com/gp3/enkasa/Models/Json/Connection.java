package com.gp3.enkasa.Models.Json;

import com.google.gson.Gson;
import com.gp3.enkasa.Models.Json.Exceptions.JsonDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class Connection {

    private static final String GETTER_API = "get_json.php";
    private static final String SETTER_API = "set_json.php";
    private static final String METHOD_POST = "POST";
    private static final String CONN_URL = "https://kasserver.synology.me/etazi/";

    public static JsonData retriveData(String url, String api, String params, String method, boolean output, String charset) throws IOException {

        HttpsURLConnection conn = (HttpsURLConnection) new URL(url+api).openConnection();

        conn.setRequestMethod(method);
        conn.setDoOutput(output);
        conn.getOutputStream().write(params.getBytes(charset));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuilder response = new StringBuilder();
        String buff;
        while((buff = in.readLine())!=null){
            response.append(buff);
        }

        return new Gson().fromJson(response.toString(), JsonData.class);
    }


    public static JsonData retriveData(String params) throws IOException {

        HttpsURLConnection conn = (HttpsURLConnection) new URL(CONN_URL+GETTER_API).openConnection();

        conn.setRequestMethod(METHOD_POST);
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes(StandardCharsets.UTF_8));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String buff;
        while((buff = in.readLine())!=null){
            response.append(buff);
        }

        return new Gson().fromJson(response.toString(), JsonData.class);
    }

    public static boolean pushData(String url, String api, String params, String method, boolean output, String charset) throws IOException, JsonDataException {

        HttpsURLConnection conn = (HttpsURLConnection) new URL(url+api).openConnection();

        conn.setRequestMethod(method);
        conn.setDoOutput(output);
        conn.getOutputStream().write(params.getBytes(charset));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuilder response = new StringBuilder();
        String buff;
        while((buff = in.readLine())!=null){
            response.append(buff);
        }

        JsonData jsonData = new Gson().fromJson(response.toString(), JsonData.class);
        if(jsonData.hashError()){
            throw new JsonDataException(jsonData.getError());
        }

        return true;
    }

    public static boolean pushData(String params) throws IOException, JsonDataException {

        HttpsURLConnection conn = (HttpsURLConnection) new URL(CONN_URL+SETTER_API).openConnection();

        conn.setRequestMethod(METHOD_POST);
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes(StandardCharsets.UTF_8));

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),  StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String buff;
        while((buff = in.readLine())!=null){
            response.append(buff);
        }

        JsonData jsonData =new Gson().fromJson(response.toString(), JsonData.class);
        if(jsonData.hashError()){
            throw new JsonDataException(jsonData.getError());
        }

        return true;
    }
}
