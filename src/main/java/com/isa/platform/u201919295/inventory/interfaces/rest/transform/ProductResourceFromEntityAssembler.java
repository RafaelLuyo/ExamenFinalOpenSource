package com.isa.platform.u201919295.inventory.interfaces.rest.transform;


import com.isa.platform.u201919295.inventory.domain.model.aggregates.Product;
import com.isa.platform.u201919295.inventory.interfaces.rest.resources.ProductResource;
import com.isa.platform.u201919295.monitoring.domain.model.aggregates.Snapshot;
import com.isa.platform.u201919295.monitoring.interfaces.rest.resources.SnapshotResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(entity.getBrand().brand(), entity.getModel().model(), entity.getSerialNumber().serialNumber(), entity.getMonitoringLevel());
    }
}
