package com.ceiba.tipodocumento.servicio;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionConstraint;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tipodocumento.modelo.entidad.TipoDocumento;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.TipoDocumentoTestDataBuilder;

public class ServicioEliminarTipoDocumentoTest {

    @Test
    public void validarTipoDocumentoNoExistePreviaTest() {

        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(false);
        ServicioEliminarTipoDocumento servicioEliminarTipoDocumento = new ServicioEliminarTipoDocumento(
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarTipoDocumento.ejecutar(tipoDocumento.getId()),
                ExcepcionDuplicidad.class, "El tipo de documento no existe en el sistema");
    }

    @Test
    public void validarTipoDocumentoConstraintPreviaTest() {

        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.doThrow(SQLIntegrityConstraintViolationException.class).when(repositorioTipoDocumento)
                .eliminar(Mockito.anyLong());
        ServicioEliminarTipoDocumento servicioEliminarTipoDocumento = new ServicioEliminarTipoDocumento(
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarTipoDocumento.ejecutar(tipoDocumento.getId()),
                ExcepcionConstraint.class,
                "No se puede eliminar el tipo de documento debido a que esta ligado a un cliente");
    }

    @Test
    public void validarTipoDocumentoConstraintViolationPreviaTest() {

        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);

        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        Mockito.doThrow(ExcepcionConstraint.class).when(repositorioTipoDocumento).eliminar(Mockito.anyLong());
        ServicioEliminarTipoDocumento servicioEliminarTipoDocumento = new ServicioEliminarTipoDocumento(
                repositorioTipoDocumento);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarTipoDocumento.ejecutar(tipoDocumento.getId()),
                ExcepcionConstraint.class,
                "No se puede eliminar el tipo de documento debido a que esta ligado a un cliente");
    }

    @Test
    public void validarEliminarTipoDocumentoTest() {
        // arrange
        TipoDocumento tipoDocumento = new TipoDocumentoTestDataBuilder().build();
        RepositorioTipoDocumento repositorioTipoDocumento = Mockito.mock(RepositorioTipoDocumento.class);
        Mockito.when(repositorioTipoDocumento.existeId(Mockito.anyLong())).thenReturn(true);
        ServicioEliminarTipoDocumento servicioEliminarTipoDocumento = new ServicioEliminarTipoDocumento(
                repositorioTipoDocumento);

        // act
        servicioEliminarTipoDocumento.ejecutar(tipoDocumento.getId());

        // assert
        Mockito.verify(repositorioTipoDocumento, Mockito.times(1)).eliminar(tipoDocumento.getId());
    }

}
