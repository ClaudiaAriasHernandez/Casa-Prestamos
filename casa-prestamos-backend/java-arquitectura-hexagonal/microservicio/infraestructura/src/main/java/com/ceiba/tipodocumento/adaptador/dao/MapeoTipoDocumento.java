package com.ceiba.tipodocumento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

public class MapeoTipoDocumento implements RowMapper<DtoTipoDocumento>, MapperResult {

    @Override
    public DtoTipoDocumento mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String tipoIdentificacion = resultSet.getString("tipoIdentificacion");
        String descripcion = resultSet.getString("descripcion");
        String estado = resultSet.getString("estado");
        return new DtoTipoDocumento(id, tipoIdentificacion, descripcion, estado);
    }

}
