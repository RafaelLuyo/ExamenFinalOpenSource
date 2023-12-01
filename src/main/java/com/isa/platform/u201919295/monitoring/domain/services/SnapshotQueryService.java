package com.isa.platform.u201919295.monitoring.domain.services;

import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotByIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotBySnapshotIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotsByProductIdQuery;

import java.util.List;
import java.util.Optional;

public interface SnapshotQueryService {
    Optional<Snapshot> handle(GetSnapshotBySnapshotIdQuery query);
    Optional<Snapshot> handle(GetSnapshotByIdQuery query);
    List<Snapshot> handle(GetAllSnapshotsQuery query);
    List<Snapshot> handle(GetSnapshotsByProductIdQuery query);
}
