package com.example.proyectocine;

public class ObjetosxDesplegar {
    private String atributo01;
    private String atributo02;
    private String atributo03;
    private int NumDibujo;
    public ObjetosxDesplegar(String atributo01, String atributo02,String atributo03, int NumDibujo){
        super();
        this.atributo01 = atributo01;
        this.atributo02 = atributo02;
        this.atributo03 = atributo03;
        this.NumDibujo = NumDibujo;
    }
    public String getAtributo01() {
        return atributo01;
    }
    public String getAtributo02() {
        return atributo02;
    }
    public String getAtributo03() {
        return atributo03;
    }
    public int getNumDibujo() {
        return NumDibujo;
    }
}
