package com.gp3.enkasa.Model.JSON;


public class Alojamientos extends Traducciones{

    private int id;
    private String nombre = "";
    private String localidad = "";
    private String telefono = "";
    private String direccion = "";
    private boolean certificado = false;
    private String email = "";
    private String web = "";
    private boolean restaurante = false;
    private boolean club = false;
    private boolean autocarabana = false;
    private String firma;
    private boolean tienda = false;
    private int capacidad = 0;
    private String codPostal = "";
    private String latlong = "";
    private String municipio = "";
    private String territorio = "";

    public Alojamientos() {

    }

    public Alojamientos(int idTraduccion, int idAlojamiento, String idioma, String tipo, String resumen,
                        String descripcion, int id, String nombre, String localidad, String telefono, String direccion,
                        boolean certificado, String email, String web, boolean restaurante, boolean club,
                        boolean autocarabana, String firma, boolean tienda, int capacidad, String codPostal,
                        String latlong, String municipio, String territorio, String json) {
        super(idTraduccion, idAlojamiento, idioma, tipo, resumen, descripcion);
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

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public boolean isClub() {
        return club;
    }

    public void setClub(boolean club) {
        this.club = club;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public void setRestaurante(boolean restaurante) {
        this.restaurante = restaurante;
    }

    public boolean isAutocarabana() {
        return autocarabana;
    }

    public void setAutocarabana(boolean autocarabana) {
        this.autocarabana = autocarabana;
    }

    public boolean isTienda() {
        return tienda;
    }

    public void setTienda(boolean tienda) {
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
