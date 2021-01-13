package com.ceiba.cliente.servicio;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import com.ceiba.BasePrueba;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.cliente.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;

public class ServicioCrearClienteTest {

    @Test
    public void validarObligatorioNombreClienteTest() {
        // arrange
        ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder().conNombre(null);
        // act - assert
        BasePrueba.assertThrows(() -> clienteTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar el nombre del cliente");
    }

    @Test
    public void validarObligatorioTelefonoClienteTest() {
        // arrange
        ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder().conTelefono(null);
        // act - assert
        BasePrueba.assertThrows(() -> clienteTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar el número de telefono del cliente");
    }

    @Test
    public void validarObligatorioTelefonoMayor7ClienteTest() {
        // arrange
        ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder().conTelefono("12345");
        // act - assert
        BasePrueba.assertThrows(() -> clienteTestDataBuilder.build(), ExcepcionLongitudValor.class,
                "El número de telefono debe tener una longitud mayor o igual a 7");
    }

    @Test
    public void validarObligatorioDireccionClienteTest() {
        // arrange
        ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder().conDireccion(null);
        // act - assert
        BasePrueba.assertThrows(() -> clienteTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar la dirección del cliente");
    }

    @Test
    public void validarObligatorioNumeroDocumentoClienteTest() {
        // arrange
        ClienteTestDataBuilder clienteTestDataBuilder = new ClienteTestDataBuilder().conNumeroDocumento(null);
        // act - assert
        BasePrueba.assertThrows(() -> clienteTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar el número de documento del cliente");
    }

    @Test
    public void validarClienteExistenciaPreviaTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioCliente.existe(anyString())).thenReturn(true);
        ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente,
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCliente.ejecutar(cliente), ExcepcionDuplicidad.class,
                "El cliente ya existe en el sistema");
    }

    @Test
    public void validarCreacionClienteTest() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(anyLong())).thenReturn(true);
        Mockito.when(repositorioCliente.existe(anyString())).thenReturn(false);
        Mockito.when(repositorioCliente.crear(cliente)).thenReturn(1L);
        ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente,
                repositorioTipoDocumento);
        // act
        Long idUsuario = servicioCrearCliente.ejecutar(cliente);

        // assert
        BasePrueba.assertEqualsObject(1L, idUsuario);
    }

    @Test
    public void validarTipoDocumentoClienteNoExisteTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(anyLong())).thenReturn(false);
        Mockito.when(repositorioCliente.existe(anyString())).thenReturn(true);
        ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente,
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCliente.ejecutar(cliente), ExcepcionDuplicidad.class,
                "El tipo de documento no existe en el sistema");
    }

}
