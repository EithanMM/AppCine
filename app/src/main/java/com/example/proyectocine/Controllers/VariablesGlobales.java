package com.example.proyectocine.Controllers;

import android.widget.TableLayout;
import android.widget.TextView;

import com.example.proyectocine.Helpers.ObjetoBitacora;

import java.sql.Time;
import java.util.ArrayList;

public class VariablesGlobales {

    private ArrayList<String> ListaAsientos = new ArrayList<>();
    private ArrayList<ObjetoBitacora> ListaBitacora = new ArrayList<>();

    private TextView TextHelper;
    private TableLayout TablaButacas;
    private String idPelicula;
    private String NombrePelicula;
    private String NombreSala;
    private String DiaFuncion;
    private Time HoraFuncion;

    private int precioTotal;
    private int totalButacas = 10;
    private int butacasSeleccionadas = 0;

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

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public ArrayList<ObjetoBitacora> getListaBitacora() {
        return ListaBitacora;
    }

    public void setListaBitacora(ArrayList<ObjetoBitacora> listaBitacora) {
        ListaBitacora = listaBitacora;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTotalButacas() {
        return totalButacas;
    }

    public int getButacasSeleccionadas() {
        return butacasSeleccionadas;
    }

    public void setButacasSeleccionadas(int butacasSeleccionadas) {
        this.butacasSeleccionadas = butacasSeleccionadas;
    }
}
