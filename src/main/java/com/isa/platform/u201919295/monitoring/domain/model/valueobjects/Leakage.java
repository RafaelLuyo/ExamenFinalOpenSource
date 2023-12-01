package com.isa.platform.u201919295.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Leakage(Integer leakage) {

    public Leakage() {
        this(0);
    }
    public Leakage {
        if (leakage != null && leakage != 0 && leakage != 1) {
            throw new IllegalArgumentException("Leakage value must be 0 or 1");
        }
    }

}