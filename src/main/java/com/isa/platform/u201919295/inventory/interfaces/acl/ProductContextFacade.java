package com.isa.platform.u201919295.inventory.interfaces.acl;


import com.isa.platform.u201919295.inventory.domain.model.commands.CreateProductCommand;
import com.isa.platform.u201919295.inventory.domain.model.queries.GetProductBySerialNumberQuery;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.MonitoringLevel;
import com.isa.platform.u201919295.inventory.domain.model.valueobjects.SerialNumber;
import com.isa.platform.u201919295.inventory.domain.services.ProductCommandService;
import com.isa.platform.u201919295.inventory.domain.services.ProductQueryService;

//capa de anticorrupción para comunicarse con otros bounden context
public class ProductContextFacade {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductContextFacade(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    public Long createProduct(String brand, String model, String serialNumber, MonitoringLevel monitoringLevel){
        var createproductCommand = new CreateProductCommand(brand,model,serialNumber,monitoringLevel);
        return productCommandService.handle(createproductCommand);
    }

    public Long getProductBySerialNumber(String serialNumber){
        var getProductBySerialNumber = (new GetProductBySerialNumberQuery(new SerialNumber(serialNumber)));
        var product = productQueryService.handle(getProductBySerialNumber);
        if(product.isEmpty())return 0L;
        return product.get().getId();
    }
}
