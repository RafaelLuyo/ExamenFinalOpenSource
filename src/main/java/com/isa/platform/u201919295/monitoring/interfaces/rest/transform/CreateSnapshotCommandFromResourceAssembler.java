package com.isa.platform.u201919295.monitoring.interfaces.rest.transform;

import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.monitoring.domain.model.commands.CreateSnapshotCommand;
import com.isa.platform.u201919295.monitoring.interfaces.rest.resources.CreateSnapshotResource;

public class CreateSnapshotCommandFromResourceAssembler {
    public static CreateSnapshotCommand toCommandFromResource(CreateSnapshotResource resource) {
        return new CreateSnapshotCommand(
                resource.snapshotId(),
                resource.productSerialNumber(),
                resource.temperature(),
                resource.energy(),
                resource.leakage()
        );
    }

    public static CreateSnapshotCommand toCommandFromResourceAndProduct(CreateSnapshotResource resource, Product product) {
        return new CreateSnapshotCommand(
                resource.snapshotId(),
                product.getSerialNumber().serialNumber(),
                resource.temperature(),
                resource.energy(),
                resource.leakage()
        );
    }
}
