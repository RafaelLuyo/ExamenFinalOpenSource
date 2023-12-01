package com.isa.platform.u201919295.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Energy(double energy) {
    public Energy() {
        this(0.0);
    }


}
