package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    String normalizarNombre(String nombre) {
        // Validar null o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        
        // Quitar espacios al inicio y final
        String nombreLimpio = nombre.trim();
        
        // Validar que no contenga números (opcional pero recomendado)
        if (nombreLimpio.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no puede contener números");
        }
        
        // Primera letra mayúscula, resto minúsculas
        return nombreLimpio.substring(0, 1).toUpperCase() 
             + nombreLimpio.substring(1).toLowerCase();
    }
}
