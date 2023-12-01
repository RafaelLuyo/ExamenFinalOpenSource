package com.isa.platform.u201919295.inventory.interfaces.rest.transform;


import com.isa.platform.u201919295.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        MonitoringLevel monitoringLevel = MonitoringLevel.fromValue(resource.monitoringLevel());


        return new CreateProductCommand(
                resource.brand(),
                resource.model(),
                resource.serialNumber(),
                monitoringLevel
        );
    }
}
