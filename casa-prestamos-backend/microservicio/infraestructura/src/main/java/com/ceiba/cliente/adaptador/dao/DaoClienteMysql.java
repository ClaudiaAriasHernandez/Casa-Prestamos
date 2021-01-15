package com.ceiba.cliente.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoClienteMysql implements DaoCliente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cliente", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "cliente", value = "consultarCliente")
    private static String sqlConsultarCliente;

    public DaoClienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCliente> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
                new MapeoCliente());
    }

    @Override
    public DtoCliente consultarCliente(String tipoIdentificacion, String numeroDocumento) {
        DtoCliente datosCliente = null;
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("tipoIdentificacion", tipoIdentificacion);
        paramSource.addValue("numeroDocumento", numeroDocumento);
        List<DtoCliente> cliente = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlConsultarCliente, paramSource, new MapeoCliente());

        for (DtoCliente clienteInfo : cliente) {
            datosCliente = clienteInfo;
        }
        return datosCliente;
    }
}
