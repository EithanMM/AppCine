package com.example.proyectocine;

import android.widget.TableLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;

public class VariablesGlobales {

    private ArrayList<String> ListaAsientos = new ArrayList<>();
    private TextView TextHelper;
    private TableLayout TablaButacas;

    private String NombrePelicula;
    private String NombreSala;
    private String DiaFuncion;
    private Time HoraFuncion;


    private static VariablesGlobales instance = null;

    protected VariablesGlobales() {}
    public static VariablesGlobales getInstance() {
        if(instance == null) {instance = new VariablesGlobales(); }
        return instance;
    }

    public ArrayList<String> getListaAsientos() {
        return ListaAsientos;
    }

    public void setListaAsientos(ArrayList<String> listaAsientos) {
        ListaAsientos = listaAsientos;
    }

    public TextView getTextHelper() {
        return TextHelper;
    }

    public void setTextHelper(TextView textHelper) {
        TextHelper = textHelper;
    }

    public TableLayout getTablaButacas() {
        return TablaButacas;
    }

    public void setTablaButacas(TableLayout tablaButacas) {
        TablaButacas = tablaButacas;
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

    public String getDiaFuncion() {
        return DiaFuncion;
    }

    public void setDiaFuncion(String diaFuncion) {
        DiaFuncion = diaFuncion;
    }

    public Time getHoraFuncion() {
        return HoraFuncion;
    }

    public void setHoraFuncion(Time horaFuncion) {
        HoraFuncion = horaFuncion;
    }
}
