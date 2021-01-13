package com.ceiba.cliente.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ceiba.ApplicationMock;
import com.ceiba.cliente.comando.ComandoCliente;
import com.ceiba.cliente.servicio.testdatabuilder.ComandoClienteTestDataBuilder;
import com.ceiba.response.testdatabuildar.ComandoRespuestaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorCliente.class)
public class ComandoControladorClienteTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception {
        // arrange
        ComandoCliente cliente = new ComandoClienteTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente))).andExpect(status().isOk())
                .andExpect(jsonPath("valor").isNotEmpty());
    }

    @Test
    public void actualizar() throws Exception {
        // arrange
        Long id = 1L;
        ComandoCliente cliente = new ComandoCliente(id, "Claudia Arias", "Calle 62 # 59 -38", "1037641034",
                "claudia-ah@hotmail.com", "5982252", 1L);

        // act - assert
        mocMvc.perform(put("/clientes/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente))).andExpect(status().isOk());
    }

    @Test
    public void eliminar() throws Exception {
        // arrange

        ComandoCliente cliente = new ComandoClienteTestDataBuilder().build();

        cliente.setNumeroDocumento("12345423");

        // act - assert
        ResultActions result = mocMvc
                .perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk()).andExpect(jsonPath("valor").isNotEmpty());

        //
        // act - assert
        mocMvc.perform(delete("/clientes/{id}",
                new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
                        ComandoRespuestaTestDataBuilder.class).getValor()).contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
