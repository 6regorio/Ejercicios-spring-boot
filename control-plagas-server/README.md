#Insert
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
#Update (Campo puntual) 

PATCH /cliente/3 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache

{
    "nombre": "Carlos24"
}

#Update (todo el registro)
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

#Delete
DELETE /cliente/1 HTTP/1.1
Host: localhost:8080
Authorization: Basic b3BlcjpwYXNz
Cache-Control: no-cache
