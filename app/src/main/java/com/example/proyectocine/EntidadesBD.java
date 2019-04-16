package com.example.proyectocine;

public class EntidadesBD {

    public EntidadesBD(){
    }

    public String EntidadSala() {
        return "IdSala INTEGER(5) PRIMARY KEY,\n" +
                "NombreSala VARCHAR(10) NOT NULL,\n" +
                "NumAsientos INTEGER(5) NOT NULL";
    }

    public String EntidadPelicula(){
        return "IdPelicula INTEGER(5) PRIMARY KEY,\n" +
                "NombrePelicula VARCHAR(30) NOT NULL,\n" +
                "Sinopsis VARCHAR(300) NOT NULL,\n" +
                "Publico VARCHAR(1) NOT NULL,\n" +
                "Tipo VARCHAR(20) NOT NULL";
    }

    public String EntitadFuncion(){
        return  "IdFuncion PRIMARY KEY,\n" +
                "IdPelicula INTEGER(5) NOT NULL,\n" +
                "IdSala INTEGER(5) NOT NULL,\n" +
                "DiaFuncion VARCHAR(20) NOT NULL,\n" +
                "HoraInicio TIME NOT NULL,\n" +
                "FOREIGN KEY (IdPelicula) REFERENCES Pelicula(IdPelicula),\n" +
                "FOREIGN KEY (IdSala) REFERENCES Sala(IdSala)";
    }

    public String EntidadBitacora(){
        return "IdBitacora INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "IdFuncion INTEGER(5) NOT NULL,\n" +
                "EstadoFuncion VARCHAR(1) NOT NULL,\n" +
                "AsientosEnUso VARCHAR(2) NOT NULL,\n" +
                "Cedula VARCHAR(20) NOT NULL,\n" +
                "Nombre VARCHAR(20) NOT NULL,\n" +
                "Apellido VARCHAR(20) NOT NULL,\n" +
                "NumTarjeta VARCHAR(20) NOT NULL,\n" +
                "Fecha DATE,\n" +
                "FOREIGN KEY (IdFuncion) REFERENCES Funcion(IdFuncion)";
    }

    public String Trigger_1() {
        return "CREATE TRIGGER agregar_fecha_bitacora AFTER INSERT ON Bitacora FOR EACH ROW \n" +
                "\tBEGIN\n" +
                "\t\tUPDATE Bitacora SET Fecha = CURRENT_DATE;\n" +
                "\tEND;";
    }

    public String Vista_Bitacora1() {
        return "CREATE VIEW vista_bitacora AS\n" +
                "\tSELECT Bitacora.Nombre AS Nombre, Bitacora.Cedula AS Cedula, Sala.NombreSala AS Sala, Pelicula.NombrePelicula AS Pelicula,\n" +
                "\t\t   Funcion.DiaFuncion AS Dia, Funcion.HoraInicio AS Inicio, Bitacora.Fecha AS Fecha, Bitacora.AsientosEnUso AS Asiento\t   \n" +
                "\tFROM Bitacora \n" +
                "\tINNER JOIN Sala INNER JOIN Pelicula INNER JOIN Funcion\n" +
                "\tON Sala.IdSala = Funcion.IdSala AND\n" +
                "\t   Pelicula.IdPelicula = Funcion.IdPelicula AND\n" +
                "\t   Funcion.IdFuncion = Bitacora.IdFuncion;";
    }

    public String Vista_Bitacora2() {
        return "CREATE VIEW vista_bitacora2 AS\n" +
                "\tSELECT Bitacora.Cedula AS Cedula, Funcion.IdFuncion AS ID_Funcion, Pelicula.NombrePelicula AS Pelicula, Bitacora.AsientosEnUso AS Asiento\n" +
                "\tFROM Bitacora\n" +
                "\tINNER JOIN Funcion INNER JOIN Pelicula\n" +
                "\tON Funcion.IdFuncion = Bitacora.IdFuncion AND\n" +
                "\t   Pelicula.IdPelicula = Funcion.IdPelicula;";
    }

    public String Vista_Funcion1(){
        return "CREATE VIEW vista_funcion AS\n" +
                "\tSELECT  Funcion.IdFuncion AS Id_Funcion, Pelicula.IdPelicula AS Id_Pelicula, Sala.IdSala AS Id_Sala, \n" +
                "\t\t\tPelicula.NombrePelicula AS NombrePelicula, Sala.NombreSala AS NombreSala, Funcion.DiaFuncion AS DiaFuncion, Pelicula.Tipo,\n" +
                "\t\t\tFuncion.HoraInicio AS HoraInicio\n" +
                "\tFROM Funcion\n" +
                "    INNER JOIN Sala INNER JOIN Pelicula\n" +
                "\tON Pelicula.IdPelicula = Funcion.IdPelicula AND\n" +
                "\t   Funcion.IdSala = Sala.idSala;";
    }

    public String ObtenerVistaFuncion(){
        return "SELECT * FROM vista_funcion";
    }

    public String ObtenerVistaBitacora(){
        return "SELECT * FROM Bitacora";
    }

    public String ObtenerAsientosOcupadosSegunFuncion(int id_funcion){
        return "SELECT Cedula, Pelicula, Asiento FROM vista_bitacora2 WHERE ID_Funcion = "+id_funcion+"";
    }

    public String InsertarRegistroBitacora(ObjetoFuncion of, String cedula, String nombre, String apellido, String numTarjeta, String asiento){
        return "INSERT INTO Bitacora(IdFuncion, EstadoFuncion, AsientosEnUso,Cedula, Nombre, Apellido, NumTarjeta) \n" +
                "VALUES("+Integer.parseInt(of.getID())+", 'A','"+asiento+"', '"+cedula+"', '"+nombre+"', '"+apellido+"', '"+numTarjeta+"');";
    }

}
