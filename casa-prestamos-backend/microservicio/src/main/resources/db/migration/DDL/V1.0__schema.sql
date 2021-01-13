create table usuario (
 id int not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);
create table cliente (
 id int not null auto_increment,
 nombre varchar(100) not null,
 direccion varchar(100) not null,
 numero_documento varchar(16) not null,
 id_tipo_documento int not null,
 telefono varchar(10) not null,
 correo varchar(100)  null,
 primary key (id)
);
create table tipo_documento (
 id int not null auto_increment,
 tipo_identificacion varchar(5) not null,
 descripcion varchar(50) not null,
 primary key (id)
);

ALTER TABLE cliente
ADD CONSTRAINT FK_ClienteTipoDocumento
FOREIGN KEY (id_tipo_documento) REFERENCES tipo_documento(id);

create table prestamo (
 id int not null auto_increment,
 fecha_solicitud date not null,
 fecha_estimada_pago date not null,
 fecha_pago date null, 
 valor double not null,
 valor_mora double null, 
 valor_interes double null,
 valor_recargo double null,
 valor_total double null,
 estado varchar(2) not null,
 id_cliente int not null,
 primary key (id)
);


ALTER TABLE prestamo
ADD CONSTRAINT FK_PrestamoCliente
FOREIGN KEY (id_cliente) REFERENCES cliente(id);
