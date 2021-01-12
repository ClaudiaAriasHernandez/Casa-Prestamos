package com.ceiba.prestamo.adaptador.repositorio;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.prestamo.adaptador.dao.MapeoPrestamo;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

@Repository
public class RepositorioPrestamoMysql implements RepositorioPrestamo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "prestamo", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "prestamo", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "prestamo", value = "existeId")
    private static String sqlExisteId;

    @SqlStatement(namespace = "prestamo", value = "existeIdCliente")
    private static String sqlExisteIdCliente;
    

    @SqlStatement(namespace = "prestamo", value = "listarByIdCliente")
    private static String sqlListarByIdCliente;
    
    @SqlStatement(namespace = "prestamo", value = "existePrestamoActivo")
    private static String sqlExistePrestamoActivo;

    public RepositorioPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Prestamo prestamo) {
        //transformacion fecha        
        
        return this.customNamedParameterJdbcTemplate.crear(prestamo, sqlCrear);
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        this.customNamedParameterJdbcTemplate.actualizar(prestamo, sqlActualizar);
    }

    @Override
    public boolean existeId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteId,
                paramSource, Boolean.class);
    }

    @Override
    public boolean existePrestamoActivo(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCliente", idCliente);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePrestamoActivo,
                paramSource, Boolean.class);
    }
    
    @Override
    public boolean existeIdCliente(Long idCliente) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idCliente", idCliente);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteIdCliente,
                paramSource, Boolean.class);
    }
    
    @Override
    public List<DtoPrestamo> listarPorIdCliente(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarByIdCliente,
                paramSource, new MapeoPrestamo());
    }
}
