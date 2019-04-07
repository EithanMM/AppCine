# AppCine
Repositorio para el proyecto de moviles.


En esta rama se va a postear un tutorial donde se explica exactamente cuales son los pasos para pdoer correr un proyecto de Android studio
en otra computadora.

Antes de empezar, cabe aclarar que el proyecto va a estar construído en la API 19: o la Android 4.4(KitKat)

En los pasos para poder correc un proyecto bajado desde github exitosamente, primero que nada debemos crearnos una carpeta que
sirva como repositorio local, por ejemplo "C:\moviles", luego que estamos dentro de la carpeta, usamos el bash de bit y ejecutamos los
siguientes comandos:


 git init - Crea un repositorio local.
 
 git clone https://github.com/EithanMM/AppCine.git "path" - cambiar "path" por la ruta donde quieren guardar el repositorio.
 
 git checkout -b developer - Crea un branch llamado developer, en el casod e cada pc se tiene que crear uno, ya que ya existe dicho branch
con ese nombre en el repositorio remoto, entonces los cambios se subiran ahí, recordar que cuando se hace el git push seria git push origin
developer.


Una vez terminados estos paso se puede acceder al Android Studio y desde ahi seguir el tutorial de como importar un projecto existente para
que se trabaje, esto con el fin de que el simple hecho de bajar el repositorio y abrirlo desde el Andorid Studio normalmente causa problemas,
entonces la forma mas segura de trabajar con el proyecto reciend bajado de git es importandolo desde la carpeta donde se bajo el proyecto.
 Link del tutorial: https://www.jetbrains.com/help/idea/importing-an-existing-android-project.html
