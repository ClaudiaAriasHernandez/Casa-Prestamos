package com.ceiba.prestamo.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;

@Component
public class DaoPrestamoMysql implements DaoPrestamo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "prestamo", value = "listar")
    private static String sqlListar;
    
    @SqlStatement(namespace = "prestamo", value = "listarByIdCliente")
    private static String sqlListarByIdClientePrestamo;

    public DaoPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPrestamo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
                new MapeoPrestamo());
    }

    @Override
    public DtoPrestamo listarPorIdCliente(Long id) {
        DtoPrestamo datosPrestamo = null;
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        List<DtoPrestamo> prestamo = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlListarByIdClientePrestamo, paramSource, new MapeoPrestamo());

        for (DtoPrestamo prestamoCliente : prestamo) {
            datosPrestamo = prestamoCliente;
        }
        return datosPrestamo;

    }

}
