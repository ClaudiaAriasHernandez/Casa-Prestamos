update tipo_documento
set  tipo_identificacion = :tipoIdentificacion,
	 descripcion = :descripcion,
	 estado= :estado,
where id = :id

