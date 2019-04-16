package com.example.proyectocine;

public class ObjetoFuncion {
    private String ID;
    private String IdPelicula;
    private String IdSala;
    private String NombrePelicula;
    private String NombreSala;
    private String Genero;
    private String DiaFuncion;
    private String HoraInicio;


    public ObjetoFuncion(String ID, String idPelicula, String idSala, String NombrePelicula, String NombreSala, String Genero, String diaFuncion, String horaInicio) {
        this.ID = ID;
        IdPelicula = idPelicula;
        IdSala = idSala;
        this.NombrePelicula = NombrePelicula;
        this.NombreSala = NombreSala;
        this.Genero = Genero;
        DiaFuncion = diaFuncion;
        HoraInicio = horaInicio;
    }

    public ObjetoFuncion(){
        this.ID = "";
        this.IdPelicula = "";
        this.IdSala = "";
        this.NombrePelicula = "";
        this.NombreSala = "";
        this.Genero = "";
        this.DiaFuncion = "";
        this.HoraInicio = "";
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getNombrePelicula() {
        return NombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        NombrePelicula = nombrePelicula;
    }

    public String getNombreSala() {
        return NombreSala;
    }

    public void setNombreSala(String nombreSala) {
        NombreSala = nombreSala;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }
}
