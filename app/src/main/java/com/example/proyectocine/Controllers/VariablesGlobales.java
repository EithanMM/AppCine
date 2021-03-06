package com.example.proyectocine.Controllers;

import android.graphics.Bitmap;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.Helpers.ObjetosxDesplegar;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class VariablesGlobales {

    private ArrayList<String> ListaAsientos = new ArrayList<>();
    private String AsientosSeleccionados;


    private ArrayList<ObjetoBitacora> ListaBitacora = new ArrayList<>();
    private ArrayList<ObjetoFuncion> ListaFuncion = new ArrayList<>();

    private TextView TextHelper;
    private TableLayout TablaButacas;
    private String idPelicula;
    private String NombrePelicula;
    private String NombreSala;
    private String DiaFuncion;
    private Time HoraFuncion;
    private String cedula;


    private Bitmap bitmap;


    private int precioTotal;
    private int totalButacas = 10;
    private int butacasSeleccionadas = 0;


    private String nombreUsuario;
    private String apellidosUsuario;
    private String cedulaUsuario;
    private String correo;


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

    public String getAsientosSeleccionados() {
        return AsientosSeleccionados;
    }

    public void setAsientosSeleccionados(String asientosSeleccionados) {
        AsientosSeleccionados = asientosSeleccionados;
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

    public ArrayList<ObjetoFuncion> getListaFuncion() {
        return ListaFuncion;
    }

    public void setListaFuncion(ArrayList<ObjetoFuncion> listaFuncion) {
        ListaFuncion = listaFuncion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
