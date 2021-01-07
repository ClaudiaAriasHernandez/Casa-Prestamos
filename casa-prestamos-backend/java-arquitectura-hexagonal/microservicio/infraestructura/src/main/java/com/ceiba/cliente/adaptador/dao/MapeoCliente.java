package com.ceiba.cliente.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoCliente implements RowMapper<DtoCliente>, MapperResult {

    @Override
    public DtoCliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String direccion = resultSet.getString("direccion");
        String numeroDocumento = resultSet.getString("numero_documento");
        String telefono = resultSet.getString("telefono");
        String correo = resultSet.getString("correo");
        String idTipoDocumento = resultSet.getString("id_tipo_documento");
        return new DtoCliente(id, nombre, direccion, numeroDocumento, telefono, correo, idTipoDocumento);
    }

}
