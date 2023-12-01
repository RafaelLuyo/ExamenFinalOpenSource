    package com.isa.platform.u201919295.monitoring.domain.model.aggregates;


    import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
    import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
    import com.isa.platform.u201919295.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
    import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.Energy;
    import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.Leakage;
    import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.SnapshotId;
    import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.Temperature;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import org.springframework.data.domain.AbstractAggregateRoot;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;
    @Setter
    @Getter
    @EntityListeners(AuditingEntityListener.class)
    @Entity
    public class Snapshot extends AbstractAggregateRoot<Snapshot> {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Embedded
        private SnapshotId snapshotId;

        @ManyToOne
        @Getter
        @JoinColumn(name = "productSerialNumber")
        private Product product;

        @Embedded
        private Temperature temperature;

        @Embedded
        private Energy energy;

        @Embedded
        @Getter
        private Leakage leakage;

        public Snapshot(String snapshotId, Product product, Double temperature, Double energy, Integer leakage) {
            this.snapshotId = new SnapshotId(snapshotId);
            this.product = product;
            this.temperature = new Temperature(temperature);

            if (energy != null) {
                this.energy = new Energy(energy);
            }

            if (leakage != null) {
                this.leakage = new Leakage(leakage);
            }
        }

        public Snapshot() {
        }

        private Product getProductBySerialNumber(String serialNumber, ProductRepository productRepository) {
            Product product = productRepository.findBySerialNumber(new SerialNumber(serialNumber))
                    .orElse(null);

            if (product == null) {
                throw new IllegalArgumentException("Product not found for serial number: " + serialNumber);
            }

            return product;
        }
    }
