package com.ceiba.tipodocumento.servicio;

import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.TipoDocumentoTestDataBuilder;

public class ServicioCrearTipoDocumentoTest {

    @Test
    public void validarObligatorioTipoTipoDocumentoTest() {
        // arrange
        TipoDocumentoTestDataBuilder tipoDocumentoTestDataBuilder = new TipoDocumentoTestDataBuilder()
                .conTipoIdentificacion(null);
        // act - assert
        BasePrueba.assertThrows(() -> tipoDocumentoTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar el tipo de documento");
    }

    @Test
    public void validarObligatorioDescripcionTipoDocumentoTest() {
        // arrange
        TipoDocumentoTestDataBuilder tipoDocumentoTestDataBuilder = new TipoDocumentoTestDataBuilder()
                .conDescripcion(null);
        // act - assert
        BasePrueba.assertThrows(() -> tipoDocumentoTestDataBuilder.build(), ExcepcionValorObligatorio.class,
                "Se debe ingresar la descripcion del tipo de documento");
    }

    @Test
    public void validarTipoDocumentoExistenciaPreviaTest() {

        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existe(anyString())).thenReturn(true);
        ServicioCrearTipoDocumento servicioCrearTipoDocumento = new ServicioCrearTipoDocumento(
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearTipoDocumento.ejecutar(tipoDocumento), ExcepcionDuplicidad.class,
                "El tipo de documento ya existe en el sistema");
    }

    @Test
    public void validarCreacionTipoDocumentoTest() {
        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();

        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existe(anyString())).thenReturn(false);
        Mockito.when(repositorioTipoDocumento.crear(tipoDocumento)).thenReturn(1L);
        ServicioCrearTipoDocumento servicioCrearTipoDocumento = new ServicioCrearTipoDocumento(
                repositorioTipoDocumento);
        // act
        Long idUsuario = servicioCrearTipoDocumento.ejecutar(tipoDocumento);

        // assert
        BasePrueba.assertEqualsObject(1L, idUsuario);
    }

}
