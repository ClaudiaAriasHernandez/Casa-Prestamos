package com.ceiba.tipodocumento.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioConsultarTipoDocumento;

@Component
public class ManejadorConsultarTipoDocumento {

    private final ServicioConsultarTipoDocumento servicioConsultarTipoDocumento;

    public ManejadorConsultarTipoDocumento(ServicioConsultarTipoDocumento servicioConsultarTipoDocumento) {

        this.servicioConsultarTipoDocumento = servicioConsultarTipoDocumento;

    }

    public DtoTipoDocumento ejecutar(String tipoIdentificacion) {

        return this.servicioConsultarTipoDocumento.ejecutar(tipoIdentificacion);
    }
}
