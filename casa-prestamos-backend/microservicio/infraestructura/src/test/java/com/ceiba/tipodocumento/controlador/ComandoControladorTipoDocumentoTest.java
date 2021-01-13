package com.ceiba.tipodocumento.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;
import com.ceiba.tipodocumento.comando.ComandoTipoDocumento;
import com.ceiba.tipodocumento.servicio.testdatabuilder.ComandoTipoDocumentoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorTipoDocumento.class)
public class ComandoControladorTipoDocumentoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception {
        // arrange
        ComandoTipoDocumento tipoDocumento = new ComandoTipoDocumentoTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/tipodocumentos").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoDocumento))).andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    public void actualizar() throws Exception {
        // arrange
        Long id = 1L;
        ComandoTipoDocumento tipoDocumento = new ComandoTipoDocumento(id, "CC", "Cedúla de Ciudadania");

        // act - assert
        mocMvc.perform(put("/tipodocumentos/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoDocumento))).andExpect(status().isOk());
    }

    @Test
    public void eliminar() throws Exception {
        // arrange
        Long id = 2L;

        // act - assert
        mocMvc.perform(delete("/tipodocumentos/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
