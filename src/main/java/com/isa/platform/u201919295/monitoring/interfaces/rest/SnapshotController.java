package com.isa.platform.u201919295.monitoring.interfaces.rest;


import com.isa.platform.u201919295.inventory.domain.model.queries.GetAllProductsQuery;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetProductByIdQuery;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;
import com.isa.platform.u201919295.inventory.domain.services.ProductCommandService;
import com.isa.platform.u201919295.inventory.domain.services.ProductQueryService;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.CreateProductResource;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.ProductResource;
import com.isa.platform.u201919295.inventory.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.isa.platform.u201919295.inventory.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetAllSnapshotsQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotByIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.queries.GetSnapshotsByProductIdQuery;
import com.isa.platform.u201919295.monitoring.domain.model.valueobjects.Leakage;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotCommandService;
import com.isa.platform.u201919295.monitoring.domain.services.SnapshotQueryService;
import com.isa.platform.u201919295.monitoring.interfaces.rest.resources.CreateSnapshotResource;
import com.isa.platform.u201919295.monitoring.interfaces.rest.resources.SnapshotResource;
import com.isa.platform.u201919295.monitoring.interfaces.rest.transform.CreateSnapshotCommandFromResourceAssembler;
import com.isa.platform.u201919295.monitoring.interfaces.rest.transform.SnapshotResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SnapshotController
 * <p>
 *     Snapshot Management Endpoints
 *     <ul>
 *         <li>Create a new Snapshot</li>
 *         <li>Get Product by Snapshot</li>
 *         <li>Get all Snapshots</li>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/products/{productId}/snapshots", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Snapshot", description = "Product Snapshot Management Endpoints")
public class SnapshotController {
    private final SnapshotCommandService snapshotCommandService;
    private final SnapshotQueryService snapshotQueryService;
    private final ProductQueryService productQueryService;

    public SnapshotController(SnapshotCommandService snapshotCommandService, SnapshotQueryService snapshotQueryService, ProductQueryService productQueryService) {
        this.snapshotCommandService = snapshotCommandService;
        this.snapshotQueryService = snapshotQueryService;
        this.productQueryService = productQueryService;
    }

    /**
     * Create a new Snapshot
     * @param resource Create Snapshot Resource including the profile data
     * @return Snapshot Resource if created, otherwise 400
     * @see ProductResource
     * @see CreateProductResource
     */
    @PostMapping
    public ResponseEntity<SnapshotResource> addSnapshot(@PathVariable Long productId, @RequestBody CreateSnapshotResource resource) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var product = productQueryService.handle(getProductByIdQuery);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (product.get().getMonitoringLevel() == MonitoringLevel.ESSENTIAL_MONITORING
                && resource.leakage() != null
                && (resource.leakage() == 0 || resource.leakage() == 1)) {
            SnapshotResource snapshotResource = new SnapshotResource(
                    "Snapshot Data not compatible with product current Monitoring Level",
                    null,
                    null,
                    null,
                    null
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(snapshotResource);
        }
        var snapshotCommand = CreateSnapshotCommandFromResourceAssembler.toCommandFromResourceAndProduct(resource, product.get());
        var snapshotId = snapshotCommandService.handle(snapshotCommand);

        if (snapshotId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        // Modificación: Obtener el Snapshot por su ID después de crearlo
        var getSnapshotByIdQuery = new GetSnapshotByIdQuery(snapshotId);
        var snapshot = snapshotQueryService.handle(getSnapshotByIdQuery);

        if (snapshot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var snapshotResource = SnapshotResourceFromEntityAssembler.toResourceFromEntity(snapshot.get());
        return new ResponseEntity<>(snapshotResource, HttpStatus.CREATED);
    }

    /**
     * Get all Snapshots for a Product
     * @return List of Snapshot Resources for the specified Product
     * @see SnapshotResource
     */
    @GetMapping
    public ResponseEntity<List<SnapshotResource>> getAllSnapshots(@PathVariable Long productId) {
        var getSnapshotsByProductIdQuery = new GetSnapshotsByProductIdQuery(productId);
        var snapshots = snapshotQueryService.handle(getSnapshotsByProductIdQuery);

        var snapshotResources = snapshots.stream()
                .map(SnapshotResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(snapshotResources);
    }
}