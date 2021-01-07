package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.cliente.servicio.ServicioActualizarCliente;
import com.ceiba.cliente.servicio.ServicioCrearCliente;
import com.ceiba.cliente.servicio.ServicioEliminarCliente;
import com.ceiba.tipodocumento.puerto.repositorio.RepositorioTipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioActualizarTipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioCrearTipoDocumento;
import com.ceiba.tipodocumento.servicio.ServicioEliminarTipoDocumento;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioCrearCliente servicioCrearCliente(RepositorioCliente repositorioCliente,
            RepositorioTipoDocumento repositorioTipoDocumento) {
        return new ServicioCrearCliente(repositorioCliente, repositorioTipoDocumento);
    }

    @Bean
    public ServicioEliminarCliente servicioEliminarCliente(RepositorioCliente repositorioCliente) {
        return new ServicioEliminarCliente(repositorioCliente);
    }

    @Bean
    public ServicioActualizarCliente servicioActualizarCliente(RepositorioCliente repositorioCliente,
            RepositorioTipoDocumento repositorioTipoDocumento) {
        return new ServicioActualizarCliente(repositorioCliente, repositorioTipoDocumento);
    }

    @Bean
    public ServicioCrearTipoDocumento servicioCrearTipoDocumento(RepositorioTipoDocumento repositorioTipoDocumento) {
        return new ServicioCrearTipoDocumento(repositorioTipoDocumento);
    }

    @Bean
    public ServicioEliminarTipoDocumento servicioEliminarTipoDocumento(
            RepositorioTipoDocumento repositorioTipoDocumento) {
        return new ServicioEliminarTipoDocumento(repositorioTipoDocumento);
    }

    @Bean
    public ServicioActualizarTipoDocumento servicioActualizarTipoDocumento(
            RepositorioTipoDocumento repositorioTipoDocumento) {
        return new ServicioActualizarTipoDocumento(repositorioTipoDocumento);
    }

}
