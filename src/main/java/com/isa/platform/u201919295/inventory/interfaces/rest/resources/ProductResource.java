package com.isa.platform.u201919295.inventory.interfaces.rest.resources;

import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;

public record ProductResource(String brand, String model, String serialNumber, MonitoringLevel monitoringLevel ) {
}
