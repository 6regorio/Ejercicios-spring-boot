#Servidor de control de plagas
##Descripción
Servidor de apoyo para Obligatorio 2017 de la asignatura "Programación de Aps. Distribuidas Java/.NET".  
**Carrera**: Analista en Informática / Universidad de la Empresa. 
##Compilación
`$ gradlew clean build`
##Ejecución 
Tomar el jar control-plagas-server-0.0.1-SNAPSHOT.jar de la carpeta build/lib y ejecutarlo con Java 8 de la siguiente manera:  
`$ java -jar control-plagas-server-0.0.1-SNAPSHOT.jar --server.port=XXXX`
Donde el la opción *server.port* es opcional y se puede usar especificar el puerto de publicación de la aplicación. Si se omite el puerto por default es 8080.
##Características
Una vez ejecutada la aplicación podra consultar las siguientes aplicaciones:
1. HAL Browser
2. H2 Console

##Ejemplo de peticiones
###Insert
POST /cliente/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache

{
    "nombre": "Carlos2",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "departamento":"http://localhost:8080/departamento/1"
}
###Update (Campo puntual) 

PATCH /cliente/3 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache

{
    "nombre": "Carlos24"
}

###Update (todo el registro)
PUT /cliente/3 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache

{
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "departamento":"http://localhost:8080/departamento/3"
}

###Delete
DELETE /cliente/1 HTTP/1.1
Host: localhost:8080
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache
