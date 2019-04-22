package com.example.proyectocine.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.Helpers.ObjetosxDesplegar;
import com.example.proyectocine.R;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends claseBase {
    //Esto es una prueba desde el proyecto.
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("cineTI", MODE_PRIVATE);
        CrearYAbrirBaseDeDatos();
        if(prefs.getBoolean("firstrun", true)){
            DropearYCrearBD();
            prefs.edit().putBoolean("firstrun", false).commit();
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        LlenarListaObjetos();
        LlenarListView();
        RegistrarClicks();
    }

    private List<ObjetosxDesplegar> misObjetos = new ArrayList<ObjetosxDesplegar>();
    private void LlenarListaObjetos() {
        ArrayList<ObjetoFuncion> funciones = ObtenerTodasFunciones();
        for(ObjetoFuncion of : funciones){
            misObjetos.add(new ObjetosxDesplegar(of.getNombrePelicula(), of.getGenero(),of.getNombreSala(),of.getDiaFuncion()+"-"+FormatoHora(of.getHoraInicio().toString()), DeterminarImagen(of.getIdPelicula()), of.getIdPelicula()));
        }
    }
    private void LlenarListView() {
        ArrayAdapter<ObjetosxDesplegar> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list_view_peliculas);
        list.setAdapter(adapter);
    }

    private void RegistrarClicks() {
        ListView list = (ListView) findViewById(R.id.list_view_peliculas);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                ObjetosxDesplegar ObjEscogido = misObjetos.get(position);
                ArrayList<ObjetoFuncion> funcion = ObtenerTodasFunciones();
                VariablesGlobales vg = VariablesGlobales.getInstance();

                vg.setNombrePelicula(funcion.get(position).getNombrePelicula());
                vg.setDiaFuncion(funcion.get(position).getDiaFuncion());
                vg.setHoraFuncion(funcion.get(position).getHoraInicio());
                vg.setNombreSala(funcion.get(position).getNombreSala());
                vg.setIdPelicula(funcion.get(position).getIdPelicula());

                ArrayList<ObjetoBitacora> registros = ObtenerRegistrosDeBitacoraPorFuncion();
                vg.setListaBitacora(registros);

                //Intent intento = new Intent(getApplicationContext(), ActivityInfoPelicula.class); /*Modulo de Tony*/
                Intent intento = new Intent(getApplicationContext(), ActividadBoletos.class); /* <- Modulo de Eithan*/
                startActivity(intento);
            }
        });
    }
    private class MyListAdapter extends ArrayAdapter<ObjetosxDesplegar> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.desplegandopeliculas, misObjetos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.desplegandopeliculas, parent, false);
            }
            final ObjetosxDesplegar ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ObjetoActual.getNumDibujo());
            //Programamos el evento onclick

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {

                    VariablesGlobales vg = VariablesGlobales.getInstance();
                    vg.setIdPelicula(ObjetoActual.getIdPelicula());

                    Intent intento = new Intent(getApplicationContext(), ActivityInfoPelicula.class); /*Modulo de Tony*/
                    startActivity(intento);
                }
            });

            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getAtributo01());

            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText(ObjetoActual.getAtributo02());

            TextView elatributo03 = (TextView) itemView.findViewById(R.id.paraelatributo03);
            elatributo03.setText(ObjetoActual.getAtributo03());

            TextView elatributo04 = (TextView) itemView.findViewById(R.id.paraelatributo04);
            elatributo04.setText(ObjetoActual.getAtributo04());

            return itemView;
        }
    }


}