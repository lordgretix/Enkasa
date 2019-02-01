package com.gp3.enkasa.Model.Json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Data {

    @SerializedName("alojamientos")
    private ArrayList<Alojamientos> alojamientos = new ArrayList<>();
    @SerializedName("reservas")
    private ArrayList<Reservas> reservas = new ArrayList<>();
    @SerializedName("traducciones")
    private ArrayList<Traducciones> traducciones = new ArrayList<>();

    public Data() {

    }

    public Data(User user, ArrayList<Alojamientos> alojamientos, ArrayList<Reservas> reservas, ArrayList<com.gp3.enkasa.Model.Json.Traducciones> traducciones) {
        this.alojamientos = alojamientos;
        this.reservas = reservas;
        this.traducciones = traducciones;
    }

    public ArrayList<Alojamientos> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(ArrayList<Alojamientos> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public ArrayList<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reservas> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<com.gp3.enkasa.Model.Json.Traducciones> getTraducciones() {
        return this.traducciones;
    }

    public void setTraducciones(ArrayList<com.gp3.enkasa.Model.Json.Traducciones> traducciones) {
        this.traducciones = traducciones;
    }

    public Traducciones getTraduccionByAlojaminetoID(int id, String lang){

        for (Traducciones tr : this.traducciones) {

            if(tr.getIdAlojamiento()==id && tr.getIdioma().equals(lang)){
                return tr;
            }
        }

        return new Traducciones();
    }

}
