package com.ceiba.prestamo.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.Date;

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
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.ComandoPrestamoTestDataBuilder;
import com.ceiba.response.testdatabuildar.ComandoRespuestaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorPrestamo.class)
public class ComandoControladorPrestamoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception {
        // arrange
       
        ComandoCliente cliente = new ComandoCliente( "Sara Quintero", "Carrera 62 # 59 -38", "13345564",
                "sara@hotmail.com", "5989252", 1L);

        // act - assert
        ResultActions result = mocMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente))).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
        .andExpect(jsonPath("valor").isNotEmpty());
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamoTestDataBuilder().build();
        prestamo.setIdCliente(Long.parseLong(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
                ComandoRespuestaTestDataBuilder.class).getValor()));
        // act - assert
        mocMvc.perform(post("/prestamos").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prestamo))).andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    public void pagar() throws Exception {

        // arrange
        Long id = 1L;
        ComandoPrestamo prestamo = new ComandoPrestamo(id, new Date(2020 - 12 - 27), new Date(2021 - 01 - 10),
                new Date(2021 - 01 - 10), 1000000.0, 30000, 0.0, 0.0, 1030000, "P", 1L);

        // act - assert
        mocMvc.perform(put("/prestamos/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prestamo))).andExpect(status().isOk());
    }

}
