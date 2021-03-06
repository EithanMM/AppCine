DROP TABLE IF EXISTS Sala;
CREATE TABLE Sala (
IdSala INTEGER(5) PRIMARY KEY,
NombreSala VARCHAR(10) NOT NULL,
NumAsientos INTEGER(5) NOT NULL
);

DROP TABLE IF EXISTS Pelicula;
CREATE TABLE Pelicula (
IdPelicula INTEGER(5) PRIMARY KEY,
NombrePelicula VARCHAR(30) NOT NULL,
Sinopsis VARCHAR(300) NOT NULL,
Publico VARCHAR(1) NOT NULL,
Tipo VARCHAR(20) NOT NULL
);




DROP TABLE IF EXISTS Funcion;
CREATE TABLE Funcion(
IdFuncion PRIMARY KEY,
IdPelicula INTEGER(5) NOT NULL,
IdSala INTEGER(5) NOT NULL,
DiaFuncion VARCHAR(20) NOT NULL,
HoraInicio TIME NOT NULL,
FOREIGN KEY (IdPelicula) REFERENCES Pelicula(IdPelicula),
FOREIGN KEY (IdSala) REFERENCES Sala(IdSala)
);

DROP TABLE IF EXISTS Bitacora;
CREATE TABLE Bitacora(
IdBitacora INTEGER PRIMARY KEY AUTOINCREMENT,
IdFuncion INTEGER(5) NOT NULL,
EstadoFuncion VARCHAR(1) NOT NULL,
AsientosEnUso VARCHAR(2) NOT NULL,
Cedula VARCHAR(20) NOT NULL,
Nombre VARCHAR(20) NOT NULL,
Apellido VARCHAR(20) NOT NULL,
NumTarjeta VARCHAR(20) NOT NULL,
Fecha DATE,
FOREIGN KEY (IdFuncion) REFERENCES Funcion(IdFuncion)
);

DROP TRIGGER IF EXISTS agregar_fecha_bitacora;
CREATE TRIGGER agregar_fecha_bitacora AFTER INSERT ON Bitacora FOR EACH ROW 
	BEGIN
		UPDATE Bitacora SET Fecha = CURRENT_DATE;
	END;
	

INSERT INTO Pelicula(IdPelicula, NombrePelicula, Sinopsis, Publico, Tipo) VALUES(1,"Coco","Un coco que se perdio","E","Animado");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(2,"Cementerio Maldito","Jovenes que entran a un cementerio, descubren un terrible secreto","M","Horro");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(3,"Capitana Marvel","Una guerrera extraterrestre de la civilización Kree se encuentra atrapada en medio de una batalla. Con la ayuda de Nick Fury trata de descubrir los secretos de su pasado mientras aprovecha sus poderes para terminar la guerra.","E","Accion");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(4,"Avengers: Infinity War","Los superhéroes se alían para vencer al poderoso Thanos, el peor enemigo al que se han enfrentado. Si Thanos logra reunir las seis gemas del infinito: poder, tiempo, alma, realidad, mente y espacio, nadie podrá detenerlo.","E","Accion");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(5,"La pasión de Cristo","Condenado a morir crucificado, Jesús de Nazaret experimenta y soporta la agonía de sus últimas doce horas.","E","Drama");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(6,"El aro","Una reportera debe resolver el misterio de una cinta que trae muerte a sus espectadores, antes de que sucumba a su poder.","M","Horro");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(7,"Doctor Strange: hechicero supremo","Después de sufrir un accidente, un brillante y arrogante cirujano busca rehabilitarse mediante técnicas alternativas. Sus intentos le llevan a descubrir que ha sido designado para encabezar la lucha contra una fuerza oscura y sobrenatural.","E","Accion");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(8,"Bohemian Rhapsody","Freddie Mercury desafía los estereotipos y se convierte en uno de los vocalistas más reconocidos de la música mundial. Después de dejar la banda Queen para seguir una carrera como solista, Mercury se reúne con la banda para dar una de las mejores actuaciones en la historia del rock 'n' roll.","M","Drama");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(9,"La forma del agua","Elisa es una joven muda que se enamora de un hombre anfibio que está recluido en un acuario en un laboratorio secreto, propiedad del Gobierno, en el que ella trabaja limpiando. Llevada por el amor, Elisa trama un plan para liberar al mutante.","M","Drama");
INSERT INTO Pelicula(IdPelicula,NombrePelicula,Sinopsis,Publico,Tipo) VALUES(10,"Dragon Ball Z: La Batalla de los Dioses","Los Peleadores Z deben contender con Bills, el Dios de la Destrucción. Pero se necesita un dios para luchar contra un dios, y ninguno de ellos lo es... ni siquiera los Saiyans.","E","Animacion");

