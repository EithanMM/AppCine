package com.example.proyectocine.Activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectocine.Controllers.claseBase;
import com.example.proyectocine.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetailsActivity extends claseBase {

    TextView txt_id,txt_monto,txt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        txt_id= findViewById(R.id.txt_id);
        txt_monto= findViewById(R.id.txt_monto);
        txt_status= findViewById(R.id.txt_status);


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
}
