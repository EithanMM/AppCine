package com.example.proyectocine;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyectocine.EntidadesBD;

import java.util.ArrayList;

public class DBAdapterSQL {

    static final String PK_PELICULA ="IdPelicula";
    static final String PK_SALA ="IdSala";
    static final String PK_FUNCION ="idFuncion";
    static final String PK_BITACORA ="IdBitacora";

    static final  String DATABASE_NAME = "CineDB.db";

    static final String TABLE_BITACORA = "Bitacora";
    static final String TABLE_FUNCION = "Funcion";
    static final String TABLE_SALA = "Sala";
    static final String TABLE_PELICULA = "Pelicula";
    static final String TRIGGER_1 = "agregar_fecha_bitacora";
    static final String VIEW_1 = "vista_bitacora";
    static final String VIEW_2 = "vista_bitacora2";
    static final String VIEW_3 = "vista_funcion";

    static String mensaje ="Mensaje inicial";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    EntidadesBD eDB = new EntidadesBD();

    public  String getDatabaseName() {
        return DATABASE_NAME;
    }


    public DBAdapterSQL(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    // creamos subclase DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {// en este metodo creamos la base de datos si no existe previamente.
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    //---Abrimos la base datos---
    public DBAdapterSQL open() throws SQLException  {
        db = DBHelper.getWritableDatabase();
        //mensaje =DATABASE_NAME+" abierta para escritura";
        return this;
    }
    //---Cerramos la base de datos ---
    public void close() {
        DBHelper.close();
        //mensaje ="La BD se ha cerrado";
    }

    public void DropearTablas(){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SALA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PELICULA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FUNCION);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BITACORA);

        db.execSQL("DROP TRIGGER IF EXISTS "+TRIGGER_1);

