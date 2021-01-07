package com.ceiba.cliente.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.cliente.comando.ComandoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;

@Component
public class FabricaCliente {

    public Cliente crear(ComandoCliente comandoCliente) {
        return new Cliente(comandoCliente.getId(), comandoCliente.getNombre(), comandoCliente.getDireccion(),
                comandoCliente.getNumeroDocumento(), comandoCliente.getTelefono(), comandoCliente.getCorreo(),
                comandoCliente.getIdTipoDocumento()

        );

    }

}
