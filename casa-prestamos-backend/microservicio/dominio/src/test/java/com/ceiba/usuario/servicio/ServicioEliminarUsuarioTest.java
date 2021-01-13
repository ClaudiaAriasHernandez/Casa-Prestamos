package com.ceiba.usuario.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;

public class ServicioEliminarUsuarioTest {

    @Test
    public void validarEliminarUsuarioTest() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().build();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);

        ServicioEliminarUsuario servicioEliminarUsuario = new ServicioEliminarUsuario(repositorioUsuario);

        // act
        servicioEliminarUsuario.ejecutar(usuario.getId());

        // assert
        Mockito.verify(repositorioUsuario, Mockito.times(1)).eliminar(usuario.getId());
    }
}
