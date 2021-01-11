package com.ceiba.cliente.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.cliente.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioActualizarClienteTest {

    @Test
    public void validarClienteNoExistePreviaTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(false);
        ServicioActualizarCliente servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente,
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCliente.ejecutar(cliente), ExcepcionDuplicidad.class,
                "El cliente no existe en el sistema");
    }

    @Test
    public void validarTipoDocumentoClienteNoExisteTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(false);
        ServicioActualizarCliente servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente,
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCliente.ejecutar(cliente), ExcepcionDuplicidad.class,
                "El tipo de documento no existe en el sistema");
    }

    @Test
    public void validarActualizarClienteTest() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCliente.existeExcluyendoId(Mockito.anyLong(), Mockito.anyString())).thenReturn(true);
        ServicioActualizarCliente servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente,
                repositorioTipoDocumento);

        // act
        servicioActualizarCliente.ejecutar(cliente);

        // assert
        Mockito.verify(repositorioCliente, Mockito.times(1)).actualizar(cliente);
    }

}
