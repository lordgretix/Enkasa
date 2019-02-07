package com.gp3.enkasa.Models.Json.Models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Alojamientos {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre = "";

    @SerializedName("telefono")
    private String telefono = "";

    @SerializedName("direccion")
    private String direccion = "";

    @SerializedName("certificado")
    private int certificado = 0;

    @SerializedName("email")
    private String email = "";

    @SerializedName("web")
    private String web = "";

    @SerializedName("restaurante")
    private int restaurante = 0;

    @SerializedName("club")
    private int club = 0;

    @SerializedName("autocarabana")
    private int autocarabana = 0;

    @SerializedName("firma")
    private String firma;

    @SerializedName("tienda")
    private int tienda = 0;

    @SerializedName("capacidad")
    private int capacidad = 0;

    @SerializedName("cod_postal")
    private int codPostal = 0;

    @SerializedName("cod_poblacion")
    private int codPoblacion = 0;

    @SerializedName("latlong")
    private String latlong = "";


    public Alojamientos() {

    }

    public Alojamientos(int id, String nombre, String telefono, String direccion, int certificado, String email, String web, int restaurante, int club, int autocarabana, String firma, int tienda, int capacidad, int codPostal, int codPoblacion, String latlong) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.certificado = certificado;
        this.email = email;
        this.web = web;
        this.restaurante = restaurante;
        this.club = club;
        this.autocarabana = autocarabana;
        this.firma = firma;
        this.tienda = tienda;
        this.capacidad = capacidad;
        this.codPostal = codPostal;
        this.codPoblacion = codPoblacion;
        this.latlong = latlong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodPoblacion() {
        return codPoblacion;
    }

    public void setCodPoblacion(int codPoblacion) {
        this.codPoblacion = codPoblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCertificado() {
        return certificado;
    }

    public void setCertificado(int certificado) {
        this.certificado = certificado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(int restaurante) {
        this.restaurante = restaurante;
    }

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    public int getAutocarabana() {
        return autocarabana;
    }

    public void setAutocarabana(int autocarabana) {
        this.autocarabana = autocarabana;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public int getTienda() {
        return tienda;
    }

    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }



}
