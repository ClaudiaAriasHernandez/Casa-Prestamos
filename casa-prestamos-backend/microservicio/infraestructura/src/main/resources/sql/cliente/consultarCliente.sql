SELECT cliente.id,cliente.nombre,cliente.direccion,cliente.numero_documento,cliente.correo,cliente.telefono,cliente.id_tipo_documento, tipo_documento.id as id_documento, tipo_documento.tipo_identificacion, tipo_documento.descripcion
FROM cliente
INNER JOIN tipo_documento
ON cliente.id_tipo_documento = tipo_documento.id
WHERE cliente.numero_documento = :numeroDocumento and tipo_documento.tipo_identificacion = :tipoIdentificacion;

