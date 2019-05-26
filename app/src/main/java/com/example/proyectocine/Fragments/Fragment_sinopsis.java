package com.example.proyectocine.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Fragment_sinopsis extends Fragment  implements Response.Listener<JSONArray>, Response.ErrorListener {
    private String idPelicula = "";
    private String sinopsis="";

    View view = null;

    public RequestQueue requestQueue;
    public JsonArrayRequest jsonArrayRequest;

    public static final int MY_DEFAULT_TIMEOUT = 15000;

    public Fragment_sinopsis() {
        // Required empty public constructor
    }

    public void setIdPelicula(String id){
        this.idPelicula = id;
    }

    public void setSinopsis(String s){
        this.sinopsis = s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragment_sinopsis, container, false);
        DesplegarInfoPelicula(VariablesGlobales.getInstance().getIdPelicula());

        view =inflater.inflate(R.layout.fragment_fragment_sinopsis, container, false);
        TextView MiTextView = (TextView) view.findViewById(R.id.textViewSinopsis);
        MiTextView.setText(sinopsis);

        return view;
    }

    public void DesplegarInfoPelicula(String idPelicula) {
        String url = "http://192.168.0.10/Android/v1/consultarSinopsis.php?id_pelicula="+idPelicula;

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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
            for (int i = 0; i < response.length(); i++) {

                String temp = response.get(i).toString();
                JSONObject jsonObject = new JSONObject(temp);
                String sinopsis = jsonObject.getString("Sinopsis");
                String publico  = jsonObject.getString("Publico");
                String tipo = jsonObject.getString("Tipo");
                String msg= "Publico: "+publico+"      Tipo: "+tipo+" \n\n\n\n Sinopsis: "+sinopsis+ "\n";
                this.setSinopsis(msg);


            }

            TextView MiTextView = (TextView) view.findViewById(R.id.textViewSinopsis);
            MiTextView.setText(sinopsis);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
