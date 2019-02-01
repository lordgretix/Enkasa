package com.gp3.enkasa.Models.Json.Models;

public class Reservas {

    private int id;
    private int usuario;// el id de usuario
    private int alojamiento;// el id deelojamiento reservado
    private String fecha;

    public Reservas() {
    }

    public Reservas(int id, int usuario, int alojamiento, String fecha) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.alojamiento = alojamiento;
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}