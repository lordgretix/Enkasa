package com.gp3.enkasa.Models.Json.Models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Reservas {

    @SerializedName("id")
    private int id;

    @SerializedName("usuario")
    private int usuario;// el id de usuario

    @SerializedName("alojamiento")
    private int alojamiento;// el id deelojamiento reservado

    @SerializedName("fecha_inicio")
    private String fechaInicio;

    @SerializedName("fecha_fin")
    private String fechaFin;

    @SerializedName("num_reservas")
    private int numReservas;

    @SerializedName("mensaje")
    private String mensaje;

    public Reservas() {
    }

    public Reservas(int id, int usuario, int alojamiento, String fechaInicio, String fechaFin, int numReservas, String mensaje) {
        this.id = id;
        this.usuario = usuario;
        this.alojamiento = alojamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numReservas = numReservas;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(int alojamiento) {
        this.alojamiento = alojamiento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaInicio(boolean formated) {
        if(formated){
            String[] fecha = fechaInicio.split("-");
            return fecha[2]+"/"+fecha[1]+"/"+fecha[0];
        }
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getFechaFin(boolean formated) {
        if(formated){
            String[] fecha = fechaFin.split("-");
            return fecha[2]+"/"+fecha[1]+"/"+fecha[0];
        }
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

}