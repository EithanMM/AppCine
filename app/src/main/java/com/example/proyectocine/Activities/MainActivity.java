package com.example.proyectocine.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.Helpers.ObjetosxDesplegar;
import com.example.proyectocine.R;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends claseBase implements Response.Listener<JSONArray>, Response.ErrorListener {
    //Esto es una prueba desde el proyecto.
    SharedPreferences prefs = null;
    SliderLayout sliderLayout;
    public static int MY_DEFAULT_TIMEOUT = 5000;

    VariablesGlobales vg = VariablesGlobales.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("cineTI", MODE_PRIVATE);
        CrearYAbrirBaseDeDatos();
        if(prefs.getBoolean("firstrun", true)){
            DropearYCrearBD();
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        ObtenerTodasFunciones(this, vg);




        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        peliculasEnSlider();

        // caca();
    }


    public void ObtenerTodasFunciones(Context context, VariablesGlobales variable){
        String url = "http://192.168.0.10/Android/v1/mostrarFunciones.php";
        mensajeAccion = "ListarFunciones";
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(jsonArrayRequest);
    }



    private void peliculasEnSlider() {

        for (int i = 0; i <= 7; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.bohemian);
                    sliderView.setDescription("Bohemian Rhapsody");

                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.cementerio_maldito);
                    sliderView.setDescription("Cementerio Maldito");

                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.capitana_marvel);
                    sliderView.setDescription("Capitana Marvel");

                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.infinity_war);
                    sliderView.setDescription("Avengers");

                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.pasion_cristo);
                    sliderView.setDescription("La Pasión de Cristo");

                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.the_ring);
                    sliderView.setDescription("El Aro");

                    break;
                case 6:
                    sliderView.setImageDrawable(R.drawable.doctor_strange);
                    sliderView.setDescription("Doctor Strange");

                    break;
                case 7:
                    sliderView.setImageDrawable(R.drawable.coco);
                    sliderView.setDescription("Coco");

                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(MainActivity.this, "Sería tuanis mandarlo al activity de Antohny", Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    private List<ObjetosxDesplegar> misObjetos = new ArrayList<ObjetosxDesplegar>();

    private void LlenarListaObjetos() {
        ArrayList<ObjetoFuncion> funciones = vg.getListaFuncion();
        for(ObjetoFuncion of : funciones){
            misObjetos.add(new ObjetosxDesplegar(of.getNombrePelicula(), of.getGenero(),of.getNombreSala(),of.getDiaFuncion()+"-"+FormatoHora(of.getHoraInicio().toString()), DeterminarImagen(of.getIdPelicula()), of.getIdPelicula()));
        }
        LlenarListView();
    }

    private void LlenarListView() {
        ArrayAdapter<ObjetosxDesplegar> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list_view_peliculas);
        list.setAdapter(adapter);

        RegistrarClicks();
    }

    private void RegistrarClicks() {
        ListView list = (ListView) findViewById(R.id.list_view_peliculas);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {
                ObjetosxDesplegar ObjEscogido = misObjetos.get(position);
                ArrayList<ObjetoFuncion> funcion = vg.getListaFuncion();
                VariablesGlobales vg = VariablesGlobales.getInstance();

                vg.setNombrePelicula(funcion.get(position).getNombrePelicula());
                vg.setDiaFuncion(funcion.get(position).getDiaFuncion());
                vg.setHoraFuncion(funcion.get(position).getHoraInicio());
                vg.setNombreSala(funcion.get(position).getNombreSala());
                vg.setIdPelicula(funcion.get(position).getIdPelicula());

                ArrayList<ObjetoBitacora> registros = ObtenerRegistrosDeBitacoraPorFuncion();
                vg.setListaBitacora(registros);

                //Intent intento = new Intent(getApplicationContext(), ActivityInfoPelicula.class); /*Modulo de Tony*/
                Intent intento = new Intent(getApplicationContext(), ActividadBoletos.class); /* <- Modulo de Eithan*/
                startActivity(intento);
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
            final ObjetosxDesplegar ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ObjetoActual.getNumDibujo());
            //Programamos el evento onclick

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {

                    VariablesGlobales vg = VariablesGlobales.getInstance();
                    vg.setIdPelicula(ObjetoActual.getIdPelicula());

                    String nombrePelicula = null;
                    for(int i=0; i<funciones.size();i++){
                        if(funciones.get(i).getIdPelicula().equals(ObjetoActual.getIdPelicula())){
                            nombrePelicula = funciones.get(i).getNombrePelicula();
                            break;
                        }
                    }
                    vg.setNombrePelicula(nombrePelicula);

                    Intent intento = new Intent(getApplicationContext(), ActivityInfoPelicula.class); /*Modulo de Tony*/
                    startActivity(intento);
                }
            });

            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getAtributo01());

            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText(ObjetoActual.getAtributo02());

            TextView elatributo03 = (TextView) itemView.findViewById(R.id.paraelatributo03);
            elatributo03.setText(ObjetoActual.getAtributo03());

            TextView elatributo04 = (TextView) itemView.findViewById(R.id.paraelatributo04);
            elatributo04.setText(ObjetoActual.getAtributo04());

            return itemView;
        }
    }
    // colocar al abrir la clase de la activity
    @Override
    public void onBackPressed() {
        //Toast.makeText(getApplicationContext(), "Te atrape", Toast.LENGTH_LONG).show();
        // super.onBackPressed(); habilite esto si desea que se devuelva con el boton back
        // Button MiBoton = (Button) findViewById(R.id.button2);
        //MiBoton.performClick();
        // cerrarApplicacion();
    }

    private Time FormatoHoraBD(String hora){
        DateFormat formatter = new SimpleDateFormat("K:mm");
        Time time = null;
        try{
            time = new Time(formatter.parse(hora).getTime());
        }catch(ParseException ex){
            Log.d(TAG, "Error:"+ex.getMessage());
        }
        return time;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {

        try {

            Time hora;
            switch(mensajeAccion) {

                case "ListarFunciones":

                    for(int i = 0; i < response.length(); i++) {

                        String temp = response.get(i).toString();
                        JSONObject jsonObject = new JSONObject(temp);

                        ObjetoFuncion obj = new ObjetoFuncion();
                        obj.setID(jsonObject.getString("Id_Funcion"));
                        obj.setIdPelicula(jsonObject.getString("Id_Pelicula"));
                        obj.setIdSala(jsonObject.getString("Id_Sala"));
                        obj.setNombrePelicula(jsonObject.getString("NombrePelicula"));
                        obj.setNombreSala(jsonObject.getString("NombreSala"));
                        obj.setGenero(jsonObject.getString("Tipo"));
                        obj.setDiaFuncion(jsonObject.getString("DiaFuncion"));
                        obj.setHoraInicio(hora = FormatoHoraBD(jsonObject.getString("HoraInicio")));

                        funciones.add(obj);
                    }
                    vg.setListaFuncion(funciones);
                    break;

                case "ListarCamposOcupados":
                    break;

                case "ListarBitacora":
                    break;

                case "AgregarBitacora":
                    break;

                default:
                    break;
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
        LlenarListaObjetos();
    }



}