package com.example.proyectocine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class claseBase extends AppCompatActivity {

     private DBAdapterSQL db;
     private GmailHelper  gh = new GmailHelper();


     private TextView cedula;
     private TextView nombre;
     private TextView apellido;
     private TextView correo;

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

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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

        public ArrayList<ObjetoFuncion> ObtenerTodasFunciones(){
            return db.ObtenerTodasFunciones();
        }

        public void InsertarRegistroEnBitacora(){
            CrearYAbrirBaseDeDatos();
            ArrayList<ObjetoFuncion> funciones = ObtenerTodasFunciones();
            VariablesGlobales vg = VariablesGlobales.getInstance();
            for(ObjetoFuncion of : funciones){
                if(of.getNombrePelicula().equals(vg.getNombrePelicula())&&
                   of.getNombreSala().equals(vg.getNombreSala())&&
                   of.getDiaFuncion().equals(vg.getDiaFuncion())&&
                   of.getHoraInicio().equals(vg.getHoraFuncion())){

                    db.InsertarRegistroEnBitacora(of, vg, nombre.getText().toString(), apellido.getText().toString(), cedula.getText().toString());
                    break;
                }
            }
        }
    /*--------------------------------------------------------------------------------------------*/
    /*---------------------------------Otros Metodos----------------------------------------------*/

    public int DeterminarImagen(String id_pelicula){
        int res = 0;
        switch(Integer.parseInt(id_pelicula)){
            case 1:
                //Coco
                    res = R.drawable.coco;
                break;
            case 2:
                //Cementerio
                res = R.drawable.cementerio_maldito;
                break;
            case 3:
                //Capitana Marvel
                res = R.drawable.capitana_marvel;
                break;
            case 4:
                //Avenger
                res = R.drawable.infinity_war;
                break;
            case 5:
                //La Pasion
                res = R.drawable.pasion_cristo;
                break;
            case 6:
                //El Aro
                res = R.drawable.the_ring;
                break;
            case 7:
                //Doctor Strange
                res = R.drawable.doctor_strange;
                break;
            case 8:
                //Bohemian
                res = R.drawable.bohemian;
                break;
            case 9:
                //La Forma del Agua
                res = R.drawable.forma_agua;
                break;
            case 10:
                //Dragon Ball
                res = R.drawable.dragon_ball;
                break;
        }
        return res;
    }

    public void OnclickDelButton(int ref) {
        View view =findViewById(ref);
        Button miButton = (Button) view;
        miButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intento;
                VariablesGlobales vg = VariablesGlobales.getInstance();
                ArrayList<String> asientos = vg.getListaAsientos();
                switch (v.getId()) {

                    case R.id.btn_continuar:
                        if(asientos.size() == 0){Mensaje("Debe seleccionar almenos una butaca."); break;}
                        else {
                            intento = new Intent(getApplicationContext(), ActivityPagoTiquete.class);
                            startActivity(intento);
                            break;
                        }

                    case R.id.btn_volver:
                        intento = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intento);
                        break;

                    case R.id.btn_realiar_pago:
                        intento = new Intent(getApplicationContext(), MainActivity.class);
                        //InsertarRegistroEnBitacora();
                        EnviarEmail(intento);
                        break;
                    case R.id.btn_vovler_seleccion_butacas:
                        intento = new Intent(getApplicationContext(), ActivitySeleccionButacas.class);
                        startActivity(intento);
                        break;
                    default:break; }
            }
        });
    }// fin de OnclickDelButton

    public void InicializamosTextViewsParaCorreo(TextView cedula, TextView nombre, TextView apellido, TextView correo){
        this.cedula = cedula;
        this.nombre =  nombre;
        this.apellido =  apellido;
        this.correo = correo;
    }

    private void EnviarEmail( Intent intento){
        VariablesGlobales vg = VariablesGlobales.getInstance();
        if(gh.EnviarEmail(cedula, nombre, apellido, correo, vg)){
            MensajeOK(("Compra realizada exitosamente!"));
            Iterator<String> it = vg.getListaAsientos().iterator();
            LimpiarListaAsientos(vg);
            intento = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intento);
        } else {
            MensajeOK("Ocurrio un error a la hora de realizar el pago.");
        }

    }

    public void LimpiarListaAsientos(VariablesGlobales vg){
        vg.getListaAsientos().clear();
    }

    public String FormatoHora(String hora){
        Calendar calendar = GenerarCalendario(hora);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

        return sdf.format(d);
    }


    private Calendar GenerarCalendario(String hora){
        int H = Integer.parseInt(hora.substring(0,2));
        int M = Integer.parseInt(hora.substring(3,5));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,H);
        cal.set(Calendar.MINUTE,M);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }
    /*--------------------------------------------------------------------------------------------*/
    }

