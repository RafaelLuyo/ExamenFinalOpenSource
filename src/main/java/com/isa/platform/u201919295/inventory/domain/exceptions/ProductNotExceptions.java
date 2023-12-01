package com.isa.platform.u201919295.inventory.domain.exceptions;

public class ProductNotExceptions extends RuntimeException{
     public ProductNotExceptions(){
        super("Products not found");
    }
}
