# Servidor de control de plagas
## Descripción
Servidor de apoyo para Obligatorio 2017 de la asignatura "Programación de Aps. Distribuidas Java/.NET".  
**Carrera**: Analista en Informática / Universidad de la Empresa. 

## Ejecución  (Requiere java 8)
El jar generado en la compilación queda con el nombre "control-plagas-server-0.0.1-SNAPSHOT.jar" en la ruta build/lib. Su ejecución se hace con:   
`$ java -jar control-plagas-server-0.0.1-SNAPSHOT.jar --server.port=XXXX`   
Donde la opción *server.port* es opcional y se puede usar para especificar el puerto de publicación de la aplicación. El puerto por default es 8080.

## Recursos disponibles
**Clientes**:   
*URL*: http://localhost:8080/cliente/   
**Departamentos**:   
*URL*: http://localhost:8080/departamento

## Usuarios y roles
Se tienen dos roles "**operario**" y "**vendedor**" con usuarios pre cargados.      
___
Con rol "**vendedor**":    

*Usuario*: vendedor   
*Contraseña*: Pass01.   

*Usuario*: vend   
*Contaseña*: pass   
___    
Con rol "**operario**":   
    
*Usuario*: operario   
*Constraseña*: Pass02.
   
*Usuario*: oper   
*Contraseña*: pass       

## Seguridad y recursos
El acceso a los recursos está protegido por autenticación HTTP Basic. Las URL y métodos permitidos por rol se describen a continuación:

*URL*: /cliente/**   
*Métodos HTTP permitidos*: POST, DELETE, PUT, PATCH, GET   
*Rol*: **vendedor**   

*URL*: /cliente/**   
*Métodos HTTP permitidos*: GET   
*Rol*: **operario**   

*URL*: /departamento/**   
*Métodos HTTP permitidos*: GET   
*Rol*: **vendedor** y **operario**

## Tipos de peticiones soportadas
### GET (Select de registro)
*Ejemplo de petición*:

```
curl -X GET \
  http://localhost:8080/cliente/4 \
  -H 'authorization: Basic b3BlcjpwYXNz'  
```
*Respuesta*:   
Código HTTP: **200** OK   
Body (Objeto consultado):  

```
{
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "_links": {
        "self": {
            "href": "http://localhost:8080/cliente/4"
        },
        "cliente": {
            "href": "http://localhost:8080/cliente/4"
        },
        "departamento": {
            "href": "http://localhost:8080/cliente/4/departamento"
        }
    }
}
```

### POST (Insert de registro)
*Ejemplo de petición*:   

```
curl -X POST \
  http://localhost:8080/cliente \
  -H 'authorization: Basic dmVuZDpwYXNz' \
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
    "nombre": "Carlos3000",
    "telefono": "092651651",
    "email": "carlos2@test.com",
    "direccion": "Mi Casa",
    "_links": {
        "self": {
            "href": "http://localhost:8080/cliente/6"
        },
        "cliente": {
            "href": "http://localhost:8080/cliente/6"
        },
        "departamento": {
            "href": "http://localhost:8080/cliente/6/departamento"
        }
    }
}
```

### PATCH (Update de campo puntual)
*Ejemplo de petición*:   

```
curl -X PATCH \
  http://localhost:8080/cliente/2 \
  -H 'authorization: Basic dmVuZDpwYXNz' \
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
    "nombre": "Carlos2",
    "telefono": "092YYYYYY",
    "email": "maria@test.com",
    "direccion": "Casa2",
    "_links": {
        "self": {
            "href": "http://localhost:8080/cliente/2"
        },
        "cliente": {
            "href": "http://localhost:8080/cliente/2"
        },
        "departamento": {
            "href": "http://localhost:8080/cliente/2/departamento"
        }
    }
}
```

### PUT (Update de todo un registro)
*Ejemplo de petición*:   

```
curl -X PUT \
  http://localhost:8080/cliente/1 \
  -H 'authorization: Basic dmVuZDpwYXNz' \
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
      "href" : "http://localhost:8080/cliente/1"
    },
    "cliente" : {
      "href" : "http://localhost:8080/cliente/1"
    },
    "departamento" : {
      "href" : "http://localhost:8080/cliente/1/departamento"
    }
  }
}
```

### DELETE (Borrado de registro)
*Ejemplo de petición*:   

```
curl -X DELETE \
  http://localhost:8080/cliente/1 \
  -H 'authorization: Basic dmVuZDpwYXNz' 
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
*JDBC URL*: jdbc:h2:mem:controldb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE   
*User Name*: sa   
*Password*:   
![](H2ConsoleLogin.png)   
Figura 2. Login H2 Console   
![](H2ConsoleManager.png)   
Figura 3. Manager H2 Console   


## Código fuente
https://github.com/earth001/Ejercicios-spring-boot/tree/master/control-plagas-server

## Compilación
Para generar el artefacto de la aplicación a partir del código fuente se requiere ejecutar el siguiente comando en la raiz:   
`$ gradlew clean build`   
Requiere Java 8.
