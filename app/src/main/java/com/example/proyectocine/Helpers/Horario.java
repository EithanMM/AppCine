package com.example.proyectocine.Helpers;

public class Horario {
    private String IdFuncion;
    private String IdPelicula;
    private String IdSala;
    private String DiaFuncion;
    private String HoraInicio;

    public Horario(String IdFuncion, String IdPelicula, String IdSala, String DiaFuncion, String HoraInicio) {
        this.setIdFuncion(IdFuncion);
        this.setIdPelicula(IdPelicula);
        this.setIdSala(IdSala);
        this.setDiaFuncion(DiaFuncion);
        this.setHoraInicio(HoraInicio);
    }



    @Override
    public String toString() {
        return "Sala: " + getIdSala() + ", " + getDiaFuncion() + " a las " + getHoraInicio();
    }


    public String getIdFuncion() {
        return IdFuncion;
    }

    public void setIdFuncion(String idFuncion) {
        IdFuncion = idFuncion;
    }

    public String getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        IdPelicula = idPelicula;
    }

    public String getIdSala() {
        return IdSala;
    }

    public void setIdSala(String idSala) {
        IdSala = idSala;
    }

    public String getDiaFuncion() {
        return DiaFuncion;
    }

    public void setDiaFuncion(String diaFuncion) {
        DiaFuncion = diaFuncion;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }
}