INSERT INTO Sala(IdSala,NombreSala, NumAsientos)VALUES(1,"Sala A",30);
INSERT INTO Sala(IdSala,NombreSala, NumAsientos)VALUES(2,"Sala B",30);
INSERT INTO Sala(IdSala,NombreSala, NumAsientos)VALUES(3,"Sala C",30);
INSERT INTO Sala(IdSala,NombreSala, NumAsientos)VALUES(4,"Sala D",30);

INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(1,1,1,"Lunes", '13:00');
INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(2,2,1,"Lunes", '9:00');

INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(3,3,2,"Martes",'16:00');
INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(4,4,2,"Martes",'20:00');

INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(5,5,3,"Miercoles",'10:00');
INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(6,6,3,"Miercoles",'12:00');

INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(7,7,4,"Jueves",'17:00');
INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(8,8,4,"Jueves",'10:00' );

INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(9,9,2,"Viernes",'15:00');
INSERT INTO Funcion(IdFuncion, IdPelicula, IdSala, DiaFuncion, HoraInicio) VALUES(10,10,2,"Viernes",'21:00');

INSERT INTO Bitacora(IdFuncion, EstadoFuncion, AsientosEnUso,Cedula, Nombre, Apellido, NumTarjeta) 
VALUES(1,"Activo","A1","116290648","Eithan","Mendez","19000092");
INSERT INTO Bitacora(IdFuncion, EstadoFuncion, AsientosEnUso,Cedula, Nombre, Apellido, NumTarjeta) 
VALUES(1,"Activo","A2","116290648","Eithan","Mendez","19000092");
INSERT INTO Bitacora(IdFuncion, EstadoFuncion, AsientosEnUso,Cedula, Nombre, Apellido, NumTarjeta) 
VALUES(6,"Activo","C5","114309876","Jorge","Hernandez","190044442");

DROP VIEW IF EXISTS vista_bitacora;
CREATE VIEW vista_bitacora AS
	SELECT Bitacora.Nombre AS Nombre, Bitacora.Cedula AS Cedula, Sala.NombreSala AS Sala, Pelicula.NombrePelicula AS Pelicula,
		   Funcion.DiaFuncion AS Dia, Funcion.HoraInicio AS Inicio, Bitacora.Fecha AS Fecha, Bitacora.AsientosEnUso AS Asiento	   
	FROM Bitacora 
	INNER JOIN Sala INNER JOIN Pelicula INNER JOIN Funcion
	ON Sala.IdSala = Funcion.IdSala AND
	   Pelicula.IdPelicula = Funcion.IdPelicula AND
	   Funcion.IdFuncion = Bitacora.IdFuncion;
	  
DROP VIEW IF EXISTS vista_bitacora2;
CREATE VIEW vista_bitacora2 AS
	SELECT Bitacora.Cedula AS Cedula, Funcion.IdFuncion AS ID_Funcion, Pelicula.NombrePelicula AS Pelicula, Bitacora.AsientosEnUso AS Asiento
	FROM Bitacora
	INNER JOIN Funcion INNER JOIN Pelicula
	ON Funcion.IdFuncion = Bitacora.IdFuncion AND
	   Pelicula.IdPelicula = Funcion.IdPelicula;
	  
DROP VIEW IF EXISTS vista_funcion;
CREATE VIEW vista_funcion AS
	SELECT  Funcion.IdFuncion AS Id_Funcion, Pelicula.IdPelicula AS Id_Pelicula, Sala.IdSala AS Id_Sala, 
			Pelicula.NombrePelicula AS NombrePelicula, Sala.NombreSala AS NombreSala, Funcion.DiaFuncion AS DiaFuncion, Pelicula.Tipo,
			Funcion.HoraInicio AS HoraInicio
	FROM Funcion
    INNER JOIN Sala INNER JOIN Pelicula
	ON Pelicula.IdPelicula = Funcion.IdPelicula AND
	   Funcion.IdSala = Sala.idSala;
	  

	 -- SELECT * FROM vista_bitacora;
 -- SELECT Cedula, Pelicula, Asiento FROM vista_bitacora2 WHERE ID_Funcion = 1;
 -- SELECT * FROM vista_bitacora;
