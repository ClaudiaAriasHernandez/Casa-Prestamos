package com.ceiba.tipodocumento.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.TipoDocumentoTestDataBuilder;

public class ServicioActualizarTipoDocumentoTest {

    @Test
    public void validarTipoDocumentoNoExistePreviaTest() {

        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioTipoDocumento.existeTipoIdentificacion(Mockito.anyString())).thenReturn(false);
        ServicioActualizarTipoDocumento servicioActualizarTipoDocumento = new ServicioActualizarTipoDocumento(
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarTipoDocumento.ejecutar(tipoDocumento),
                ExcepcionDuplicidad.class, "El tipo de documento no existe en el sistema");
    }

    @Test
    public void validarActualizarTipoDocumentoTest() {
        // arrange

        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioTipoDocumento.existeTipoIdentificacion(Mockito.anyString())).thenReturn(true);
        ServicioActualizarTipoDocumento servicioActualizarTipoDocumento = new ServicioActualizarTipoDocumento(
                repositorioTipoDocumento);

        // act
        servicioActualizarTipoDocumento.ejecutar(tipoDocumento);

        // assert
        Mockito.verify(repositorioTipoDocumento, Mockito.times(1)).actualizar(tipoDocumento);
    }

}