        db.execSQL("DROP VIEW IF EXISTS "+VIEW_1);
        db.execSQL("DROP VIEW IF EXISTS "+VIEW_2);
        db.execSQL("DROP VIEW IF EXISTS "+VIEW_3);
    }

    public void CrearTablas(){
        EntidadesBD eDB = new EntidadesBD();
        db.execSQL("CREATE TABLE "+TABLE_SALA+"("+ eDB.EntidadSala()+ ");");

        db.execSQL("CREATE TABLE "+TABLE_PELICULA+"("+eDB.EntidadPelicula()+");");

        db.execSQL("CREATE TABLE "+TABLE_FUNCION+"("+eDB.EnditadFuncion()+");");

        db.execSQL("CREATE TABLE "+TABLE_BITACORA+"("+eDB.EntidadBitacora()+");");

        db.execSQL(eDB.Trigger_1());
        db.execSQL(eDB.Vista_Bitacora1());
        db.execSQL(eDB.Vista_Bitacora2());
        db.execSQL(eDB.Vista_Funcion1());

        RealizarInsercionesBasicas();
    }

    public void RealizarInsercionesBasicas() {
        try {
            db.execSQL("INSERT INTO Pelicula VALUES(1,\"Coco\",\"Un coco que se perdio\",\"E\",\"Animación\");");
            db.execSQL("INSERT INTO Pelicula VALUES(2,\"Cementerio Maldito\",\"Jovenes que entran a un cementerio, descubren un terrible secreto\",\"M\",\"Horror\");");
            db.execSQL("INSERT INTO Pelicula VALUES(3,\"Capitana Marvel\",\"Una guerrera extraterrestre de la civilización Kree se encuentra atrapada en medio de una batalla. Con la ayuda de Nick Fury trata de descubrir los secretos de su pasado mientras aprovecha sus poderes para terminar la guerra.\",\"E\",\"Acción\");");
            db.execSQL("INSERT INTO Pelicula VALUES(4,\"Avengers: Infinity War\",\"Los superhéroes se alían para vencer al poderoso Thanos, el peor enemigo al que se han enfrentado. Si Thanos logra reunir las seis gemas del infinito: poder, tiempo, alma, realidad, mente y espacio, nadie podrá detenerlo.\",\"E\",\"Acción\");");
            db.execSQL("INSERT INTO Pelicula VALUES(5,\"La Pasión de Cristo\",\"Condenado a morir crucificado, Jesús de Nazaret experimenta y soporta la agonía de sus últimas doce horas.\",\"E\",\"Drama\");");
            db.execSQL("INSERT INTO Pelicula VALUES(6,\"El Aro\",\"Una reportera debe resolver el misterio de una cinta que trae muerte a sus espectadores, antes de que sucumba a su poder.\",\"M\",\"Horror\");");
            db.execSQL("INSERT INTO Pelicula VALUES(7,\"Doctor Strange: hechicero supremo\",\"Después de sufrir un accidente, un brillante y arrogante cirujano busca rehabilitarse mediante técnicas alternativas. Sus intentos le llevan a descubrir que ha sido designado para encabezar la lucha contra una fuerza oscura y sobrenatural.\",\"E\",\"Acción\");");
            db.execSQL("INSERT INTO Pelicula VALUES(8,\"Bohemian Rhapsody\",\"Freddie Mercury desafía los estereotipos y se convierte en uno de los vocalistas más reconocidos de la música mundial. Después de dejar la banda Queen para seguir una carrera como solista, Mercury se reúne con la banda para dar una de las mejores actuaciones en la historia del rock 'n' roll.\",\"M\",\"Drama\");");
            db.execSQL("INSERT INTO Pelicula VALUES(9,\"La Forma del Agua\",\"Elisa es una joven muda que se enamora de un hombre anfibio que está recluido en un acuario en un laboratorio secreto, propiedad del Gobierno, en el que ella trabaja limpiando. Llevada por el amor, Elisa trama un plan para liberar al mutante.\",\"M\",\"Drama\");");
            db.execSQL("INSERT INTO Pelicula VALUES(10,\"Dragon Ball Z: La Batalla de los Dioses\",\"Los Peleadores Z deben contender con Bills, el Dios de la Destrucción. Pero se necesita un dios para luchar contra un dios, y ninguno de ellos lo es... ni siquiera los Saiyans.\",\"E\",\"Animación\");");

            db.execSQL("INSERT INTO Sala VALUES(1,\"Sala A\",30);");
            db.execSQL("INSERT INTO Sala VALUES(2,\"Sala B\",30);");
            db.execSQL("INSERT INTO Sala VALUES(3,\"Sala C\",30);");
            db.execSQL("INSERT INTO Sala VALUES(4,\"Sala D\",30);");

            db.execSQL("INSERT INTO Funcion VALUES(1,1,1,\"Lunes\",\"1:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(2,2,1,\"Lunes\", \"9:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(3,3,2,\"Martes\",\"4:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(4,4,2,\"Martes\",\"8:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(5,5,3,\"Miércoles\",\"10:00 AM\");");

            db.execSQL("INSERT INTO Funcion VALUES(6,6,3,\"Miércoles\",\"12:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(7,7,4,\"Jueves\",\"5:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(8,8,4,\"Jueves\",\"10:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(9,9,2,\"Viernes\",\"3:00 PM\");");
            db.execSQL("INSERT INTO Funcion VALUES(10,10,2,\"Viernes\",\"8:00 PM\");");

        } catch(SQLException e){
            e.printStackTrace();
            mensaje ="Error insertando";
        }
    }

    public String DropearYCrearBD(){
        if(db == null){
            open();
            DropearTablas();
            CrearTablas();
            RealizarInsercionesBasicas();
            return mensaje ="BD abierta, dropeada y creada de nuevo.";
        } else {
            DropearTablas();
            CrearTablas();
            RealizarInsercionesBasicas();
            return mensaje = "BD dropeada y creada de nuevo.";
        }
    }

    // Otra version de Insertar pero usando SQL
    boolean   insertDatoSQL(String  atributo01, int  atributo02)
    {
        String orden = "";
        try {
            db.execSQL(orden);
            mensaje ="Inserción OK";
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje ="Error insertando";
            return false;

        }
    }

    public String ObtenerButacasOcupadasPorFuncion(int id_funcion){
        Cursor c = db.rawQuery(eDB.ObtenerAsientosOcupadosSegunFuncion(id_funcion),null);
        String msg="";
        if(c.getCount() == 0){msg = "No se encontraron registros..";}
        else {
            while(c.moveToNext()){
                String cedula = c.getString(0);
                String pelicula = c.getString(1);
                String asiento = c.getString(2);
                msg+="ID: "+cedula+" Pelicula: "+pelicula+" Asiento: "+asiento+ "\n";
            }
        }
        return msg;
    }

    public String ObtenerButacasOcupadas(){
        return "";
    }

     /*FUNCIONA PRROS*/
    public String ObtenerTodosLosRegistros()
    {
        String query = "SELECT * FROM Sala";
        Cursor c = db.rawQuery(query,null);
        String msg="";
        if(c.getCount() == 0){msg = "Sin registros de Salas existentes..";}
        else {
            while (c.moveToNext()) {
                Integer id = c.getInt(0);
                String name = c.getString(1);
                String numSala = c.getString(2);
                msg = msg + "ID: " + id + " NombreSala: " + name + " Asientos:" + numSala + "\n";
            }
        }
        return msg;
    }

    public ArrayList<ObjetoFuncion> ObtenerTodasFunciones(){
        ArrayList<ObjetoFuncion> resultado =  new ArrayList<>();
        String query = eDB.ObtenerVistaFuncion();
        Cursor c = db.rawQuery(query,null);
        int count = c.getCount();
        if(c.getCount() != 0){
            while(c.moveToNext()){
                String id = c.getString(0);
                String idPelicula = c.getString(1);
                String idSala = c.getString(2);
                String NombrePelicula = c.getString(3);
                String NombreSala = c.getString(4);
                String Dia = c.getString(5);
                String Genero = c.getString(6);
                String Hora = c.getString(7);
                ObjetoFuncion objF = new ObjetoFuncion(id, idPelicula, idSala, NombrePelicula, NombreSala, Genero, Dia, Hora);
                resultado.add(objF);
            }
        } else {return null;}
        return resultado;
    }

}


