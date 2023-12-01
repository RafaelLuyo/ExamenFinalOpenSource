package com.isa.platform.u201919295.monitoring.application.internal.queryservices;


import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotByIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotBySnapshotIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotsByProductIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotQueryService;
import com.isa.platform.u201919295.monitoring.infrastructure.persistence.jpa.repositories.SnapshotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnapshotQueryServiceImpl implements SnapshotQueryService {
    private final SnapshotRepository snapshotRepository;

    public SnapshotQueryServiceImpl(SnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public Optional<Snapshot> handle(GetSnapshotBySnapshotIdQuery query) {
        SnapshotId snapshotId = new SnapshotId(query.snapshotId().snapshotId());
        return snapshotRepository.findBySnapshotId(snapshotId);
    }

    @Override
    public Optional<Snapshot> handle(GetSnapshotByIdQuery query) {
        return snapshotRepository.findById(query.SnapshotId());
    }
    @Override
    public List<Snapshot> handle(GetSnapshotsByProductIdQuery query) {
        return snapshotRepository.findByProductId(query.productId());
    }
    @Override
    public List<Snapshot> handle(GetAllSnapshotsQuery query) {
        return snapshotRepository.findAll();
    }
}
