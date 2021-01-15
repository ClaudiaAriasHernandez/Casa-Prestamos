package com.ceiba.prestamo.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.servicio.ServicioConsultarCliente;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.comando.fabrica.FabricaPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.servicio.ServicioPagarPrestamo;

@Component
public class ManejadorPagarPrestamo implements ManejadorComando<ComandoPrestamo> {

    private final FabricaPrestamo fabricaPrestamo;
    private final ServicioPagarPrestamo servicioPagarPrestamo;
    private final ServicioConsultarCliente servicioConsultarCliente;

    public ManejadorPagarPrestamo(FabricaPrestamo fabricaPrestamo, ServicioPagarPrestamo servicioPagarPrestamo,
            ServicioConsultarCliente servicioConsultarCliente) {
        this.fabricaPrestamo = fabricaPrestamo;
        this.servicioPagarPrestamo = servicioPagarPrestamo;
        this.servicioConsultarCliente = servicioConsultarCliente;
    }

    public void ejecutar(ComandoPrestamo comandoPrestamo) {
        DtoCliente cliente = this.servicioConsultarCliente.ejecutar(comandoPrestamo.getTipoIdentificacion(),
                comandoPrestamo.getNumeroDocumento());
        Prestamo prestamo = this.fabricaPrestamo.crear(comandoPrestamo);
        prestamo.setIdCliente(cliente.getId());
        this.servicioPagarPrestamo.ejecutar(prestamo);
    }
}
