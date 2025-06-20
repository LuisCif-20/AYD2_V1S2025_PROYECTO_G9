package com.ayd2.imporcomgua.exceptions;

public class NoStockException extends ServiceException {
    public NoStockException() {
        super("No hay stock disponible para el producto solicitado");
    }
    
    public NoStockException(String productCode) {
        super(String.format("No hay stock disponible para el producto con code: %s", productCode));
    }
}