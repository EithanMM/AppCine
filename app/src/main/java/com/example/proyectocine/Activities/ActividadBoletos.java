package com.example.proyectocine.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.R;

public class ActividadBoletos extends claseBase {

<<<<<<< HEAD

    private TextView cant_boletos_edad3,resta_boletos_edad3,suma_boletos_edad3,
            cant_boletos_adulto,resta_boletos_adulto,suma_boletos_adulto
            ,vista_total;
=======
>>>>>>> 6101319f99e5aaa0f8df7755b26de15de5a96924


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_boletos);

<<<<<<< HEAD

        btnEscogerCampos = (Button) findViewById(R.id.btnEscogerCampos);
=======
        Button btnEscogerCampos = (Button) findViewById(R.id.btnEscogerCampos);
>>>>>>> 6101319f99e5aaa0f8df7755b26de15de5a96924

        TextView cant_boletos_edad3 = findViewById(R.id.cant_boletos_edad3);
        TextView resta_boletos_edad3 = findViewById(R.id.resta_boletos_edad3);
        TextView suma_boletos_edad3 = findViewById(R.id.suma_boletos_edad3);

        TextView cant_boletos_adulto = findViewById(R.id.cant_boletos_adulto);
        TextView resta_boletos_adulto = findViewById(R.id.resta_boletos_adulto);
        TextView suma_boletos_adulto = findViewById(R.id.suma_boletos_adulto);

        TextView vista_total = findViewById(R.id.vista_total);

        iniciarParametrosTextViewParaBoletos(cant_boletos_edad3,resta_boletos_edad3,
                                             suma_boletos_edad3,cant_boletos_adulto,
                                             resta_boletos_adulto,suma_boletos_adulto,
                                             vista_total,btnEscogerCampos);

        agregarEventos();


    }
}
