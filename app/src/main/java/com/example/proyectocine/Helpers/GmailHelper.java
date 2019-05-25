package com.example.proyectocine.Helpers;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import com.example.proyectocine.Controllers.VariablesGlobales;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static android.content.ContentValues.TAG;

public class GmailHelper {

    Session session = null;
    Properties prop = null;
    private String Destino, Titulo, Mensaje;
    private static String cryptoPass = "sup3rS3xy";
    public boolean email_enviado;

    public GmailHelper(){
        this.email_enviado = false;
    }


    public boolean EnviarEmail(EditText cedula, EditText nombre, EditText apellido, EditText correo, VariablesGlobales vg){
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");



        Destino = correo.getText().toString();
        Titulo = "Pago de Tiquete - CineTI";
        Mensaje = ContenidoMensaje(cedula.getText().toString(), nombre.getText().toString(), apellido.getText().toString(), vg);


        session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cineTIMoviles@gmail.com",ObtenerPermisos());
            }
        });

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
        email_enviado = true;
        return email_enviado;
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("cineTIMoviles@gmail.com")); /*Correo de la "empresa". */
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(Destino)); /*Almacena el correo del destinatario.*/
                message.setSubject(Titulo); /*Almacena el Titulo del mensaje.*/
                message.setContent(Mensaje, "text/html; charset=utf-8"); /*Determina la codificacion del mensaje.*/

                Transport.send(message); /*Sen envia el mensaje*/

            } catch(MessagingException e){
                e.printStackTrace();;
            } catch(Exception e){
                e.printStackTrace();;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            email_enviado = false;
        }
    }

    private String ContenidoMensaje(String cedula, String nombre, String apellido, VariablesGlobales vg){
            return "<html>"+
                    "<head><h1>CineTI</h1></head>"+
                    "<body><p></p>"+
                    "<h2>Información Suministrada</h2>"+
                    "<p></p>"+
                    "<h5>Cédula</h5>"+
                    "<p>"+cedula+"</p>"+
                    "<hr>"+
                    "<h5>Nombre Completo</h5>"+
                    "<p>"+nombre+" "+apellido+"</p>"+
                    "<hr>"+
                    "<h5>Película</h5>"+
                    "<p>"+vg.getNombrePelicula()+"</p>"+
                    "<hr>"+
                    "<h5>Función</h5>"+
                    "<p>"+vg.getDiaFuncion()+" "+FormatoHora(vg.getHoraFuncion().toString())+"</p>"+
                    "<hr>"+
                    "<h5>Asientos</h5>"+
                    "<p>"+ObtenerAsientosSeleccionados(vg)+"</p>"+
                    "<hr>"+
                    "<h5>Precio</h5>"+
                    "<p>"+CalcularPrecio(vg)+"</p>"+
                    "<hr>"+
                    "<p></p>"+
                    "<h4><i>Gracias por preferirnos</i></h4>"+
                    "</body></html>";
    }


    private String ObtenerAsientosSeleccionados(VariablesGlobales vg){
        ArrayList<String> butacas = vg.getListaAsientos();
        String resp = "";
        for(String dato : butacas){
            if(butacas.size() == 1){
                resp+=dato;
            } else {
                resp+=dato+", ";
            }
        }
        return resp;
    }

    private String CalcularPrecio(VariablesGlobales vg){
        return String.format("₡ %,.1f", (float)vg.getPrecioTotal());
    }

    private String Encriptar(String value){
        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] clearText = value.getBytes("UTF8");
            // Cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            Log.d(TAG, "Encrypted: " + value + " -> " + encrypedValue);
            return encrypedValue;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return value;
    }

    public String Desencriptar( String value) {
        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

            String decrypedValue = new String(decrypedValueBytes);
            Log.d(TAG, "Decrypted: " + value + " -> " + decrypedValue);
            return decrypedValue;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return value;
    }

    private String ObtenerPermisos(){
        String res = "";
        res = Desencriptar("Ap0KhEYA9jZcy/DO6wan0w==");
        return res;
    }

    private String FormatoHora(String hora){
        Calendar calendar = GenerarCalendario(hora);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

        return sdf.format(d);
    }

    private Calendar GenerarCalendario(String hora){
        int H = Integer.parseInt(hora.substring(0,2));
        int M = Integer.parseInt(hora.substring(3,5));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,H);
        cal.set(Calendar.MINUTE,M);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal;
    }

}
