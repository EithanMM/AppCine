package com.example.proyectocine.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class PaymentDetailsActivity extends claseBase implements Response.Listener<JSONObject>, Response.ErrorListener {

    TextView txt_id,txt_monto,txt_status;
    private JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        getSupportActionBar().setTitle("Detalles de compra");
        txt_id= findViewById(R.id.txt_id);
        txt_monto= findViewById(R.id.txt_monto);
        txt_status= findViewById(R.id.txt_status);


        InsertarEnBitacora();

        Intent intent  = getIntent();

        try{

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("details"));
            verDetalles(jsonObject.getJSONObject("response"),intent.getStringExtra("monto"));

        }catch (Exception e){

        }
    }

    private void verDetalles(JSONObject response, String monto) throws JSONException {
        txt_id.setText(response.getString("id"));
        txt_status.setText(response.getString("status"));
        txt_monto.setText(response.getString(String.format("$%s",monto)));

    }

    private void InsertarEnBitacora(){

        VariablesGlobales vg = VariablesGlobales.getInstance();
        String url = "http://192.168.0.107/Android/v1/registroBitacora.php?"+ConstruirUrl(vg);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        requestQueue.add(jsonObjectRequest);
    }

    private String ConstruirUrl(VariablesGlobales vg){
        String res = "id_funcion="+vg.getIdPelicula()+
                "&EstadoFuncion=A"+
                Asientos(vg)+
                "&Cedula="+vg.getCedulaUsuario()+
                "&Nombre="+vg.getNombreUsuario().replace(" ","%20")+
                "&Apellido="+vg.getApellidosUsuario().replace(" ","%20")+
                "&NumeroConsec="+GenerarNumeroConsecutivo();

        return res;
    }


    private String Asientos(VariablesGlobales vg){
        String res = "";
        String[]array;
        String reciever = vg.getAsientosSeleccionados();

        if(reciever.length() == 2){ //D5
            array = new String[1];
            array[0] = reciever;
        } else {
            array = reciever.split(",");
        }


        for(int i = 0; i < array.length; i++){
            res += "&Asiento[]="+array[i].toString();
        }
        return res;
    }


    private String GenerarNumeroConsecutivo(){
        Random rand = new Random(System.currentTimeMillis());
        boolean salida = false;
        int randomVal = 0;
        do{
            randomVal = rand.nextInt();
            if(randomVal > 0) {
                if(String.valueOf(randomVal).length() > 0){
                    if(String.valueOf(randomVal).length() <= 10){
                        salida = true;
                    }
                }
            }

        }while(!salida);
        return String.valueOf(randomVal);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String res = String.valueOf(error.networkResponse.data);
        MensajeOK("No inserta en bitacora: "+error.toString());
    }


    @Override
    public void onResponse(JSONObject response) {
        MensajeOK("Compra ingresada a Bitacora exitosamente.");
    }
}
