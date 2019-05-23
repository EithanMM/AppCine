package com.example.proyectocine.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class PaymentDetailsActivity extends claseBase implements Response.Listener<JSONArray>, Response.ErrorListener {

    TextView txt_id,txt_monto,txt_status;

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
        String url = "http://192.168.0.10/Android/v1/registroBitacora.php?"+ConstruirUrl(vg);

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(jsonArrayRequest);
    }

    private String ConstruirUrl(VariablesGlobales vg){
        String res = "id_funcion="+vg.getIdPelicula()+
                     "&EstadoFuncion=A"+
                     Asientos(vg)+
                     "&Cedula="+vg.getCedulaUsuario()+
                     "&Nombre="+vg.getNombreUsuario()+
                     "&Apellido="+vg.getApellidosUsuario().replace(" ","%20")+
                     "&NumeroConsec="+GenerarNumeroConsecutivo();

        return res;
    }


    private String Asientos(VariablesGlobales vg){
        String res = "";
        for(int i = 0; i < vg.getListaAsientos().size(); i++){
            res += "&Asiento[]="+vg.getListaAsientos().get(i).toString();
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
        MensajeOK("Compra ingresada a Bitacora exitosamente.");
    }

    @Override
    public void onResponse(JSONArray response) {
    }
}
