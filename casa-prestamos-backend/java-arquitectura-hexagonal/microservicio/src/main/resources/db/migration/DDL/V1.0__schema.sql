create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);
create table cliente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 direccion varchar(100) not null,
 numero_documento varchar(16) not null,
 id_tipo_documento int not null,
 telefono varchar(10) not null,
 correo varchar(100)  null,
 primary key (id)
);
create table tipo_documento (
 id int(11) not null auto_increment,
 tipo_identificacion varchar(5) not null,
 descripcion varchar(50) not null,
 primary key (id)
);

ALTER TABLE cliente
ADD CONSTRAINT FK_ClienteTipoDocumento
FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id);

