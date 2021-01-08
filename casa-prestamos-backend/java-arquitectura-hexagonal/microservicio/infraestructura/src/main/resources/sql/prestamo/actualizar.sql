update prestamo
set  fecha_solicitud = :fechaSolicitud,
     fecha_estimada_pago = :fechaEstimadaPago,
     fecha_pago = :fechaPago,
     valor = :valor,
     valor_mora  = :valorMora,
     valor_interes  = :valorInteres,
     valor_recargo = :valorRecargo,
     valor_total  = :valorTotal,
     estado  = :estado,
     id_cliente  = :idCliente
where id = :id