# Servidor class
## Descripción
Servidor de apoyo para Obligatorio 2018 de la asignatura "Programación de Aps. Distribuidas Java/.NET".  
**Carrera**: Analista en Informática / Universidad de la Empresa. 

## Ejecución  (Requiere java 8)
El jar generado en la compilación queda con el nombre "class-server-0.0.1-SNAPSHOT.jar" en la ruta target/. Su ejecución se hace con:   
`$ java -jar class-server-0.0.1-SNAPSHOT.jar --server.port=XXXX`   
Donde la opción *server.port* es opcional y se puede usar para especificar el puerto de publicación de la aplicación. El puerto por default es 8080.

## Recursos disponibles
**Clientes**:   
*URL*: http://localhost:8080/estudiante/   
**Departamentos**:   
*URL*: http://localhost:8080/departamento/
**Usuario**:   
*URL*: http://localhost:8080/usuario/


## Usuarios y roles
Se tienen dos roles "**estudiante**" y "**profesor**" con usuarios pre cargados.      
___
Con rol "**estudiante**":    

*Usuario*: estudiante   
*Contraseña*: estudiante   

*Usuario*: est   
*Contaseña*: est   
___    
Con rol "**profesor**":   
    
*Usuario*: profesor   
*Constraseña*: profesor
   
*Usuario*: prof   
*Contraseña*: prof       

## Seguridad y recursos
El acceso a los recursos está protegido por autenticación HTTP Basic. Las URL y métodos permitidos por rol se describen a continuación:

*URL*: /estudiante/**   
*Métodos HTTP permitidos*: POST(profesor), DELETE(profesor), PUT(profesor), PATCH(profesor), GET(profesor, estudiante)

*URL*: /profesor/**   
*Métodos HTTP permitidos*: POST(profesor), DELETE(profesor), PUT(profesor), PATCH(profesor), PUT(profesor) 

*URL*: /departamento/**   
*Métodos HTTP permitidos*: GET(profesor, estudiante)

*URL*: /usuario/**   
*Métodos HTTP permitidos*: GET(profesor, estudiante)   

## Tipos de peticiones soportadas
### GET (Select de registro)
*Ejemplo de petición con usuario estudiante*:

```
curl -X GET \
  http://localhost:8080/estudiante/1 \
  -H 'authorization: Basic ZXN0dWRpYW50ZTplc3R1ZGlhbnRl'  
```
*Respuesta*:   
Código HTTP: **200** OK   
Body (Objeto consultado):  

```
{
  "nombre" : "Adan D.",
  "telefono" : "092123123",
  "email" : "estudiante@class.com",
  "direccion" : "Cerro Largo",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/estudiante/1"
    },
    "estudiante" : {
      "href" : "http://localhost:8080/estudiante/1"
    },
    "departamento" : {
      "href" : "http://localhost:8080/estudiante/1/departamento"
    }
  }
}
```

### POST (Insert de registro)
*Ejemplo de petición con usuario profesor*:   

```
curl -X POST \
  http://localhost:8080/estudiante \
  -H 'authorization: Basic cHJvZmVzb3I6cHJvZmVzb3I=' \
  -H 'content-type: application/json' \
  -d '{
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "departamento":"http://localhost:8080/departamento/3"
}'
```
*Respuesta*:   
Código HTTP: **201** Created   
Body (Objeto creado):  

```
{
  "nombre" : "Carlos3000",
  "telefono" : "092651651",
  "email" : "carlos2@test.com",
  "direccion" : "Mi Casa",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/estudiante/2"
    },
    "estudiante" : {
      "href" : "http://localhost:8080/estudiante/2"
    },
    "departamento" : {
      "href" : "http://localhost:8080/estudiante/2/departamento"
    }
  }
}
```

### PATCH (Update de campo puntual)
*Ejemplo de petición con usuario profesor*:   

```
curl -X PATCH \
  http://localhost:8080/estudiante/2 \
  -H 'authorization: Basic cHJvZmVzb3I6cHJvZmVzb3I=' \
  -H 'content-type: application/json' \
  -d '{
    "nombre": "Carlos2"
}'
```

*Respuesta*:   
Código HTTP: **200** OK     
Body (Objeto actualizado):   
  
```
{
  "nombre" : "Carlos2",
  "telefono" : "092651651",
  "email" : "carlos2@test.com",
  "direccion" : "Mi Casa",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/estudiante/2"
    },
    "estudiante" : {
      "href" : "http://localhost:8080/estudiante/2"
    },
    "departamento" : {
      "href" : "http://localhost:8080/estudiante/2/departamento"
    }
  }
}
```

### PUT (Update de todo un registro)
*Ejemplo de petición con usuario profesor*:   

```
curl -X PUT \
  http://localhost:8080/estudiante/1 \
  -H 'authorization: Basic cHJvZmVzb3I6cHJvZmVzb3I=' \
  -H 'content-type: application/json' \
  -d '{
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "departamento":"http://localhost:8080/departamento/3"
}'
```

*Respuesta*:   
Código HTTP: **200** OK     
Body (Objeto actualizado):   

```
{
  "nombre" : "Carlos3000",
  "telefono" : "092651651",
  "email" : "carlos2@test.com",
  "direccion" : "Mi Casa",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/estudiante/1"
    },
    "estudiante" : {
      "href" : "http://localhost:8080/estudiante/1"
    },
    "departamento" : {
      "href" : "http://localhost:8080/estudiante/1/departamento"
    }
  }
}
```

### DELETE (Borrado de registro)
*Ejemplo de petición con usuario profesor*:   

```
curl -X DELETE \
  http://localhost:8080/estudiante/1 \
  -H 'authorization: Basic cHJvZmVzb3I6cHJvZmVzb3I=' 
```

*Respuesta*:   
Código HTTP: **204** No content     
Body: Sin contenido  

## Códigos HTTP adicionales soportados
**404** Not found (Recurso no encontrado, ruta inválida)   
**500** Internal Server Error (Operación Ilegal, Validación de tamaño de campos/Datos nulos/formato de email)   
**401** Unauthorized (Acceso denegado, contraseña incorrecta, rol sin privilegios)   

## Utilidades embebidas
Una vez ejecutada la aplicación podra consultar las siguientes utilidades embebidas:   
1. **HAL Browser**: Permite explorar los endpoint del servidor a través de navegación.  
*URL Acceso*: [http://localhost:8080/browser/index.html#/](http://localhost:8080/browser/index.html#/)
Para acceder a los endpoints protegidos con HTTP Basic deberá agregar en los "Custom Request Headers" el valor:   
Authorization: Basic dmVuZDpwYXNz    
Donde "dmVuZDpwYXNz" es el Base64 de vend:pass.        
![](HalBrowser.png)  
Figura 1. Captura de Hal Browser
   
2. **H2 Console**: Consola para realizar consultas SQL y administrar la base de datos H2.   
*URL Acceso*: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)   
**Parámetros de conexión:**   
*Driver*: org.h2.Driver   
*JDBC URL*: jdbc:h2:mem:classdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE   
*User Name*: sa   
*Password*:   
![](H2ConsoleLogin.png)   
Figura 2. Login H2 Console   
![](H2ConsoleManager.png)   
Figura 3. Manager H2 Console   


## Código fuente
https://github.com/earth001/Ejercicios-spring-boot/tree/master/class-server

## Compilación
Para generar el artefacto de la aplicación a partir del código fuente se requiere ejecutar el siguiente comando en la raíz:   
`$ mvn clean install`   
Requiere Java 8.
