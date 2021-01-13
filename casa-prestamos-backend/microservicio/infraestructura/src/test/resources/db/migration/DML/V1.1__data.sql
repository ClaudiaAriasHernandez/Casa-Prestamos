insert into usuario(nombre,clave,fecha_creacion) values('test','1234',now());
insert into tipo_documento ( tipo_identificacion, descripcion) values (  'CC', 'Cedula de Ciudadania');
insert into cliente (  nombre,  direccion,  numero_documento, correo,  telefono, id_tipo_documento) values (  'Claudia Arias', 'Calle 62 # 59 -38', '1037641034', 'claudia-ah@hotmail.com',  '3145253630', 1);
insert into prestamo ( fecha_solicitud, fecha_estimada_pago, fecha_pago, valor, valor_mora, valor_interes, valor_recargo, valor_total, estado, id_cliente) values (  '2020-12-27', '2021-01-10', null, 1000000, null, null, null,null, 'D', 1)
 