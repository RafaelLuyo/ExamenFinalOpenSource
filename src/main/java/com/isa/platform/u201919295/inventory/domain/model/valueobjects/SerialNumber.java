package com.isa.platform.u201919295.inventory.domain.model.valueobjects;




public record SerialNumber(String serialNumber) {

    public SerialNumber() {
        this(null);
    }

    public SerialNumber {
        if (serialNumber == null || serialNumber.isBlank()) {
            throw new IllegalArgumentException("Serial Number cannot be null or blank");
        }
    }
}
