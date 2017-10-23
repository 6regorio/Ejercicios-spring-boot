create table departamento (id int auto_increment, nombre varchar(256) not null); 
create table cliente (id int auto_increment, nombre varchar(256) not null, telefono varchar(256) not null, email varchar(256), direccion varchar(256),departamento_id int, FOREIGN KEY(departamento_id) REFERENCES departamento(id)); 
