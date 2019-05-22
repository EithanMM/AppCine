package com.example.proyectocine.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocine.Controllers.VariablesGlobales;
import com.example.proyectocine.R;
import com.example.proyectocine.Controllers.claseBase;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class ActivityPagoTiquete extends claseBase {

    TextView cedula;
    TextView nombre;
    TextView apellido;
    TextView correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_tiquete);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(intent);


        OnclickDelButton(R.id.btn_realiar_pago);
        OnclickDelButton(R.id.btn_vovler_seleccion_butacas);

        cedula = (TextView) findViewById(R.id.cedula_input);
        nombre = (TextView) findViewById(R.id.nombre_input);
        apellido = (TextView) findViewById(R.id.apellido_input);
        correo = (TextView) findViewById(R.id.correo_input);


        InicializamosTextViewsParaCorreo(cedula,nombre,apellido,correo);
    }
    //metodos para pago con paypal


    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PaymentActivity.class));
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (paymentConfirmation != null) {
                    try {
                        VariablesGlobales vg = VariablesGlobales.getInstance();

                        vg.setCedulaUsuario(cedula.getText().toString());
                        vg.setNombreUsuario(nombre.getText().toString());
                        vg.setApellidosUsuario(apellido.getText().toString());

                        String payment_details = paymentConfirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetailsActivity.class)
                                .putExtra("details", payment_details)
                                .putExtra("monto", dinero));
                    } catch (Exception e) {

                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
        }
    }
}
