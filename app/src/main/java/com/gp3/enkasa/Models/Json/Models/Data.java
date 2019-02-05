package com.gp3.enkasa.Models.Json.Models;

import com.google.gson.annotations.SerializedName;

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

    public Data(User user, ArrayList<Alojamientos> alojamientos, ArrayList<Reservas> reservas, ArrayList<Traducciones> traducciones) {
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

    public ArrayList<Traducciones> getTraducciones() {
        return this.traducciones;
    }

    public ArrayList<Traducciones> getTraducciones(String lang){
        ArrayList<Traducciones> trs = new ArrayList<>();

        for (Traducciones tr : this.traducciones) {
            if(tr.getIdioma().equals(lang.toLowerCase())){
                trs.add(tr);
            }
        }
        return trs;
    }

    public void setTraducciones(ArrayList<Traducciones> traducciones) {
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

    public Traducciones getTraduccionByID(int id){
        for (Traducciones tr : this.traducciones) {
            if(tr.getIdTraduccion()==id){
                return tr;
            }
        }
        return new Traducciones();
    }


    public Alojamientos getAlojamientoByID(int id){
        for (Alojamientos aloj : this.alojamientos) {
            if(aloj.getId()==id){
                return aloj;
            }
        }
        return new Alojamientos();
    }

    public void setAlojamientosToTraducciones(){
        for (Traducciones tr : this.traducciones) {
            tr.setAlojamiento(getAlojamientoByID(tr.getIdAlojamiento()));
        }
    }

}
