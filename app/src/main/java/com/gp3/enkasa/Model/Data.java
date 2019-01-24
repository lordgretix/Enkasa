package com.gp3.enkasa.Model;

import com.gp3.enkasa.Model.JSON.Alojamientos;
import com.gp3.enkasa.Model.JSON.Reservas;
import com.gp3.enkasa.Model.JSON.Traducciones;
import com.gp3.enkasa.Model.JSON.User;

import java.util.ArrayList;

public class Data {

    private User user;
    private ArrayList<Alojamientos> alojamientos = new ArrayList<>();
    private ArrayList<Reservas> reservas = new ArrayList<>();
    private ArrayList<Traducciones> Traducciones = new ArrayList<>();
    private String error;

    public Data() {

    }

    public Data(String error) {
        this.error = error;
    }

    public Data(User user, ArrayList<Alojamientos> alojamientos, ArrayList<Reservas> reservas, ArrayList<com.gp3.enkasa.Model.JSON.Traducciones> traducciones) {
        this.user = user;
        this.alojamientos = alojamientos;
        this.reservas = reservas;
        Traducciones = traducciones;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public ArrayList<com.gp3.enkasa.Model.JSON.Traducciones> getTraducciones() {
        return Traducciones;
    }

    public void setTraducciones(ArrayList<com.gp3.enkasa.Model.JSON.Traducciones> traducciones) {
        Traducciones = traducciones;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hashError(){
        return this.error != null;
    }
}
