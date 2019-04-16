package com.example.proyectocine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_informacion_compra extends Fragment {


    public Fragment_informacion_compra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_info_pelicula, container, false);

        PintarInformacionDeTiquete(view);
        //espacio_dia_funcion.setText(vg.getDiaFuncion());
        //espacio_nombre_sala.setText(vg.getNombreSala());
        //espacio_hora_funcion.setText(vg.getHoraFuncion());
        //MostrarAsientosSeleccionados(espacio_asientos_seleccionados, vg);
        return view;
    }

    public void MostrarAsientosSeleccionados(TextView tv, VariablesGlobales vg){
        ArrayList<String> asientos = vg.getListaAsientos();
        String resp = "";
        for(String dato : asientos){
            if(asientos.size() == 1){ resp+= dato;}
            else { resp+=dato+","; }
        }
        tv.setText(resp);
    }

    public void PintarInformacionDeTiquete(View view){

        VariablesGlobales vg = VariablesGlobales.getInstance();

        TextView espacio_nombre_pelicula  = (TextView) view.findViewById(R.id.output_nombre_pelicula);
        TextView espacio_nombre_sala = (TextView) view.findViewById(R.id.output_nombre_sala);
        TextView espacio_dia_funcion = (TextView) view.findViewById(R.id.output_dia);
        TextView espacio_hora_funcion = (TextView) view.findViewById(R.id.output_hora);
        TextView espacio_asientos_seleccionados = (TextView) view.findViewById(R.id.output_asientos);
        TextView espacio_precio = (TextView) view.findViewById(R.id.output_precio);

        espacio_nombre_pelicula.setText(vg.getNombrePelicula());
        espacio_nombre_sala.setText(vg.getNombreSala());
        espacio_dia_funcion.setText(vg.getDiaFuncion());
        espacio_hora_funcion.setText(String.valueOf(vg.getHoraFuncion()));
        espacio_asientos_seleccionados.setText(ObtenerAsientosSeleccionados(vg));
        espacio_precio.setText(CalcularPrecio(vg));
    }

    private String ObtenerAsientosSeleccionados(VariablesGlobales vg){
        ArrayList<String> butacas = vg.getListaAsientos();
        String resp = "";
        for(String dato : butacas){
            if(butacas.size() == 1){
                resp+=dato;
            } else {
                resp+=dato+", ";
            }
        }
        return resp;
    }

    private String CalcularPrecio(VariablesGlobales vg){
        ArrayList<String> butacas = vg.getListaAsientos();
        int numButacas = butacas.size();
        int resultado = numButacas * 1500;

        return String.format("₡ %,.1f", (float)resultado);
    }

}
