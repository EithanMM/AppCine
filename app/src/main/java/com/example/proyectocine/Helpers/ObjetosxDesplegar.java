package com.example.proyectocine.Helpers;

public class ObjetosxDesplegar {
    private String atributo01;
    private String atributo02;
    private String atributo03;
    private String atributo04;
    private int NumDibujo;
    private String idPelicula;

    public ObjetosxDesplegar(String atributo01, String atributo02, String atributo03, String atributo04, int NumDibujo, String idPelicula){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.atributo03 = atributo03;
        this.atributo04 = atributo04;
        this.NumDibujo = NumDibujo;
        this.idPelicula = idPelicula;
    }
    public String getAtributo01() {
        return atributo01;
    }
    public String getAtributo02() {
        return atributo02;
    }
    public String getAtributo03() { return atributo03; }
    public String getAtributo04() {
        return atributo04;
    }
    public String getIdPelicula() { return idPelicula; }
    public int getNumDibujo() {
        return NumDibujo;
    }
}
