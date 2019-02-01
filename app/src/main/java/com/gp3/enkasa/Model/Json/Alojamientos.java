package com.gp3.enkasa.Model.Json;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Alojamientos{

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre = "";

    @SerializedName("localidad")
    private String localidad = "";

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

    @SerializedName("codPostal")
    private String codPostal = "";

    @SerializedName("latlong")
    private String latlong = "";

    @SerializedName("municipio")
    private String municipio = "";

    @SerializedName("territorio")
    private String territorio = "";

    public Alojamientos() {

    }

    public Alojamientos( int id, String nombre, String localidad, String telefono, String direccion,
                        int certificado, String email, String web, int restaurante, int club,
                        int autocarabana, String firma, int tienda, int capacidad, String codPostal,
                        String latlong, String municipio, String territorio, String json) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
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
        this.latlong = latlong;
        this.municipio = municipio;
        this.territorio = territorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTraduccion() {
        return id;
    }

    public void setIdTraduccion(int idTraduccion) {
        this.id = idTraduccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
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

    public int getClub() {
        return club;
    }

    public void setClub(int club) {
        this.club = club;
    }

    public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(int restaurante) {
        this.restaurante = restaurante;
    }

    public int getAutocarabana() {
        return autocarabana;
    }

    public void setAutocarabana(int autocarabana) {
        this.autocarabana = autocarabana;
    }

    public int getTienda() {
        return tienda;
    }

    public void setTienda(int tienda) {
        this.tienda = tienda;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }
}
