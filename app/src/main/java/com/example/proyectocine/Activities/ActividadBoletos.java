package com.example.proyectocine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectocine.R;

public class ActividadBoletos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_boletos);

        Button Mi_button = (Button) findViewById(R.id.escogerCampos);

        Mi_button.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {

                // escriba lo que desea hacer
                Intent intento = new Intent(getApplicationContext(), ActivitySeleccionButacas.class);
                startActivity(intento);



            }

        });
    }
}
