package com.example.proyectocine.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.proyectocine.R;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;

public class ActivitySeleccionButacas extends claseBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_butacas);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));

        VariablesGlobales vg = VariablesGlobales.getInstance();
        TextView Mi_textview = (TextView) findViewById(R.id.input_asientos);
        vg.setTextHelper(Mi_textview);

        OnclickDelButton(R.id.btn_continuar);
        OnclickDelButton(R.id.btn_volver);
    }

}
