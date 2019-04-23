package com.example.proyectocine.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectocine.R;

public class ActivityCeds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceds);

        // alambramos el Button
        Button MiButton = (Button) findViewById(R.id.button2);

        //Programamos el evento onclick

        MiButton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {

                Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intento);


            }

        });
    }
}
