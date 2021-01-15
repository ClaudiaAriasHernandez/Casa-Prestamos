package com.ceiba.prestamo.servicio;

import static org.mockito.Matchers.anyLong;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.PrestamoTestDataBuilder;

public class ServicioCrearPrestamoTest {

    @Test
    public void validarObligatorioValorPrestamoTest() {
        // arrange
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conValor(null);
        // act - assert
        BasePrueba.assertThrows(() -> prestamoTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar el valor del prestamo");
    }

    @Test
    public void validarClientePrestamoExistePreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(true);
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPrestamo.ejecutar(prestamo), ExcepcionDuplicidad.class,
                "El cliente ya tiene un prestamo en el sistema");
    }

    @Test
    public void validarClientePrestamoNoExistePreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(false);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(false);
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPrestamo.ejecutar(prestamo), ExcepcionDuplicidad.class,
                "El cliente no existe en el sistema");
    }

    @Test
    public void validarCreacionPrestamoTest() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(false);
        Mockito.when(repositorioPrestamo.crear(prestamo)).thenReturn(1L);
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act
        Long idPrestamo = servicioCrearPrestamo.ejecutar(prestamo);

        // assert
        BasePrueba.assertEqualsObject(1L, idPrestamo);
    }

}
