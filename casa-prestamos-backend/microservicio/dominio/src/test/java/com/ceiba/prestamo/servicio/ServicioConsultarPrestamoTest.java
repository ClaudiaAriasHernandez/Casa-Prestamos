package com.ceiba.prestamo.servicio;

import static org.mockito.Matchers.anyLong;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.PrestamoTestDataBuilder;

public class ServicioConsultarPrestamoTest {

    @Test
    public void validarPrestamoClienteNoExistePreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);

        DaoPrestamo daoPrestamo = Mockito.mock(DaoPrestamo.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(false);

        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo, daoPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioConsultarPrestamo.ejecutar(prestamo.getId()), ExcepcionDuplicidad.class,
                "El cliente no existe en el sistema");
    }

    @Test
    public void validarPrestamoNoActivoPreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        DaoPrestamo daoPrestamo = Mockito.mock(DaoPrestamo.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(false);

        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo, daoPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioConsultarPrestamo.ejecutar(prestamo.getId()), ExcepcionDuplicidad.class,
                "El cliente no tiene un prestamo activo en el sistema");
    }

    @Test
    public void validarConsultarPrestamoConMoraInteresTest() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();

        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaSolicitud(),
                prestamo.getFechaEstimadaPago(), prestamo.getFechaPago(), prestamo.getValor(), prestamo.getValorMora(),
                prestamo.getValorInteres(), prestamo.getValorRecargo(), prestamo.getValorTotal(), prestamo.getEstado(),
                prestamo.getIdCliente(), null);

        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        DaoPrestamo daoPrestamo = Mockito.mock(DaoPrestamo.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(true);
        Mockito.when(daoPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(dtoPrestamo);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo, daoPrestamo);

        // act
        DtoPrestamo datosPrestamos = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(dtoPrestamo, datosPrestamos);
    }

    @Test
    public void validarConsultarPrestamoConMoraInteresRecargoTest() {
        // arrange

        Prestamo prestamo = new Prestamo(1L, generarFecha(2020, 11, 19), generarFecha(2021, 00, 9),
                generarFecha(2021, 00, 11), 1000000.0, 0, 0, 0, 0, "D", 1L);

        DtoPrestamo dtoPrestamo = new DtoPrestamo(1L, generarFecha(2020, 11, 19), generarFecha(2021, 00, 9),
                generarFecha(2021, 00, 11), 1000000.0, 10000, 30000, 20000, 1050000, "D", 1L, null);

        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        DaoPrestamo daoPrestamo = Mockito.mock(DaoPrestamo.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(true);
        Mockito.when(daoPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(dtoPrestamo);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo, daoPrestamo);
        // act
        DtoPrestamo datosPrestamos = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(dtoPrestamo, datosPrestamos);
    }

    @Test
    public void validarConsultarPrestamoConInteres15DiasTest() {
        // arrange

        Prestamo prestamo = new Prestamo(1L, generarFechaDias(generarFechaActual(), 15), generarFechaActual(),
                generarFechaActual(), 1000000.0, 0, 0, 0, 0, "D", 1L);

        DtoPrestamo dtoPrestamo = new DtoPrestamo(1L, generarFechaDias(generarFechaActual(), 15), generarFechaActual(),
                generarFechaActual(), 1000000.0, 10000, 30000, 20000, 1050000, "D", 1L, null);

        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        DaoPrestamo daoPrestamo = Mockito.mock(DaoPrestamo.class);
        Mockito.when(repositorioCliente.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(anyLong())).thenReturn(true);
        Mockito.when(daoPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(dtoPrestamo);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo, daoPrestamo);

        // act
        DtoPrestamo datosPrestamos = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(dtoPrestamo, datosPrestamos);
    }

    private Date generarFecha(int anio, int mes, int dia) {
        Calendar fecha = Calendar.getInstance();

        fecha.set(anio, mes, dia);

        return fecha.getTime();
    }

    private Date generarFechaDias(Date now, int dias) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(now);
        cal.add(Calendar.DATE, -dias);

        return cal.getTime();
    }

    private Date generarFechaActual() {
        Calendar fechaActual = Calendar.getInstance();

        return fechaActual.getTime();
    }
}
