package com.isa.platform.u201919295.inventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


public enum MonitoringLevel {
    ESSENTIAL_MONITORING,
    ADVANCE_MONITORING;
    public static MonitoringLevel fromValue(String value) {
        switch (value.toUpperCase()) {
            case "ESSENTIAL_MONITORING":
                return ESSENTIAL_MONITORING;
            case "ADVANCE_MONITORING":
                return ADVANCE_MONITORING;
            default:
                throw new IllegalArgumentException("Invalid monitoring level: " + value);
        }
    }
}