package com.ceiba.prestamo.servicio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.PrestamoTestDataBuilder;

public class ServicioConsultarPrestamoTest {

    @Test
    public void validarPrestamoClienteNoExistePreviaTest() {

        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(false);

        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo);
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
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(false);

        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo);
        // act - assert
        BasePrueba.assertThrows(() -> servicioConsultarPrestamo.ejecutar(prestamo.getId()), ExcepcionDuplicidad.class,
                "El cliente no tiene un prestamo activo en el sistema");
    }

    // @Test
    // public void validarConsultarPrestamoTest() {
    // // arrange
    // Prestamo prestamo = new PrestamoTestDataBuilder().build();
    // RepositorioCliente repositorioCliente =
    // Mockito.mock(RepositorioCliente.class);
    // Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
    // RepositorioPrestamo repositorioPrestamo =
    // Mockito.mock(RepositorioPrestamo.class);
    //
    // Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(true);
    // ServicioConsultarPrestamo servicioConsultarPrestamo = new
    // ServicioConsultarPrestamo(repositorioCliente,
    // repositorioPrestamo);
    //
    // // act
    // servicioConsultarPrestamo.ejecutar(prestamo.getId());
    //
    // // assert
    // Mockito.verify(repositorioPrestamo,
    // Mockito.times(1)).listarPorIdCliente(prestamo.getId());
    // }

    @Test
    public void validarConsultarPrestamoConMoraInteresTest() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        List<DtoPrestamo> myList = new ArrayList<>();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaSolicitud(),
                prestamo.getFechaEstimadaPago(), prestamo.getFechaPago(), prestamo.getValor(), prestamo.getValorMora(),
                prestamo.getValorInteres(), prestamo.getValorRecargo(), prestamo.getValorTotal(), prestamo.getEstado(),
                prestamo.getIdCliente(), null);
        myList.add(dtoPrestamo);
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(myList);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo);

        // act
        List<DtoPrestamo> prestamoList = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(myList, prestamoList);
    }

    @Test
    public void validarConsultarPrestamoConMoraInteresRecargoTest() {
        // arrange

        Prestamo prestamo = new Prestamo(1L, generarFecha(2020, 11, 19), generarFecha(2021, 00, 9),
                generarFecha(2021, 00, 11), 1000000.0, 0, 0, 0, 0, "D", 1L);
        List<DtoPrestamo> myList = new ArrayList<>();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(1L, generarFecha(2020, 11, 19), generarFecha(2021, 00, 9),
                generarFecha(2021, 00, 11), 1000000.0, 10000, 30000, 20000, 1050000, "D", 1L, null);
        myList.add(dtoPrestamo);
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(myList);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo);

        // act
        List<DtoPrestamo> prestamoList = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(myList, prestamoList);
    }

    @Test
    public void validarConsultarPrestamoConInteres15DiasTest() {
        // arrange

        Prestamo prestamo = new Prestamo(1L, generarFechaDias(generarFechaActual(), 15), generarFechaActual(), generarFechaActual(), 1000000.0, 0,
                0, 0, 0, "D", 1L);
        List<DtoPrestamo> myList = new ArrayList<>();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(1L,   generarFechaDias(generarFechaActual(), 15), generarFechaActual(), generarFechaActual(),
                1000000.0, 10000, 30000, 20000, 1050000, "D", 1L, null);
        myList.add(dtoPrestamo);
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.existePrestamoActivo(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.listarPorIdCliente(prestamo.getId())).thenReturn(myList);
        ServicioConsultarPrestamo servicioConsultarPrestamo = new ServicioConsultarPrestamo(repositorioCliente,
                repositorioPrestamo);

        // act
        List<DtoPrestamo> prestamoList = servicioConsultarPrestamo.ejecutar(prestamo.getId());

        // assert
        BasePrueba.assertEqualsObject(myList, prestamoList);
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
        Date nowMinus15 = cal.getTime();

        return nowMinus15;
    }

    private Date generarFechaActual() {
        Calendar c = Calendar.getInstance();
        Date now = c.getTime();

        return now;
    }
}
