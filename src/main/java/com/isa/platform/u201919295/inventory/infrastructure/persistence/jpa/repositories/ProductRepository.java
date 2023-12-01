package com.isa.platform.u201919295.inventory.infrastructure.persistence.jpa.repositories;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySerialNumber(SerialNumber serialNumber);
}
