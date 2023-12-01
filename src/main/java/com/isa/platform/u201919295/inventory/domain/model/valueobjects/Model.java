package com.isa.platform.u201919295.inventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Model(String model) {

    public Model() {
        this(null);
    }

    public Model {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null or blank");
        }
    }
}
