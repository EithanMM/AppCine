package com.example.proyectocine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends claseBase {
    //Esto es una prueba desde el proyecto.
    private List<ObjetosxDesplegar> misObjetos = new ArrayList<ObjetosxDesplegar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        LlenarListaObjetos();
        LlenarListView();
        RegistrarClicks();
    }

    private void LlenarListaObjetos() {
        misObjetos.add(new ObjetosxDesplegar("Coco", "DRAMA", "01:00 p m",R.drawable.coco));
        misObjetos.add(new ObjetosxDesplegar("Bohemian Rhapsody: la historia de Freddie Mercury","DRAMA", "01:00 p m", R.drawable.bohemian));
        misObjetos.add(new ObjetosxDesplegar("Coco", "DRAMA", "01:00 p m", R.drawable.coco));
        misObjetos.add(new ObjetosxDesplegar("Doctor Strange: hechicero supremo", "DRAMA", "01:00 p m", R.drawable.doctor));
        misObjetos.add(new ObjetosxDesplegar("Bohemian Rhapsody: la historia de Freddie Mercury", "DRAMA", "01:00 p m", R.drawable.bohemian));
        misObjetos.add(new ObjetosxDesplegar("Coco", "DRAMA", "01:00 p m", R.drawable.coco));
        misObjetos.add(new ObjetosxDesplegar("Doctor Strange: hechicero supremo", "DRAMA", "01:00 p m", R.drawable.doctor));
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
            public void onItemClick(AdapterView<?> parent, View viewClicked,  int position, long id) {

                Mensaje("hola");
               // ObjetosxDesplegar ObjEscogido = misObjetos.get(position);
                //String message = "Elegiste item No.  " + (1 + position)
                  //      + " que es un objeto cuyo primer atributo es  " + ObjEscogido.getAtributo01();
                //Mensaje(message);
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
            ObjetosxDesplegar ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ObjetoActual.getNumDibujo());
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getAtributo01());

            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText(ObjetoActual.getAtributo02());

            TextView elatributo03 = (TextView) itemView.findViewById(R.id.paraelatributo03);
            elatributo03.setText(ObjetoActual.getAtributo03());

            return itemView;
        }
    }
}
