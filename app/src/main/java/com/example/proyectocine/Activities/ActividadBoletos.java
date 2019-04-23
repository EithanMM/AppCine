package com.example.proyectocine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.R;

public class ActividadBoletos extends claseBase {

    private TextView cant_boletos_edad3,resta_boletos_edad3,suma_boletos_edad3,
            cant_boletos_adulto,resta_boletos_adulto,suma_boletos_adulto
            ,vista_total;

    private Button btnEscogerCampos;
    private int contador_boletos_edad3=0,contador_boletos_adulto=0;
    private final int PRECIO_BOLETO_ADULTO = 3200;
    private final int PRECIO_BOLETO_TERCERA_EDAD = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_boletos);

        btnEscogerCampos = (Button) findViewById(R.id.btnEscogerCampos);

        cant_boletos_edad3 = findViewById(R.id.cant_boletos_edad3);
        resta_boletos_edad3 = findViewById(R.id.resta_boletos_edad3);
        suma_boletos_edad3 = findViewById(R.id.suma_boletos_edad3);

        cant_boletos_adulto = findViewById(R.id.cant_boletos_adulto);
        resta_boletos_adulto = findViewById(R.id.resta_boletos_adulto);
        suma_boletos_adulto = findViewById(R.id.suma_boletos_adulto);

        vista_total = findViewById(R.id.vista_total);

        agregarEventos();


    }
    private void agregarEventos(){
//        private TextView cant_boletos_edad3,resta_boletos_edad3,suma_boletos_edad3,
//                cant_boletos_adulto,resta_boletos_adulto,suma_boletos_adulto
//                ,vista_total;


        resta_boletos_edad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contador_boletos_edad3>0){
                    contador_boletos_edad3--;
                    cant_boletos_edad3.setText(""+contador_boletos_edad3);
                }
                int semi_total = PRECIO_BOLETO_TERCERA_EDAD*contador_boletos_edad3
                        +PRECIO_BOLETO_ADULTO*contador_boletos_adulto;
                vista_total.setText(""+semi_total);

            }
        });
        suma_boletos_edad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador_boletos_edad3<10){
                    contador_boletos_edad3++;
                    cant_boletos_edad3.setText(""+contador_boletos_edad3);

                    int semi_total = PRECIO_BOLETO_TERCERA_EDAD*contador_boletos_edad3
                            +PRECIO_BOLETO_ADULTO*contador_boletos_adulto;

                    vista_total.setText(""+semi_total);
                }
            }
        });
        resta_boletos_adulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador_boletos_adulto>0){
                    contador_boletos_adulto--;
                    cant_boletos_adulto.setText(""+contador_boletos_adulto);

                    int semi_total = PRECIO_BOLETO_TERCERA_EDAD*contador_boletos_edad3
                            +PRECIO_BOLETO_ADULTO*contador_boletos_adulto;
                    vista_total.setText(""+semi_total);
                }
            }
        });
        suma_boletos_adulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador_boletos_adulto<10){
                    contador_boletos_adulto++;
                    cant_boletos_adulto.setText(""+contador_boletos_adulto);
                    int semi_total = PRECIO_BOLETO_TERCERA_EDAD*contador_boletos_edad3
                            +PRECIO_BOLETO_ADULTO*contador_boletos_adulto;

                    vista_total.setText(""+semi_total);
                }
            }

        });
        btnEscogerCampos.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                int cant_edad3 = Integer.parseInt((cant_boletos_edad3.getText().toString()));
                int cant_adulto = Integer.parseInt((cant_boletos_edad3.getText().toString()));
                if(cant_edad3 !=0 || cant_adulto!= 0){
                    // escriba lo que desea hacer
                    Intent intento = new Intent(getApplicationContext(), ActivitySeleccionButacas.class);
                    startActivity(intento);
                }else{
                    Mensaje("Debe seleccionar cuantos boletos deseea comprar.");
                }
            }

        });

    }
}
