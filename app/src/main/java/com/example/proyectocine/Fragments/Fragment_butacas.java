package com.example.proyectocine.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_butacas extends Fragment implements Response.Listener<JSONArray>, Response.ErrorListener {

    private int contador = 0;
    public RequestQueue requestQueue;
    public JsonArrayRequest jsonArrayRequest;
    public String mensajeAccion = "";
    public static final int MY_DEFAULT_TIMEOUT = 15000;
    public Fragment_butacas() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment_butacas, container, false);
        OnclickDelImageView(view.findViewById(R.id.A1));
        OnclickDelImageView(view.findViewById(R.id.A2));
        OnclickDelImageView(view.findViewById(R.id.A3));
        OnclickDelImageView(view.findViewById(R.id.A4));
        OnclickDelImageView(view.findViewById(R.id.A5));
        OnclickDelImageView(view.findViewById(R.id.B1));
        OnclickDelImageView(view.findViewById(R.id.B2));
        OnclickDelImageView(view.findViewById(R.id.B3));
        OnclickDelImageView(view.findViewById(R.id.B4));
        OnclickDelImageView(view.findViewById(R.id.B5));
        OnclickDelImageView(view.findViewById(R.id.C1));
        OnclickDelImageView(view.findViewById(R.id.C2));
        OnclickDelImageView(view.findViewById(R.id.C3));
        OnclickDelImageView(view.findViewById(R.id.C4));
        OnclickDelImageView(view.findViewById(R.id.C5));
        OnclickDelImageView(view.findViewById(R.id.D1));
        OnclickDelImageView(view.findViewById(R.id.D2));
        OnclickDelImageView(view.findViewById(R.id.D3));
        OnclickDelImageView(view.findViewById(R.id.D4));
        OnclickDelImageView(view.findViewById(R.id.D5));
        OnclickDelImageView(view.findViewById(R.id.E1));
        OnclickDelImageView(view.findViewById(R.id.E2));
        OnclickDelImageView(view.findViewById(R.id.E3));
        OnclickDelImageView(view.findViewById(R.id.E4));
        OnclickDelImageView(view.findViewById(R.id.E5));
        OnclickDelImageView(view.findViewById(R.id.F1));
        OnclickDelImageView(view.findViewById(R.id.F2));
        OnclickDelImageView(view.findViewById(R.id.F3));
        OnclickDelImageView(view.findViewById(R.id.F4));
        OnclickDelImageView(view.findViewById(R.id.F5));

        VariablesGlobales vg = VariablesGlobales.getInstance();
        TableLayout Mi_tablelayout = (TableLayout) view.findViewById(R.id.tabla_butacas);
        vg.setTablaButacas(Mi_tablelayout);

        ConsultarButacas();
        //ObtenerButacasOcupadasPorFuncion(vg);
        return view;

    }

    public void OnclickDelImageView(View view) {
        ImageView miImageView = (ImageView)  view;
        final VariablesGlobales vg = VariablesGlobales.getInstance();
        miImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                switch (v.getId()) {

                    case R.id.A1:
                        cambiarButaca(R.id.A1,v);

                        break;

                    case R.id.A2:
                        cambiarButaca(R.id.A2,v);

                        break;

                    case R.id.A3:
                        cambiarButaca(R.id.A3,v);

                        break;

                    case R.id.A4:
                        cambiarButaca(R.id.A4,v);

                        break;

                    case R.id.A5:
                        cambiarButaca(R.id.A5,v);

                        break;

                    case R.id.B1:
                        cambiarButaca(R.id.B1,v);

                        break;

                    case R.id.B2:
                        cambiarButaca(R.id.B2,v);

                        break;

                    case R.id.B3:
                        cambiarButaca(R.id.B3,v);

                        break;

                    case R.id.B4:
                        cambiarButaca(R.id.B4,v);

                        break;

                    case R.id.B5:
                        cambiarButaca(R.id.B5,v);

                        break;

                    case R.id.C1:
                        cambiarButaca(R.id.C1,v);

                        break;

                    case R.id.C2:
                        cambiarButaca(R.id.C2,v);

                        break;

                    case R.id.C3:
                        cambiarButaca(R.id.C3,v);

                        break;

                    case R.id.C4:
                        cambiarButaca(R.id.C4,v);

                        break;

                    case R.id.C5:
                        cambiarButaca(R.id.C5,v);

                        break;

                    case R.id.D1:
                        cambiarButaca(R.id.D1,v);

                        break;

                    case R.id.D2:
                        cambiarButaca(R.id.D2,v);

                        break;

                    case R.id.D3:
                        cambiarButaca(R.id.D3,v);

                        break;

                    case R.id.D4:
                        cambiarButaca(R.id.D4,v);

                        break;

                    case R.id.D5:
                        cambiarButaca(R.id.D5,v);

                        break;

                    case R.id.E1:
                        cambiarButaca(R.id.E1,v);

                        break;

                    case R.id.E2:
                        cambiarButaca(R.id.E2,v);

                        break;

                    case R.id.E3:
                        cambiarButaca(R.id.E3,v);

                        break;

                    case R.id.E4:
                        cambiarButaca(R.id.E4,v);

                        break;

                    case R.id.E5:
                        cambiarButaca(R.id.E5,v);

                        break;

                    case R.id.F1:
                        cambiarButaca(R.id.F1,v);

                        break;

                    case R.id.F2:
                        cambiarButaca(R.id.F2,v);

                        break;

                    case R.id.F3:
                        cambiarButaca(R.id.F3,v);

                        break;

                    case R.id.F4:
                        cambiarButaca(R.id.F4,v);

                        break;

                    case R.id.F5:
                        cambiarButaca(R.id.F5,v);

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelImageView

    private void cambiarButaca(int ident, View view){
        ImageView miImageView = (ImageView)  view;
        VariablesGlobales vg = VariablesGlobales.getInstance();
        ImageView midib = (ImageView)view.findViewById(ident);
        String tag = String.valueOf(midib.getTag());

        switch(tag){

            case "DA1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SA1");
                    AgregarButacaEnLista(vg,"A1");
                }
                break;

            case "DA2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SA2");
                    AgregarButacaEnLista(vg,"A2");
                }
                break;

            case "DA3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SA3");
                    AgregarButacaEnLista(vg,"A3");
                }
                break;

            case "DA4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SA4");
                    AgregarButacaEnLista(vg,"A4");
                }
                break;

            case "DA5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SA5");
                    AgregarButacaEnLista(vg,"A5");
                }
                break;

            case "DB1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SB1");
                    AgregarButacaEnLista(vg,"B1");
                }
                break;

            case "DB2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SB2");
                    AgregarButacaEnLista(vg,"B2");
                }
                break;

            case "DB3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SB3");
                    AgregarButacaEnLista(vg,"B3");
                }
                break;

            case "DB4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SB4");
                    AgregarButacaEnLista(vg,"B4");
                }
                break;

            case "DB5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SB5");
                    AgregarButacaEnLista(vg,"B5");
                }
                break;

            case "DC1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SC1");
                    AgregarButacaEnLista(vg,"C1");
                }
                break;

            case "DC2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SC2");
                    AgregarButacaEnLista(vg,"C2");
                }
                break;

            case "DC3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SC3");
                    AgregarButacaEnLista(vg,"C3");
                }
                break;

            case "DC4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SC5");
                    AgregarButacaEnLista(vg,"C5");
                }
                break;

            case "DC5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SC5");
                    AgregarButacaEnLista(vg,"C5");
                }
                break;

            case "DD1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SD1");
                    AgregarButacaEnLista(vg,"D1");
                }
                break;

            case "DD2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SD2");
                    AgregarButacaEnLista(vg,"D2");
                }
                break;

            case "DD3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SD3");
                    AgregarButacaEnLista(vg,"D3");
                }
                break;

            case "DD4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SD4");
                    AgregarButacaEnLista(vg,"D4");
                }
                break;

            case "DD5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SD5");
                    AgregarButacaEnLista(vg,"D5");
                }
                break;

            case "DE1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SE1");
                    AgregarButacaEnLista(vg,"E1");
                }
                break;

            case "DE2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SE2");
                    AgregarButacaEnLista(vg,"E2");
                }
                break;

            case "DE3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SE3");
                    AgregarButacaEnLista(vg,"E3");
                }
                break;

            case "DE4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SE4");
                    AgregarButacaEnLista(vg,"E4");
                }
                break;

            case "DE5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SE5");
                    AgregarButacaEnLista(vg,"E5");
                }
                break;

            case "DF1":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SF1");
                    AgregarButacaEnLista(vg,"F1");
                }
                break;

            case "DF2":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SF2");
                    AgregarButacaEnLista(vg,"F2");
                }
                break;

            case "DF3":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SF3");
                    AgregarButacaEnLista(vg,"F3");
                }
                break;

            case "DF4":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SF4");
                    AgregarButacaEnLista(vg,"F4");
                }
                break;

            case "DF5":
                if(PermitirIncremento()){
                    midib.setImageResource(R.drawable.butaca_seleccionada);
                    midib.setTag("SF5");
                    AgregarButacaEnLista(vg,"F5");
                }
                break;


            case "SA1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"A1");
                    midib.setTag("DA1");
                }
                break;

            case "SA2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"A2");
                    midib.setTag("DA2");
                }
                break;

            case "SA3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"A3");
                    midib.setTag("DA3");
                }
                break;

            case "SA4":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"A4");
                    midib.setTag("DA4");
                }
                break;

            case "SA5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"A5");
                    midib.setTag("DA5");
                }
                break;

            case "SB1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"B1");
                    midib.setTag("DB1");
                }
                break;

            case "SB2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"B2");
                    midib.setTag("DB2");
                }
                break;

            case "SB3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"B3");
                    midib.setTag("DB3");
                }
                break;

            case "SB4":
                if(PermitirDecremento()){}
                midib.setImageResource(R.drawable.butaca_mediana);
                EliminarButacaEnLista(vg,"B4");
                midib.setTag("DB4");
                break;

            case "SB5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"B5");
                    midib.setTag("DB5");
                }
                break;

            case "SC1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"C1");
                    midib.setTag("DC1");
                }
                break;

            case "SC2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"C2");
                    midib.setTag("DC2");
                }
                break;

            case "SC3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"C3");
                    midib.setTag("DC3");
                }
                break;

            case "SC4":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"C4");
                    midib.setTag("DC4");
                }
                break;

            case "SC5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"C5");
                    midib.setTag("DC5");
                }
                break;

            case "SD1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"D1");
                    midib.setTag("DD1");
                }
                break;

            case "SD2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"D2");
                    midib.setTag("DD2");
                }
                break;

            case "SD3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"D3");
                    midib.setTag("DD3");
                }
                break;

            case "SD4":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"D4");
                    midib.setTag("DD4");
                }
                break;

            case "SD5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"D5");
                    midib.setTag("DD5");
                }
                break;

            case "SE1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"E1");
                    midib.setTag("DE1");
                }
                break;

            case "SE2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"E2");
                    midib.setTag("DE2");
                }
                break;

            case "SE3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"E3");
                    midib.setTag("DE3");
                }
                break;

            case "SE4":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"E4");
                    midib.setTag("DE4");
                }
                break;

            case "SE5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"E5");
                    midib.setTag("DE5");
                }
                break;

            case "SF1":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"F1");
                    midib.setTag("DF1");
                }
                break;

            case "SF2":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"F2");
                    midib.setTag("DF2");
                }
                break;

            case "SF3":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"F3");
                    midib.setTag("DF3");
                }
                break;

            case "SF4":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"F4");
                    midib.setTag("DF4");
                }
                break;

            case "SF5":
                if(PermitirDecremento()){
                    midib.setImageResource(R.drawable.butaca_mediana);
                    EliminarButacaEnLista(vg,"F5");
                    midib.setTag("DF5");
                }
                break;
        }
        TransmitirButacasAEditText(vg);
    }

    private void TransmitirButacasAEditText(VariablesGlobales vg){
        int tam = vg.getListaAsientos().size();
        TextView input = vg.getTextHelper();
        String dato = "";
        for(int i = 0; i < tam; i++){
            if(tam == 1){
                dato += vg.getListaAsientos().get(i);
                input.setText(dato);
            } else {
                dato += vg.getListaAsientos().get(i)+",";
                input.setText(dato);
            }
        }
    }

    private void Mensaje(String msg){ Toast.makeText(getActivity(), msg,Toast.LENGTH_SHORT).show();};

    private void AgregarButacaEnLista(VariablesGlobales vg, String butaca){
        int tam = vg.getListaAsientos().size();
        if(tam < 10){
            vg.getListaAsientos().add(butaca);
        } else {
            Mensaje("Limite de butacas seleccionadas alcanzado.");
        }
    }

    private void EliminarButacaEnLista(VariablesGlobales vg, String butaca){
        vg.getListaAsientos().remove(butaca);
        TextView input = vg.getTextHelper();
        input.setText("");
    }


    private void ConsultarButacas(){
        VariablesGlobales vg = VariablesGlobales.getInstance();
        String url = "http://192.168.150.1/Android/v1/consultarButacas.php?id_pelicula="+vg.getIdPelicula()+
                "&dia="+vg.getDiaFuncion()+"&hora="+vg.getHoraFuncion();
        mensajeAccion = "ListarFunciones";
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        requestQueue = (RequestQueue) Volley.newRequestQueue(getActivity());

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
    }

    private void ObtenerButacasOcupadasPorFuncion(VariablesGlobales vg){
        if(vg.getListaBitacora() != null) {
            TableLayout tabla = vg.getTablaButacas();
            TableRow fila;
            ImageView imagen;
            for (ObjetoBitacora oB : vg.getListaBitacora()) { // recorremos cada uno de los registros de bitacora.
                for (int i = 0; i < 6; i++) {
                    fila = (TableRow) tabla.getChildAt(i);
                    for (int j = 0; j < 5; j++) {
                        imagen = (ImageView) fila.getChildAt(j); //obtenemos la imagen de cada elem de la fila
                        if (imagen.getTag().toString().equals("D"+ oB.getAsientoEnUso())) {
                            imagen.setImageResource(R.drawable.butaca_ocupada);
                            imagen.setClickable(false);
                        }
                    }
                }
            }
        }
    }

    private boolean PermitirIncremento(){
        VariablesGlobales vg = VariablesGlobales.getInstance();
        if(contador < vg.getButacasSeleccionadas()){
            contador++;
            return true;
        } else {return false;}
    }

    private boolean PermitirDecremento(){
        VariablesGlobales vg = VariablesGlobales.getInstance();
        if(contador == 0 || contador < 0){return false;}
        else{
            contador--;
            return true;
        }
    }



    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONArray response) {

        try {
            Time hora;
            ArrayList<ObjetoBitacora> asientos = new ArrayList<ObjetoBitacora>();

            for(int i = 0; i < response.length(); i++) {

                String temp = response.get(i).toString();
                JSONObject jsonObject = new JSONObject(temp);

                ObjetoBitacora obj = new ObjetoBitacora();
                obj.setAsientoEnUso(jsonObject.getString("Asiento"));

                asientos.add(obj);
            }
            VariablesGlobales vg = VariablesGlobales.getInstance();
            vg.setListaBitacora(asientos);

            ObtenerButacasOcupadasPorFuncion(vg);

        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
