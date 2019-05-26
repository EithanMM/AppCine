package com.example.proyectocine.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Activities.ActividadBoletos;
import com.example.proyectocine.Activities.ActivityInfoPelicula;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.Helpers.Horario;
import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class Fragment_horario extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener {

    //private String idPelicula="";

    // TODO: Rename and change types of parameters
    private String nombrePelicula;
    private String mParam2;
    ListView listView;
    ArrayList<Horario> elementos = new ArrayList<>();

    String mensajeAccion = "";
    public RequestQueue requestQueue;
    public JsonArrayRequest jsonArrayRequest;


    public Fragment_horario() {
        // Required empty public constructor
    }

    //public void setIdPelicula(String idPelicula) {
    //this.idPelicula = idPelicula;
    //}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        VariablesGlobales vg = VariablesGlobales.getInstance();
        Toast.makeText(getContext(), vg.getIdPelicula(), Toast.LENGTH_LONG).show();

        //LlenarListView();
        //DandoClickALosItems();

        return inflater.inflate(R.layout.fragment_fragment_horario, container, false);
    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = (ListView) getView().findViewById(R.id.listView1);

        VariablesGlobales vg = VariablesGlobales.getInstance();


        String url = "http://192.168.150.1/Android/v1/consultarHorarios.php?id_pelicula=" + vg.getIdPelicula();
        mensajeAccion = "ListarHorarios";
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(jsonArrayRequest);


    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {

        try {

            //Time hora;
            switch (mensajeAccion) {

                case "ListarHorarios":

                    for (int i = 0; i < response.length(); i++) {

                        String temp = response.get(i).toString();
                        JSONObject jsonObject = new JSONObject(temp);

                        //ObjetoFuncion obj = new ObjetoFuncion();
                        Horario funcion = new Horario(jsonObject.getInt("IdFuncion") + "", jsonObject.getString("IdPelicula"), jsonObject.getString("IdSala"),
                                jsonObject.getString("DiaFuncion"), jsonObject.getString("HoraInicio"));


                        elementos.add(funcion);
                    }

                    ArrayAdapter<Horario> adapter = new ArrayAdapter<Horario>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, elementos);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Toast.makeText(getActivity().getApplicationContext(), elementos.get(position).toString()+ elementos.get(position).getIdFuncion(), Toast.LENGTH_SHORT).show();

                            VariablesGlobales vg = VariablesGlobales.getInstance();
                            //vg.setNombrePelicula(funcion.get(position).getNombrePelicula());
                            vg.setDiaFuncion(elementos.get(position).getDiaFuncion());
                            vg.setHoraFuncion(FormatoHoraBD(elementos.get(position).getHoraInicio()));
                            vg.setNombreSala(elementos.get(position).getIdSala());
                            vg.setIdPelicula(elementos.get(position).getIdFuncion());

                            //ArrayList<ObjetoBitacora> registros = ActivityInfoPelicula.ObtenerRegistrosDeBitacoraPorFuncion();
                            //vg.setListaBitacora(registros);

                            //Intent intento = new Intent(getApplicationContext(), ActivityInfoPelicula.class); /*Modulo de Tony*/
                            Intent intento = new Intent(getContext(), ActividadBoletos.class); /* <- Modulo de Eithan*/
                            startActivity(intento);

                        }
                    });

                    //vg.setListaFuncion(funciones);
                    break;


                default:
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //LlenarListaObjetos();
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
}
