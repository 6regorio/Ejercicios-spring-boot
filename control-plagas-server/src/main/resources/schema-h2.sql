create table departamento (id int auto_increment, nombre varchar(256)); 
create table cliente (id int auto_increment, nombre varchar(256), telefono varchar(256), email varchar(256), direccion varchar(256),departamento_id int, FOREIGN KEY(departamento_id) REFERENCES departamento(id)); 
