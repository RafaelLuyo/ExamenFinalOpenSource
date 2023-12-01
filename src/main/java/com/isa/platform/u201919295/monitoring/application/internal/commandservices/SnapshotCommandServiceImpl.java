package com.isa.platform.u201919295.monitoring.application.internal.commandservices;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
import com.isa.platform.u201919295.inventory.domain.services.ProductCommandService;
import com.isa.platform.u201919295.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.domain.model.commands.CreateSnapshotCommand;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotCommandService;
import com.isa.platform.u201919295.monitoring.infrastructure.persistence.jpa.repositories.SnapshotRepository;
import org.springframework.stereotype.Service;
@Service
public class SnapshotCommandServiceImpl implements SnapshotCommandService {
    private final SnapshotRepository snapshotRepository;
    private final ProductRepository productRepository;

    public SnapshotCommandServiceImpl(SnapshotRepository snapshotRepository, ProductRepository productRepository) {
        this.snapshotRepository = snapshotRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateSnapshotCommand command) {
        var snapshotId = new SnapshotId(command.snapshotId());
        snapshotRepository.findBySnapshotId(snapshotId).ifPresent(snapshot -> {
            throw new IllegalArgumentException("Snapshot with snapshotId " + command.snapshotId() + " already exists");
        });

        // Validar que el serial number pertenezca al producto creado
        var product = productRepository.findBySerialNumber(new SerialNumber(command.productSerialNumber()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found for serial number: " + command.productSerialNumber()));

        // Validar compatibilidad del nivel de monitoreo
        validateMonitoringLevelCompatibility(product, command);

        var snapshot = new Snapshot(command.snapshotId(), product, command.temperature(), command.energy(), command.leakage());
        snapshotRepository.save(snapshot);
        return snapshot.getId();
    }

    private void validateMonitoringLevelCompatibility(Product product, CreateSnapshotCommand command) {
        // Verificar si el producto tiene un nivel de monitoreo esencial
        if (product.getMonitoringLevel() == MonitoringLevel.ESSENTIAL_MONITORING) {
            // Si el nivel de monitoreo esencial, asegurarse de que no haya datos de monitoreo avanzado
            if (command.energy() != null || command.leakage() != null) {
                throw new IllegalArgumentException("Snapshot Data not compatible with product current Monitoring Level");
            }
        }}
}

