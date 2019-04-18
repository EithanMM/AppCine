package com.example.proyectocine.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectocine.R;
import com.example.proyectocine.Controllers.claseBase;

public class ActivityPagoTiquete extends claseBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_tiquete);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));

        CrearYAbrirBaseDeDatos();

        OnclickDelButton(R.id.btn_realiar_pago);
        OnclickDelButton(R.id.btn_vovler_seleccion_butacas);

        TextView cedula = (TextView) findViewById(R.id.cedula_input);
        TextView nombre = (TextView) findViewById(R.id.nombre_input);
        TextView apellido = (TextView) findViewById(R.id.apellido_input);
        TextView correo = (TextView) findViewById(R.id.correo_input);

        InicializamosTextViewsParaCorreo(cedula,nombre,apellido,correo);
    }
}
