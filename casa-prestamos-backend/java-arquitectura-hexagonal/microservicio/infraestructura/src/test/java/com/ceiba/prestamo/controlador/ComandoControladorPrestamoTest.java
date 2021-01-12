package com.ceiba.prestamo.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;
import com.ceiba.cliente.comando.ComandoCliente;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Long idCliente = 30L;
        ComandoCliente cliente = new ComandoCliente(idCliente, "Sara Quintero", "Carrera 62 # 59 -38", "1023458874",
                "sara@hotmail.com", "5989252", 1L);

        // act - assert
        mocMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente))).andExpect(status().isOk())
                .andExpect(content().json("{'valor': 30}"));
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamo(200L, new Date(2020 - 12 - 27), new Date(2021 - 01 - 10),
                new Date(2021 - 01 - 10), 1000000.0, 0.0, 0.0, 0.0, 0.0, "D", 30L);

        // act - assert
        mocMvc.perform(post("/prestamos").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prestamo))).andExpect(status().isOk())
                .andExpect(content().json("{'valor': 200}"));
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
