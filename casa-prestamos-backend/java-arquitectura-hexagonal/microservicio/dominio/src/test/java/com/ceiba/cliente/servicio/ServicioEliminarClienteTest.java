package com.ceiba.cliente.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.cliente.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioEliminarClienteTest {

    @Test
    public void validarClienteNoExistePreviaTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);

        Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(false);
        ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarCliente.ejecutar(cliente.getId()), ExcepcionDuplicidad.class,
                "El cliente no existe en el sistema");
    }

    @Test
    public void validarEliminarClienteTest() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existeId(Mockito.anyLong())).thenReturn(true);
        ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente);

        // act
        servicioEliminarCliente.ejecutar(cliente.getId());

        // assert
        Mockito.verify(repositorioCliente, Mockito.times(1)).eliminar(cliente.getId());
    }
}
