SELECT cliente.id,cliente.nombre,cliente.direccion,cliente.numero_documento,cliente.correo,cliente.telefono,cliente.id_tipo_documento, tipo_documento.id as id_documento, tipo_documento.tipo_identificacion, tipo_documento.descripcion, prestamo.id as idPrestamo, prestamo.fecha_solicitud, prestamo.fecha_estimada_pago, prestamo.fecha_pago, prestamo.valor, prestamo.valor_mora, prestamo.valor_interes, prestamo.valor_recargo, prestamo.valor_total, prestamo.estado, prestamo.id_cliente
FROM cliente
INNER JOIN tipo_documento
ON cliente.id_tipo_documento = tipo_documento.id
INNER JOIN prestamo
ON prestamo.id_cliente = cliente.id;

