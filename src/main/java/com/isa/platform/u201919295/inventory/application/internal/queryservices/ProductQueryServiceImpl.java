package com.isa.platform.u201919295.inventory.application.internal.queryservices;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetAllProductsQuery;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetProductBySerialNumberQuery;
import com.isa.platform.u201919295.inventory.domain.services.ProductQueryService;
import com.isa.platform.u201919295.inventory.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductBySerialNumberQuery query) {
        return productRepository.findBySerialNumber(query.serialNumber());
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.ProductId());
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }
}
