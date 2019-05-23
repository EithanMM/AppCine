package com.example.proyectocine.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;


public class Fragment_horario extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener {

    //private String idPelicula="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;
    ArrayList<String> elementos = new ArrayList<>();

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

    private void LlenarListView() {
        String[] presidentes = {
                "Dwight D. Eisenhower",
                "John F. Kennedy",
                "Lyndon B. Johnson",
                "Richard Nixon",
                "Gerald Ford",
                "Jimmy Carter",
                "Ronald Reagan",
                "George H. W. Bush",
                "Bill Clinton",
                "George W. Bush",
                "Barack Obama"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, presidentes);
        ListView milistview = (ListView) getView().findViewById(R.id.listView1);
        milistview.setAdapter(adaptador);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = (ListView) getView().findViewById(R.id.listView1);

        VariablesGlobales vg = VariablesGlobales.getInstance();


        String url = "http://192.168.0.10/Android/v1/consultarHorarios.php?id_pelicula=" + vg.getIdPelicula();
        mensajeAccion = "ListarHorarios";
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(jsonArrayRequest);


    }


    public void DandoClickALosItems() {
        ListView list = (ListView) getView().findViewById(R.id.listView1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked,
                                    int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "Presidente # " + (1 + position) + ", corresponde a: " + textView.getText().toString();
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
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
                        String funcion = "Sala: " + jsonObject.getInt("IdSala") + ", " + jsonObject.getString("DiaFuncion") + " a las " + jsonObject.getString("HoraInicio");


                        elementos.add(funcion);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1, elementos);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

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
}
