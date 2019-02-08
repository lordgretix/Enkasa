package com.gp3.enkasa.Models.Json.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {

    @SerializedName("id")
    private int ID;

    @SerializedName("usuario")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("role")
    private int role;

    @SerializedName("nombre")
    private String nombre = "";

    @SerializedName("apellidos")
    private String apellidos = "";

    @SerializedName("email")
    private String email = "";

    public User() {
    }

    public User(int ID, String username, String password, int role, String nombre, String apellidos, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword(String password, boolean hash) {

        String pass = password;

        if (hash) {
            try {
                byte[] data = MessageDigest.getInstance("SHA-256").digest(password.getBytes());

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < data.length; i++) {
                    sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
                }
                pass = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        this.password = pass;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
