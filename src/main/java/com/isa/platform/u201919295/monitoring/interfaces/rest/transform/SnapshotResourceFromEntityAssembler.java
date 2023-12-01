package com.isa.platform.u201919295.monitoring.interfaces.rest.transform;

import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.interfaces.rest.resources.SnapshotResource;

public class SnapshotResourceFromEntityAssembler {

    public static SnapshotResource toResourceFromEntity(Snapshot entity) {
        if (entity == null) {
            return null;
        }

        String snapshotId = entity.getSnapshotId() != null ? entity.getSnapshotId().snapshotId() : null;
        String productSerialNumber = entity.getProduct() != null ? entity.getProduct().getSerialNumber().serialNumber() : null;
        Double temperature = entity.getTemperature() != null ? entity.getTemperature().temperature() : null;
        Double energy = entity.getEnergy() != null ? entity.getEnergy().energy() : null;
        Integer leakage = entity.getLeakage() != null ? entity.getLeakage().leakage() : null;

        return new SnapshotResource(snapshotId, productSerialNumber, temperature, energy, leakage);
    }
}
