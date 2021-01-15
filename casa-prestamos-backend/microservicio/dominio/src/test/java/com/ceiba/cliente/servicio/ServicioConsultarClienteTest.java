package com.ceiba.cliente.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.cliente.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.TipoDocumentoTestDataBuilder;

public class ServicioConsultarClienteTest {

    @Test
    public void validarClienteNoExistePreviaTest() {

        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        DaoCliente daoCliente = Mockito.mock(DaoCliente.class);
        // act
        ServicioConsultarCliente servicioActualizarCliente = new ServicioConsultarCliente(daoCliente);
        // act - assert
        BasePrueba
                .assertThrows(
                        () -> servicioActualizarCliente.ejecutar(tipoDocumento.getTipoIdentificacion(),
                                cliente.getNumeroDocumento()),
                        ExcepcionDuplicidad.class, "El cliente no existe en el sistema");
    }

    @Test
    public void validarConsultaClienteTest() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();

        ;
        DtoTipoDocumento dtoTipoDocumento = new DtoTipoDocumento(tipoDocumento.getId(),
                tipoDocumento.getTipoIdentificacion(), tipoDocumento.getDescripcion());

        DtoCliente dtoCliente = new DtoCliente(cliente.getId(), cliente.getNombre(), cliente.getDireccion(),
                cliente.getNumeroDocumento(), cliente.getCorreo(), cliente.getTelefono(), cliente.getIdTipoDocumento(),
                dtoTipoDocumento);
        DaoCliente daoCliente = Mockito.mock(DaoCliente.class);
        Mockito.when(daoCliente.consultarCliente(tipoDocumento.getTipoIdentificacion(), cliente.getNumeroDocumento()))
                .thenReturn(dtoCliente);
        ServicioConsultarCliente servicioActualizarCliente = new ServicioConsultarCliente(daoCliente);

        // act
        DtoCliente datosCliente = servicioActualizarCliente.ejecutar(tipoDocumento.getTipoIdentificacion(),
                cliente.getNumeroDocumento());

        // assert
        BasePrueba.assertEqualsObject(dtoCliente, datosCliente);
    }

}
