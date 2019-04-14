package com.example.proyectocine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class claseBase extends AppCompatActivity {

    DBAdapterSQL db;

    public void MensajeOK(String msg) {
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        ;
    }

    /*-----------------------------Metodos publicos de manejo de BD-------------------------------*/
    public void CrearYAbrirBaseDeDatos() {
        if (db == null) {
            db = new DBAdapterSQL(this);
            db.open();
        }
    }

        public void DesplegarTodosLosRegistros (){
            if (db != null) {
                MensajeOK(db.ObtenerTodosLosRegistros());
            } else {
                MensajeOK("BD nula");
            }
        }

        public void ObtenerButacasOcupadasPorFuncion(){
            MensajeOK(db.ObtenerButacasOcupadasPorFuncion(1));
        }

        public void DropearYCrearBD(){
            MensajeOK(db.DropearYCrearBD());
        }
    /*--------------------------------------------------------------------------------------------*/
    /*---------------------------------Otros Metodos----------------------------------------------*/
    public void MostrarAsientosSeleccionados(VariablesGlobales vg){
        TextView Mi_textview = (TextView) findViewById(R.id.input_asientos);
        String datos = "";
        int tam = vg.getListaAsientos().size();
        for(int i = 0; i < tam; i++){
            if(tam == 1){
                datos+= vg.getListaAsientos().get(i).toString();
            } else {
                datos+= vg.getListaAsientos().get(i).toString()+",";
            }
        }
        Mi_textview.setText(datos);
    }
    /*--------------------------------------------------------------------------------------------*/
    }

