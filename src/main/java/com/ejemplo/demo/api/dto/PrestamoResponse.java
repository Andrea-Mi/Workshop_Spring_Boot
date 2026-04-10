package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;

public class PrestamoResponse {
    private BigDecimal cuotaMensual;
    private BigDecimal interesTotal;
    private BigDecimal totalPagar;
    
    // Constructor vacío (requerido por Jackson)
    public PrestamoResponse() {
    }
    
    // Constructor con parámetros
    public PrestamoResponse(BigDecimal cuotaMensual, BigDecimal interesTotal, BigDecimal totalPagar) {
        this.cuotaMensual = cuotaMensual;
        this.interesTotal = interesTotal;
        this.totalPagar = totalPagar;
    }
    
    // Getters y Setters
    public BigDecimal getCuotaMensual() {
        return cuotaMensual;
    }
    
    public void setCuotaMensual(BigDecimal cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }
    
    public BigDecimal getInteresTotal() {
        return interesTotal;
    }
    
    public void setInteresTotal(BigDecimal interesTotal) {
        this.interesTotal = interesTotal;
    }
    
    public BigDecimal getTotalPagar() {
        return totalPagar;
    }
    
    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }
}