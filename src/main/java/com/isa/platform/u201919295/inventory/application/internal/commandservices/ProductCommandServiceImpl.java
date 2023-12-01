package com.isa.platform.u201919295.inventory.application.internal.commandservices;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
import com.isa.platform.u201919295.inventory.domain.services.ProductCommandService;
import com.isa.platform.u201919295.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        var serialnumber = new SerialNumber(command.serialNumber());
        productRepository.findBySerialNumber(serialnumber).map(profile -> {
            throw new IllegalArgumentException("Profile with serialNumber "+ command.serialNumber() + "already exists");
        });
        var product = new Product(command.brand(), command.model(), command.serialNumber(), command.monitoringLevel());
        productRepository.save(product);
        return product.getId();
    }
}
