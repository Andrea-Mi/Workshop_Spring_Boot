package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public PrestamoResponse calcularPrestamo(PrestamoRequest request) {
        BigDecimal monto = request.getMonto();
        BigDecimal tasaAnual = request.getTasaAnual();
        int meses = request.getMeses();

        // 1. Calcula la tasa mensual (Tasa anual / 12 / 100)
        // Dividimos entre 1200 para convertir porcentaje anual a mensual decimal
        BigDecimal tasaMensual = tasaAnual.divide(BigDecimal.valueOf(1200), 10, RoundingMode.HALF_UP);

        // 2. Fórmula de amortización francesa:
        // Cuota = P * [ r * (1 + r)^n ] / [ (1 + r)^n - 1 ]
        
        BigDecimal unoMasR = BigDecimal.ONE.add(tasaMensual);
        
        // (1 + r)^n
        BigDecimal factorPotencia = unoMasR.pow(meses);
        
        BigDecimal numerador = tasaMensual.multiply(factorPotencia);
        BigDecimal denominador = factorPotencia.subtract(BigDecimal.ONE);
        
        BigDecimal factor = numerador.divide(denominador, 10, RoundingMode.HALF_UP);
        
        BigDecimal cuotaMensual = monto.multiply(factor).setScale(2, RoundingMode.HALF_UP);
        
        // 3. Calcular totales
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(meses)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(monto).setScale(2, RoundingMode.HALF_UP);

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}