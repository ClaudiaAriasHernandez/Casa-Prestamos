package com.ceiba.prestamo.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ceiba.ApplicationMock;
import com.ceiba.cliente.comando.ComandoCliente;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.servicio.testdatabuilder.ComandoPrestamoTestDataBuilder;
import com.ceiba.response.testdatabuildar.ComandoRespuestaTestDataBuilder;
import com.ceiba.tipodocumento.controlador.ConsultaControladorTipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorTipoDocumento.class)
public class ConsultaControladorPrestamoTest {

    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/prestamos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void listarPorIdCliente() throws Exception {
        
        // arrange        
        ComandoCliente cliente = new ComandoCliente( "David Quintero", "Carrera 64 # 58 -38", "103764534",
                "david@hotmail.com", "5989352", 1L);

        // act - assert
        ResultActions result = mocMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("valor").isNotEmpty());
        
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamoTestDataBuilder().build();
        
        prestamo.setIdCliente(Long.parseLong(new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
                ComandoRespuestaTestDataBuilder.class).getValor()));
        // act - assert
        ResultActions resultPrestamo=  mocMvc.perform(post("/prestamos").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("valor").isNotEmpty());
        // arrange
     
        // act - assert
      
        mocMvc.perform(get("/prestamos/{id}", Long.parseLong(new Gson().fromJson(resultPrestamo.andReturn().getResponse().getContentAsString(),
                ComandoRespuestaTestDataBuilder.class).getValor()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
