package com.example.proyectocine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySeleccionButacas extends claseBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_butacas);
        TextView Mi_textview = (TextView) findViewById(R.id.input_asientos);

        VariablesGlobales vg = VariablesGlobales.getInstance();
        vg.setTextHelper(Mi_textview);

          CrearYAbrirBaseDeDatos();
          //DropearYCrearBD();
          //DesplegarTodosLosRegistros();
          //ObtenerButacasOcupadasPorFuncion();
    }
}
