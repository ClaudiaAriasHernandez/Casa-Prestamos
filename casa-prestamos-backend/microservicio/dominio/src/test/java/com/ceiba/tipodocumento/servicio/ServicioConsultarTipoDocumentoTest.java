package com.ceiba.tipodocumento.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.dao.DaoTipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.TipoDocumentoTestDataBuilder;

public class ServicioConsultarTipoDocumentoTest {

    @Test
    public void validarTipoDocumentoNoExistePreviaTest() {

        // arrange

        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        DaoTipoDocumento daoTipoDocumento = Mockito.mock(DaoTipoDocumento.class);
        // act
        ServicioConsultarTipoDocumento servicioActualizarTipoDocumento = new ServicioConsultarTipoDocumento(
                daoTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarTipoDocumento.ejecutar(tipoDocumento.getTipoIdentificacion()),
                ExcepcionDuplicidad.class, "El tipo de documento no existe en el sistema");
    }

    @Test
    public void validarConsultaTipoDocumentoTest() {
        // arrange

        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();

        DtoTipoDocumento dtoTipoDocumento = new DtoTipoDocumento(tipoDocumento.getId(),
                tipoDocumento.getTipoIdentificacion(), tipoDocumento.getDescripcion());

        DaoTipoDocumento daoTipoDocumento = Mockito.mock(DaoTipoDocumento.class);
        Mockito.when(daoTipoDocumento.consultarTipoDocumento(tipoDocumento.getTipoIdentificacion()))
                .thenReturn(dtoTipoDocumento);
        ServicioConsultarTipoDocumento servicioActualizarTipoDocumento = new ServicioConsultarTipoDocumento(
                daoTipoDocumento);

        // act
        DtoTipoDocumento datosTipoDocumento = servicioActualizarTipoDocumento
                .ejecutar(tipoDocumento.getTipoIdentificacion());

        // assert
        BasePrueba.assertEqualsObject(dtoTipoDocumento, datosTipoDocumento);
    }

}
