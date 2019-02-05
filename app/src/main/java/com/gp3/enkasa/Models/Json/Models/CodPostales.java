package com.gp3.enkasa.Models.Json.Models;

import com.google.gson.annotations.SerializedName;

public class CodPostales {

    @SerializedName("cod_provincia")
    private int codProvincia;
    @SerializedName("cod_poblacion")
    private int codPoblacion;
    @SerializedName("cod_postal")
    private int codPostal;
    @SerializedName("poblacion")
    private String poblacion;
    @SerializedName("provincia")
    private String provincia;

    public CodPostales() {
    }

    public CodPostales(int codProvincia, int codPoblacion, int codPostal, String poblacion, String provincia) {
        this.codProvincia = codProvincia;
        this.codPoblacion = codPoblacion;
        this.codPostal = codPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public int getCodPoblacion() {
        return codPoblacion;
    }

    public void setCodPoblacion(int codPoblacion) {
        this.codPoblacion = codPoblacion;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
