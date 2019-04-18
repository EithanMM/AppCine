package com.example.proyectocine.Helpers;

import java.sql.Time;

public class ObjetoBitacora {

    private String IdFuncion;
    private String Cedula;
    private String Nombrepelicula;
    private String Dia;
    private Time Hora;
    private String AsientoEnUso;


    public ObjetoBitacora(String idFuncion, String cedula, String nombrepelicula, String dia, Time hora, String asientoEnUso) {
        IdFuncion = idFuncion;
        Cedula = cedula;
        Nombrepelicula = nombrepelicula;
        Dia = dia;
        Hora = hora;
        AsientoEnUso = asientoEnUso;
    }

    public String getIdFuncion() {
        return IdFuncion;
    }

    public void setIdFuncion(String idFuncion) {
        IdFuncion = idFuncion;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getNombrepelicula() {
        return Nombrepelicula;
    }

    public void setNombrepelicula(String nombrepelicula) {
        Nombrepelicula = nombrepelicula;
    }

    public String getAsientoEnUso() {
        return AsientoEnUso;
    }

    public void setAsientoEnUso(String asientoEnUso) {
        AsientoEnUso = asientoEnUso;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String dia) {
        Dia = dia;
    }

    public Time getHora() {
        return Hora;
    }

    public void setHora(Time hora) {
        Hora = hora;
    }

}
