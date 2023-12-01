package com.isa.platform.u201919295.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Temperature(double temperature) {


    public Temperature() {
        this(0.0);
    }


    public Temperature  {
        if (temperature < -273.15) {
            throw new IllegalArgumentException("El valor de temperatura debe ser mayor o igual a -273.15 (cero absoluto).");
        }
    }
}
