package com.isa.platform.u201919295.inventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Brand(String brand) {

    public Brand() {
        this(null);
    }

    public Brand {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or blank");
        }
    }
}
