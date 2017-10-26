#Servidor de control de plagas
##Descripción
Servidor de apoyo para Obligatorio 2017 de la asignatura "Programación de Aps. Distribuidas Java/.NET".  
**Carrera**: Analista en Informática / Universidad de la Empresa. 
##Compilación
Para generar el artefacto de la aplicación a partir del código fuente se requiere ejecutar el siguiente comando en la raiz:   
`$ gradlew clean build`
Requiere Java 8.
##Ejecución 
El jar generado en la compilación queda con el nombre "control-plagas-server-0.0.1-SNAPSHOT.jar" en la ruta build/lib. Su ejecución se hace con:   
`$ java -jar control-plagas-server-0.0.1-SNAPSHOT.jar --server.port=XXXX`   
Donde la opción *server.port* es opcional y se puede usar para especificar el puerto de publicación de la aplicación. El puerto por default es 8080.
##Características
Una vez ejecutada la aplicación podra consultar las siguientes utilidades embebidas:   
1. HAL Browser: Permite explorar los endpoint del servidor a través de navegación.  
*URL Acceso*: [http://localhost:8080/browser/index.html#/](http://localhost:8080/browser/index.html#/)
Para acceder a los endpoints protegidos con HTTP Basic deberá agregar en los "Custom Request Headers" el valor:   
Authorization: Basic dmVuZDpwYXNz   
![](HalBrowser.png)  
Figura 1. Captura de Hal Browser
   
2. H2 Console: Consola para realizar consultas SQL y administrar la base de datos H2.   
*URL Acceso*: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)   
**Parámetros de conexión:**   
*Driver*: org.h2.Driver   
*JDBC URL*: jdbc:h2:mem:controldb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE   
*User Name*: sa   
*Password*:   
![](H2ConsoleLogin.png)   
Figura 2. Login H2 Console   
![](H2ConsoleManager.png)   
Figura 2. Manager H2 Console   

##Tipos de peticiones soportadas
###POST (Insert)
Para agregar datos 

```
curl -X POST \
  http://localhost:8080/cliente \
  -H 'authorization: Basic dmVuZDpwYXNz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ebb7f311-2cea-5fc8-20f6-df18e742494e' \
  -d '{
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "departamento":"http://localhost:8080/departamento/3"
}'
``` 

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
