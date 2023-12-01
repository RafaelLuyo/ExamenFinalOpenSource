package com.isa.platform.u201919295.inventory.domain.model.commands;

import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;

public record CreateProductCommand(String brand, String model, String serialNumber, MonitoringLevel monitoringLevel) {
}
