package com.example.proyectocine.Controllers;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyectocine.Activities.ActivityPagoTiquete;
import com.example.proyectocine.Activities.ActivitySeleccionButacas;
import com.example.proyectocine.Activities.MainActivity;
import com.example.proyectocine.Data.DBAdapterSQL;
import com.example.proyectocine.Helpers.GmailHelper;
import com.example.proyectocine.Helpers.ObjetoBitacora;
import com.example.proyectocine.Helpers.ObjetoFuncion;
import com.example.proyectocine.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import net.glxn.qrgen.android.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class claseBase extends AppCompatActivity {

    private DBAdapterSQL db;
    public GmailHelper gh = new GmailHelper();
    private VariablesGlobales vg = VariablesGlobales.getInstance();


    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText correo;

    public ArrayList<ObjetoFuncion> funciones = new ArrayList<ObjetoFuncion>();
    public ArrayList<ObjetoBitacora> bitacora = new ArrayList<ObjetoBitacora>();

    private TextView cant_boletos_edad3, resta_boletos_edad3, suma_boletos_edad3,
            cant_boletos_adulto, resta_boletos_adulto, suma_boletos_adulto, vista_total;


    //views para detalles de pago paypal
    public static final String PAYPAL_CLIENT_ID = "AXPLEB3uBxOGOCVsumxfmEofmN6m7CocpInV827k3MeeOWFO47YgBSX4ZMl2WtMa0K0GpJcfOuqcRUIX";
    TextView txt_id, txt_monto, txt_status;
    public String dinero;
    public int result;
    public static final int PAYPAL_REQUEST_CODE = 7171;
    public PayPalConfiguration configuration = new PayPalConfiguration().environment(
            PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(PAYPAL_CLIENT_ID);

    private Button btnEscogerCampos;
    private int contador_boletos_edad3 = 0;
    private int contador_boletos_adulto = 0;
    private final int PRECIO_BOLETO_ADULTO = 3200;
    private final int PRECIO_BOLETO_TERCERA_EDAD = 2500;
    private int total_boletos = 0;

    public RequestQueue requestQueue;
    public JsonArrayRequest jsonArrayRequest;
    public String mensajeAccion = "";

    public static final int MY_DEFAULT_TIMEOUT = 15000;

    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    private String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|" +
            "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\" +
            "x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|" +
            "\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]" +
            "*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7" +
            "f])+)\\])";

    private String NAME_REGEX = "^\\s*[a-zA-Z,\\sÁÉÍÓÚáéíóú]+\\s*$";

    public String archivo;


    public void MensajeOK(String msg) {
        View v1 = getWindow().getDecorView().getRootView();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        ;
    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /*-----------------------------Metodos publicos de manejo de BD-------------------------------*/
    public void CrearYAbrirBaseDeDatos() {
        if (db == null) {
            db = new DBAdapterSQL(this);
            db.open();
        }
    }

    //String drawName = "algo";
    // int resID = getResources().getIdentifier(drawName, "drawable", getPackageName());

    public void DesplegarTodosLosRegistros() {
/*            if (db != null) {
                MensajeOK(db.ObtenerTodosLosRegistros());
            } else {
                MensajeOK("BD nula");
            }*/
    }


    public void DropearYCrearBD() {
        MensajeOK(db.DropearYCrearBD());
    }


    public ArrayList<ObjetoBitacora> ObtenerRegistrosDeBitacoraPorFuncion() {
        return db.ObtenerButacasOcupadas(Integer.parseInt(vg.getIdPelicula()), vg.getDiaFuncion(), vg.getHoraFuncion());
    }

    public void InsertarRegistroEnBitacora(Intent intento) {
//            ArrayList<ObjetoFuncion> funciones = ObtenerListaFuncion();
//            String msg = "";
//            for(ObjetoFuncion of : funciones){
//                if(of.getNombrePelicula().equals(vg.getNombrePelicula())&&
//                   of.getNombreSala().equals(vg.getNombreSala())&&
//                   of.getDiaFuncion().equals(vg.getDiaFuncion())&&
//                   of.getHoraInicio().equals(vg.getHoraFuncion())){
//
//                    msg = db.InsertarRegistroEnBitacora(of, vg, nombre.getText().toString(), apellido.getText().toString(), cedula.getText().toString());
//                }
//            }
//            if(msg.equals("good")){
//                EnviarEmail(intento);
//            } else {
//                MensajeOK("Ocurrio un error a la hora de registrar compra...");
//            }
    }


    /*--------------------------------------------------------------------------------------------*/
    /*---------------------------------Otros Metodos----------------------------------------------*/

    public int DeterminarImagen(String id_pelicula) {
        int res = 0;
        switch (Integer.parseInt(id_pelicula)) {
            case 1:
                //Coco
                res = R.drawable.coco;
                break;
            case 2:
                //Cementerio
                res = R.drawable.cementerio_maldito;
                break;
            case 3:
                //Capitana Marvel
                res = R.drawable.capitana_marvel;
                break;
            case 4:
                //Avenger
                res = R.drawable.infinity_war;
                break;
            case 5:
                //La Pasion
                res = R.drawable.pasion_cristo;
                break;
            case 6:
                //El Aro
                res = R.drawable.the_ring;
                break;
            case 7:
                //Doctor Strange
                res = R.drawable.doctor_strange;
                break;
            case 8:
                //Bohemian
                res = R.drawable.bohemian;
                break;
            case 9:
                //La Forma del Agua
                res = R.drawable.forma_agua;
                break;
            case 10:
                //Dragon Ball
                res = R.drawable.dragon_ball;
                break;
        }
        return res;
    }

    public void OnclickDelButton(int ref) {
        View view = findViewById(ref);
        Button miButton = (Button) view;
        miButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento;
                ArrayList<String> asientos = vg.getListaAsientos();
                switch (v.getId()) {

                    case R.id.btn_continuar:
                        if (asientos.size() == 0) {
                            Mensaje("Debe seleccionar almenos una butaca.");
                            break;
                        } else {
                            TextView butacas_seleccionadas = (TextView) findViewById(R.id.input_asientos);
                            vg.setAsientosSeleccionados(butacas_seleccionadas.getText().toString());
                            intento = new Intent(getApplicationContext(), ActivityPagoTiquete.class);
                            startActivity(intento);
                            break;
                        }

                    case R.id.btn_volver:
                        if(vg.getButacasSeleccionadas() > 0){
                            vg.setButacasSeleccionadas(0);
                            vg.setAsientosSeleccionados(null);
                            if(vg.getListaAsientos().size() > 0){
                                vg.getListaAsientos().clear();
                            }
                        }
                        intento = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intento);
                        break;

                    case R.id.btn_realiar_pago:
                        //intento = new Intent(getApplicationContext(), MainActivity.class);
                        //InsertarRegistroEnBitacora(intento);


                        TextInputLayout Mi_edittext = findViewById(R.id.cedula_input);
                        VariablesGlobales.getInstance().setCedula(Mi_edittext.getEditText().getText().toString());

                        VariablesGlobales vg = VariablesGlobales.getInstance();

                        String texto = "Cedula:" + vg.getCedula() + "\n" +
                                "Pelicula:" + vg.getNombrePelicula() + ", sala " + vg.getNombreSala() + ", a las " + vg.getHoraFuncion() + "\n" +
                                "Asientos:" + vg.getListaAsientos().toString() + "\n" +
                                "Precio:" + vg.getPrecioTotal() + "\n";

                        Bitmap bitmap = QRCode.from(texto).bitmap();
                        // Suponiendo que tienes un ImageView con el id ivCodigoGenerado

                        // Crear stream del código QR
                        ByteArrayOutputStream byteArrayOutputStream = QRCode.from(texto).stream();
                        // E intentar guardar
                        FileOutputStream fos;
                        try {
                            // Guardar en el almacenamiento externo con el nombre de "codigo.png"


                            pedirPermiso();


                            archivo = Environment.getExternalStorageDirectory() + "/codigo.png";
                            fos = new FileOutputStream(archivo);
                            byteArrayOutputStream.writeTo(fos);
                            Toast.makeText(claseBase.this, "Código guardado", Toast.LENGTH_SHORT).show();


                            //pago con paypal
                            vg.setBitmap(bitmap);
                            pagar(archivo);


                        } catch (FileNotFoundException e) {
                            // Nota: aquí indica al usuario que algo salió mal
                            e.printStackTrace();
                        } catch (IOException e) {
                            // Nota: aquí indica al usuario que algo salió mal
                            e.printStackTrace();
                        }

                        break;
                    case R.id.btn_vovler_seleccion_butacas:
                        intento = new Intent(getApplicationContext(), ActivitySeleccionButacas.class);
                        startActivity(intento);
                        break;
                    default:
                        break;
                }
            }
        });
    }// fin de OnclickDelButton


    void pedirPermiso() {

        int estadoDePermiso = ContextCompat.checkSelfPermission(claseBase.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            Mensaje("Permiso concebido");
        } else {
            // Si no, entonces pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(claseBase.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        int estadoDePermiso2 = ContextCompat.checkSelfPermission(claseBase.this, Manifest.permission.CAMERA);

        if (estadoDePermiso2 == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            Mensaje("Permiso concebido");
        } else {
            // Si no, entonces pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(claseBase.this,
                    new String[]{Manifest.permission.CAMERA}, 1);
        }

    }

    public void InicializamosTextViewsParaCorreo(EditText cedula, EditText nombre, EditText apellido, EditText correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public void iniciarParametrosTextViewParaBoletos(TextView cant_boletos_edad3, TextView resta_boletos_edad3,
                                                     TextView suma_boletos_edad3, TextView cant_boletos_adulto,
                                                     TextView resta_boletos_adulto, TextView suma_boletos_adulto,
                                                     TextView vista_total, Button btnEscogerCampos) {
        this.cant_boletos_edad3 = cant_boletos_edad3;
        this.resta_boletos_edad3 = resta_boletos_edad3;
        this.suma_boletos_edad3 = suma_boletos_edad3;
        this.cant_boletos_adulto = cant_boletos_adulto;
        this.resta_boletos_adulto = resta_boletos_adulto;
        this.suma_boletos_adulto = suma_boletos_adulto;
        this.vista_total = vista_total;
        this.btnEscogerCampos = btnEscogerCampos;
    }

    public boolean emailValido() {
        String message = correo.getText().toString().trim();
        if (message.isEmpty()) {
            correo.setError("Campo correo está vacío");
            return false;
        } else {
            correo.setError(null);
            return true;
        }
    }

    public boolean cedulaValido() {
        String message = cedula.getText().toString().trim();
        if (message.isEmpty()) {
            cedula.setError("Campo cedula está vacío");
            return false;
        } else {
            cedula.setError(null);
            return true;
        }
    }

    public boolean nombreValido() {
        String message = nombre.getText().toString().trim();
        if (message.isEmpty()) {
            nombre.setError("Campo nombre está vacío");
            return false;
        } else {
            nombre.setError(null);
            return true;
        }
    }

    public boolean apellidoValido() {
        String message = apellido.getText().toString().trim();
        if (message.isEmpty()) {
            apellido.setError("Campo apellido está vacío");
            return false;
        } else {
            apellido.setError(null);
            return true;
        }
    }

    private void EnviarEmail(Intent intento, String archivo) {

        if (!emailValido() | !nombreValido() | !apellidoValido() | !cedulaValido()) {
            return;
        } else {


            LimpiarListaAsientos();

            startActivityForResult(intento, PAYPAL_REQUEST_CODE);
            // startActivity(intento);

        }


    }

    public boolean Validaciones() {
        boolean respuesta = false;
        VariablesGlobales vg = VariablesGlobales.getInstance();
        Pattern name_pattern = Pattern.compile(NAME_REGEX);
        Pattern first_last_name_pattern = Pattern.compile(NAME_REGEX);
        Pattern email_pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher1 = name_pattern.matcher(vg.getNombreUsuario());
        Matcher matcher2 = first_last_name_pattern.matcher(vg.getApellidosUsuario());
        Matcher matcher3 = email_pattern.matcher(vg.getCorreo());


        if (vg.getCedulaUsuario().toString().equals("")) {
            MensajeOK("Debe digitar una cédula por favor.");
            return respuesta;

        } else if (vg.getNombreUsuario().toString().equals("")) {
            MensajeOK("Debe digitar un nombre por favor.");
            return respuesta;
        } else if (!matcher1.find()) {
            MensajeOK("El nombre ingresado posee caracteres no validos.");
            return respuesta;
        } else if (vg.getApellidosUsuario().toString().equals("")) {
            MensajeOK("Debe ingresar un apellido por favor.");
            return respuesta;
        } else if (!matcher2.find()) {
            MensajeOK("El o los apellidos ingresados poseen caracteres no validos.");
            return respuesta;
        } else if (vg.getCorreo().toString().equals("")) {
            MensajeOK("Debe digitar un correo electrónico por favor.");
            return respuesta;
        } else if (!matcher3.find()) {
            MensajeOK("El correo ingresado no es válido, favor ingresar un correo válido.");
            return respuesta;
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public void LimpiarListaAsientos() {
        vg.getListaAsientos().clear();
    }

    public void LimpiarListaBitacora() {
        vg.setListaBitacora(null);
    }


    public String FormatoHora(String hora) {
        Calendar calendar = GenerarCalendario(hora);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

        return sdf.format(d);
    }

    private Calendar GenerarCalendario(String hora) {
        int H = Integer.parseInt(hora.substring(0, 2));
        int M = Integer.parseInt(hora.substring(3, 5));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, H);
        cal.set(Calendar.MINUTE, M);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public void agregarEventos() {

        resta_boletos_edad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contador_boletos_edad3 > 0) {

                    contador_boletos_edad3--;
                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;

                    cant_boletos_edad3.setText("" + contador_boletos_edad3);
                    result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                    vg.setButacasSeleccionadas(total_boletos);
                }
            }
        });

        suma_boletos_edad3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (contador_boletos_edad3 == 0 && total_boletos < 10) {
                    contador_boletos_edad3++;
                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;

                    cant_boletos_edad3.setText("" + contador_boletos_edad3);
                    result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                    vg.setButacasSeleccionadas(total_boletos);

                } else if (contador_boletos_edad3 + contador_boletos_adulto < 10) {
                    contador_boletos_edad3++;
                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;
                    cant_boletos_edad3.setText("" + contador_boletos_edad3);
                    vg.setButacasSeleccionadas(total_boletos);

                    result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                } else {
                    Mensaje("Limite de butacas alcanzado.");
                }
            }
        });


        resta_boletos_adulto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (contador_boletos_adulto > 0) {
                    contador_boletos_adulto--;
                    cant_boletos_adulto.setText("" + contador_boletos_adulto);
                    result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);

                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;
                    cant_boletos_adulto.setText("" + contador_boletos_adulto);

                    vg.setButacasSeleccionadas(total_boletos);
                    int result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                }
            }
        });


        suma_boletos_adulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contador_boletos_adulto == 0 && total_boletos < 10) {
                    contador_boletos_adulto++;
                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;

                    cant_boletos_adulto.setText("" + contador_boletos_adulto);
                    result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    dinero = Integer.toString(result);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                    vg.setButacasSeleccionadas(total_boletos);

                } else if (contador_boletos_edad3 + contador_boletos_adulto < 10) {
                    contador_boletos_adulto++;
                    total_boletos = contador_boletos_adulto + contador_boletos_edad3;
                    cant_boletos_adulto.setText("" + contador_boletos_adulto);
                    vg.setButacasSeleccionadas(total_boletos);

                    int result = (PRECIO_BOLETO_TERCERA_EDAD * contador_boletos_edad3) + (PRECIO_BOLETO_ADULTO * contador_boletos_adulto);
                    vg.setPrecioTotal(result);
                    vista_total.setText("₡ " + String.valueOf(result));
                } else {
                    Mensaje("Limite de butacas alcanzado.");
                }
            }

        });


        btnEscogerCampos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                int cant_edad3 = Integer.parseInt((cant_boletos_edad3.getText().toString()));
                int cant_adulto = Integer.parseInt((cant_boletos_adulto.getText().toString()));
                if (cant_edad3 != 0 || cant_adulto != 0) {

                    int total_boletos = Integer.parseInt(cant_boletos_edad3.getText().toString())
                            + Integer.parseInt(cant_boletos_adulto.getText().toString());

                    // escriba lo que desea hacer
                    Intent intento = new Intent(getApplicationContext(), ActivitySeleccionButacas.class);
                    startActivity(intento);

                } else {
                    Mensaje("Debe seleccionar cuantos boletos deseea comprar.");
                }
            }

        });

    }


    /*--------------------------------------------------------------------------------------------*/

    //metodos para pago con paypal
    public void pagar(String archivo) {
        PayPalPayment payPalPayment = new PayPalPayment(
                new BigDecimal(String.valueOf(Integer.toString(vg.getPrecioTotal()))),
                "USD", "CineTI", PayPalPayment.PAYMENT_INTENT_SALE

        );

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);

        EnviarEmail(intent, archivo);

        //startActivityForResult(intent, PAYPAL_REQUEST_CODE);

    }

}
