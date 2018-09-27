--Datos departamentos
insert into departamento (nombre)
values ('Artigas');
insert into departamento (nombre)
values ('Canelones');
insert into departamento (nombre)
values ('Cerro Largo');
insert into departamento (nombre)
values ('Colonia');
insert into departamento (nombre)
values ('Durazno');
insert into departamento (nombre)
values ('Flores');
insert into departamento (nombre)
values ('Florida');
insert into departamento (nombre)
values ('Lavalleja');
insert into departamento (nombre)
values ('Maldonado');
insert into departamento (nombre)
values ('Montevideo');
insert into departamento (nombre)
values ('Paysandú');
insert into departamento (nombre)
values ('Río Negro');
insert into departamento (nombre)
values ('Rivera');
insert into departamento (nombre)
values ('Rocha');
insert into departamento (nombre)
values ('Salto');
insert into departamento (nombre)
values ('San José');
insert into departamento (nombre)
values ('Soriano');
insert into departamento (nombre)
values ('Tacuarembó');
insert into departamento (nombre)
values ('Treinta y Tres');
--Datos roles
insert into rol (id, nombre, descripcion)
values (1, 'ROLE_ESTUDIANTE', 'Rol de estudiante');
insert into rol (id, nombre, descripcion)
values (2, 'ROLE_PROFESOR', 'Rol de profesor');
--Datos usuarios
insert into usuario (login, password)
values ('estudiante', 'estudiante');
insert into usuario (login, password)
values ('profesor', 'profesor');
--Datos roles usuarios
insert into usuario_roles (usuario_id, roles_id)
values (1, 1);
insert into usuario_roles (usuario_id, roles_id)
values (2, 2);
--Datos estudiante
insert into estudiante (direccion, email, nombre, telefono, departamento_id, usuario_id)
values ('Cerro Largo', 'estudiante@class.com', 'Adan C.', '092123123', 10, 1);
--Datos profesor
insert into profesor (cargo, nombre, usuario_id)
values ('Asistente', 'David C.', 2);
