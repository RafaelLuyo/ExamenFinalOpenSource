package com.isa.platform.u201919295.inventory.domain.model.aggregates;


import com.isa.platform.u201919295.inventory.domain.model.valueobjects.Brand;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.Model;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Embedded
    private Brand brand;

    @Setter
    @Getter
    @Embedded
    private Model model;
    @Setter
    @Getter
    @Embedded
    private SerialNumber serialNumber;
    @Enumerated(EnumType.STRING)
    private MonitoringLevel monitoringLevel;
    public Product(String brand, String model, String serialNumber, MonitoringLevel monitoringLevel) {
        this.brand = new Brand(brand);
        this.model = new Model(model);
        this.serialNumber = new SerialNumber(serialNumber) ;
        this.monitoringLevel = monitoringLevel;
    }

    public Product() {
    }

}
