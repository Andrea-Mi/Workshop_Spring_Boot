package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulaciones")
@Tag(name = "Simulaciones", description = "API para simulaciones de préstamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/prestamo")
    @Operation(summary = "Simular préstamo", 
               description = "Calcula la cuota mensual, interés total y total a pagar de un préstamo")
    public ResponseEntity<PrestamoResponse> simularPrestamo(
            @Valid @RequestBody PrestamoRequest request) {
        
        PrestamoResponse response = prestamoService.calcularPrestamo(request);
        return ResponseEntity.ok(response);
    }
}