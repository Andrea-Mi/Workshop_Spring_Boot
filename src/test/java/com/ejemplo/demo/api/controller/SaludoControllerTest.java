package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;// <--- AGREGAR ESTO
import org.springframework.http.MediaType;              // <--- AGREGAR ESTO
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // <--- AGREGAR ESTO
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mockea el servicio para que el test no falle al buscar la clase real
    @MockBean
    private com.ejemplo.demo.domain.service.SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    // TEST 1: GET 
    @Test
    @DisplayName("GET /saludos debe retornar saludo")
    void testGetSaludo() throws Exception {
        // Configura el mock para que devuelva una respuesta
        when(saludoService.crearSaludo(anyString()))
            .thenReturn(new com.ejemplo.demo.api.dto.SaludoResponse(
                "Hola, Ana. Bienvenido a Spring Boot 3!",
                java.time.Instant.now()
            ));

        mockMvc.perform(get("/api/v1/saludos")
                .param("nombre", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Ana. Bienvenido a Spring Boot 3!"));
    }

    // TEST 2: POST con la validacion 
    @Test
    @DisplayName("POST /saludos con nombre vacio debe dar 400")
    void testPostSaludoInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/saludos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}
