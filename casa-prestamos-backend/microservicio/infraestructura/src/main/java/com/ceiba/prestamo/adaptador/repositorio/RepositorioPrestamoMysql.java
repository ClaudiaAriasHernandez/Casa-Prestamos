package com.ceiba.prestamo.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

@Repository
public class RepositorioPrestamoMysql implements RepositorioPrestamo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "prestamo", value = "crear")
    private static String sqlCrearPrestamo;

    @SqlStatement(namespace = "prestamo", value = "actualizar")
    private static String sqlActualizarPrestamo;

    @SqlStatement(namespace = "prestamo", value = "existeId")
    private static String sqlExisteIdPrestamo;

    @SqlStatement(namespace = "prestamo", value = "existePrestamoActivo")
    private static String sqlExistePrestamoActivoPrestamo;

    public RepositorioPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Prestamo prestamo) {
        // transformacion fecha

        return this.customNamedParameterJdbcTemplate.crear(prestamo, sqlCrearPrestamo);
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        this.customNamedParameterJdbcTemplate.actualizar(prestamo, sqlActualizarPrestamo);
    }

    @Override
    public boolean existeId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteIdPrestamo,
                paramSource, Boolean.class);
    }

    @Override
    public boolean existePrestamoActivo(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCliente", idCliente);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePrestamoActivoPrestamo, paramSource, Boolean.class);
    }

}
