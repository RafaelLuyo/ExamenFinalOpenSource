package com.isa.platform.u201919295.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SnapshotId(String snapshotId) {
    public SnapshotId() {
        this(null);
    }

    public SnapshotId {
        if (snapshotId == null || snapshotId.isBlank()) {
            throw new IllegalArgumentException("SnapshotId cannot be null or blank");
        }
    }

}
