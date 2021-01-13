update cliente
set nombre = :nombre,
    direccion = :direccion,
    numero_documento = :numeroDocumento,
    id_tipo_documento = :idTipoDocumento,
    telefono = :telefono,
    correo  = :correo
where id = :id