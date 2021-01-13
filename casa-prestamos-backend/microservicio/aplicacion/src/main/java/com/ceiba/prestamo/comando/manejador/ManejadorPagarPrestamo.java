package com.ceiba.prestamo.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.comando.fabrica.FabricaPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.servicio.ServicioPagarPrestamo;

@Component
public class ManejadorPagarPrestamo implements ManejadorComando<ComandoPrestamo> {

    private final FabricaPrestamo fabricaPrestamo;
    private final ServicioPagarPrestamo servicioPagarPrestamo;

    public ManejadorPagarPrestamo(FabricaPrestamo fabricaPrestamo,
            ServicioPagarPrestamo servicioPagarPrestamo) {
        this.fabricaPrestamo = fabricaPrestamo;
        this.servicioPagarPrestamo = servicioPagarPrestamo;
    }

    public void ejecutar(ComandoPrestamo comandoPrestamo) {
        Prestamo prestamo = this.fabricaPrestamo.crear(comandoPrestamo);
        this.servicioPagarPrestamo.ejecutar(prestamo);
    }
}
