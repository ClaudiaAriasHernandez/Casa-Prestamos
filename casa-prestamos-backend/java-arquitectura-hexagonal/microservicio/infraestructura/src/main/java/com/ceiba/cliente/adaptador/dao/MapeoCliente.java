package com.ceiba.cliente.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

public class MapeoCliente implements RowMapper<DtoCliente>, MapperResult {

    @Override
    public DtoCliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String direccion = resultSet.getString("direccion");
        String numeroDocumento = resultSet.getString("numero_documento");
        String telefono = resultSet.getString("telefono");
        String correo = resultSet.getString("correo");
        Long idTipoDocumento = resultSet.getLong("id_tipo_documento");
        Long idIdentificacion = resultSet.getLong("id_documento");
        String tipoIdentificacion = resultSet.getString("tipo_identificacion");
        String descripcion = resultSet.getString("descripcion");

        DtoTipoDocumento dtoTipoDocumento = new DtoTipoDocumento(idIdentificacion, tipoIdentificacion, descripcion);
        return new DtoCliente(id, nombre, direccion, numeroDocumento, telefono, correo, idTipoDocumento,
                dtoTipoDocumento);
    }

}
