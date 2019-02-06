package com.gp3.enkasa.Models.Json.Models;

import com.google.gson.annotations.SerializedName;
public class Traducciones extends Alojamientos{

    @SerializedName("id_traduccion")
    private int idTraduccion;
    @SerializedName("alojamiento")
    private int idAlojamiento;
    @SerializedName("idioma")
    private String idioma = "";
    @SerializedName("tipo")
    private String tipo = "";
    @SerializedName("resumen")
    private String resumen = "";
    @SerializedName("descripcion")
    private String descripcion = "";

    public Traducciones() {

    }

    public Traducciones(int idTraduccion, int idAlojamiento, String idioma, String tipo, String resumen, String descripcion) {
        this.idTraduccion = idTraduccion;
        this.idAlojamiento = idAlojamiento;
        this.idioma = idioma;
        this.tipo = tipo;
        this.resumen = resumen;
        this.descripcion = descripcion;
    }

    public int getIdTraduccion() {
        return idTraduccion;
    }

    public void setIdTraduccion(int idTraduccion) {
        this.idTraduccion = idTraduccion;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAlojamiento(Alojamientos alojamiento){
        this.setId(alojamiento.getId());
        this.setNombre(alojamiento.getNombre());
        this.setCodPoblacion(alojamiento.getCodPoblacion());
        this.setTelefono(alojamiento.getTelefono());
        this.setDireccion(alojamiento.getDireccion());
        this.setCertificado(alojamiento.getCertificado());
        this.setEmail(alojamiento.getEmail());
        this.setWeb(alojamiento.getWeb());
        this.setRestaurante(alojamiento.getRestaurante());
        this.setClub(alojamiento.getClub());
        this.setAutocarabana(alojamiento.getAutocarabana());
        this.setFirma(alojamiento.getFirma());
        this.setTienda(alojamiento.getTienda());
        this.setCapacidad(alojamiento.getCapacidad());
        this.setCodPostal(alojamiento.getCodPostal());
        this.setLatlong(alojamiento.getLatlong());
        this.setCodPostal(alojamiento.getCodPostal());
        this.setCodPoblacion(alojamiento.getCodPoblacion());
    }
}