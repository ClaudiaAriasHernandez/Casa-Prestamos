package com.ceiba.prestamo.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.PrestamoTestDataBuilder;

public class ServicioPagarPrestamoTest {

    @Test
    public void validarPrestamoClienteNoExistePreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(false);
      

        ServicioPagarPrestamo servicioPagarPrestamo = new ServicioPagarPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioPagarPrestamo.ejecutar(prestamo), ExcepcionDuplicidad.class,
                "El cliente no existe en el sistema");
    }

    @Test
    public void validarPrestamoNoActivoPreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(false);

        ServicioPagarPrestamo servicioPagarPrestamo = new ServicioPagarPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioPagarPrestamo.ejecutar(prestamo), ExcepcionDuplicidad.class,
                "El cliente no tiene un prestamo activo en el sistema");
    }

    @Test
    public void validarPagarPretamoTest() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(true);
        ServicioPagarPrestamo servicioPagarPrestamo = new ServicioPagarPrestamo(repositorioCliente,
                repositorioPrestamo);

        // act
        servicioPagarPrestamo.ejecutar(prestamo);

        // assert
        Mockito.verify(repositorioPrestamo, Mockito.times(1)).actualizar(prestamo);
    }
}
