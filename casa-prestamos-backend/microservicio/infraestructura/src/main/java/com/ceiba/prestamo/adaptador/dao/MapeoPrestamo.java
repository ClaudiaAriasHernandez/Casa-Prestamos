package com.ceiba.prestamo.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.tipodocumento.modelo.dto.DtoTipoDocumento;

public class MapeoPrestamo implements RowMapper<DtoPrestamo>, MapperResult {

    @Override
    public DtoPrestamo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long idPrestamo = resultSet.getLong("idPrestamo");
        Date fechaSolicitud = resultSet.getDate("fecha_solicitud");
        Date fechaEstimadaPago = resultSet.getDate("fecha_estimada_pago");
        Date fechaPago = resultSet.getDate("fecha_pago");
        double valor = resultSet.getDouble("valor");
        double valorMora = resultSet.getDouble("valor_mora");
        double valorInteres = resultSet.getDouble("valor_interes");
        double valorRecargo = resultSet.getDouble("valor_recargo");
        double valorTotal = resultSet.getDouble("valor_total");
        String estado = resultSet.getString("estado");
        Long idClientePrestamo = resultSet.getLong("id_cliente");
        Long idTipoDocumento = resultSet.getLong("id_tipo_documento");
        Long idIdentificacion = resultSet.getLong("id_documento");
        String tipoIdentificacion = resultSet.getString("tipo_identificacion");
        String descripcion = resultSet.getString("descripcion");
        Long idCliente = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String direccion = resultSet.getString("direccion");
        String numeroDocumento = resultSet.getString("numero_documento");
        String telefono = resultSet.getString("telefono");
        String correo = resultSet.getString("correo");

        DtoTipoDocumento dtoTipoDocumento = new DtoTipoDocumento(idIdentificacion, tipoIdentificacion, descripcion);

        DtoCliente dtoCliente = new DtoCliente(idCliente, nombre, direccion, numeroDocumento, telefono, correo,
                idTipoDocumento, dtoTipoDocumento);

        return new DtoPrestamo(idPrestamo, fechaSolicitud, fechaEstimadaPago, fechaPago, valor, valorMora, valorInteres,
                valorRecargo, valorTotal, estado, idClientePrestamo, dtoCliente);
    }

}
