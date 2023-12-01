package com.isa.platform.u201919295.inventory.interfaces.rest;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetAllProductsQuery;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u201919295.inventory.domain.services.ProductCommandService;
import com.isa.platform.u201919295.inventory.domain.services.ProductQueryService;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.CreateProductResource;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.ProductResource;
import com.isa.platform.u201919295.inventory.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.isa.platform.u201919295.inventory.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController
 * <p>
 *     Product Management Endpoints
 *     <ul>
 *         <li>Create a new Product</li>
 *         <li>Get Product by Identifier</li>
 *         <li>Get all Products</li>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Product Management Endpoints")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    /**
     * Create a new Product
     * @param resource Create Product Resource including the profile data
     * @return Product Resource if created, otherwise 400
     * @see ProductResource
     * @see CreateProductResource
     */
    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource resource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var productId = productCommandService.handle(createProductCommand);
        if (productId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    /**
     * Get Product by Identifier
     * @param productId the given Product Identifier
     * @return Product Resource if found, otherwise 404
     * @see ProductResource
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    /**
     * Get all Products
     * @return List of Product Resources currently available
     * @see ProductResource
     */
    @GetMapping
    public ResponseEntity<List<ProductResource>>  getAllProducts() {
        var getAllProductsQuery = new GetAllProductsQuery();
        var products = productQueryService.handle(getAllProductsQuery);
        var productResources = products.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResources);
    }
}
