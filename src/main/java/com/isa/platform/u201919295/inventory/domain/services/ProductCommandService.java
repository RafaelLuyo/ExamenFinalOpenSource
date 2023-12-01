package com.isa.platform.u201919295.inventory.domain.services;


import com.isa.platform.u201919295.inventory.domain.model.commands.CreateProductCommand;


public interface ProductCommandService {
    Long handle(CreateProductCommand command);
}
