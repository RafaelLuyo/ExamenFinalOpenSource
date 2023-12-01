package com.isa.platform.u201919295.monitoring.infrastructure.persistence.jpa.repositories;



import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {
    Optional<Snapshot> findBySnapshotId(SnapshotId snapshotId);
    List<Snapshot> findByProductId(Long productId);
}
