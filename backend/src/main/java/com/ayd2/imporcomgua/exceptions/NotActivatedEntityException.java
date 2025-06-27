package com.ayd2.imporcomgua.exceptions;

public class NotActivatedEntityException extends ServiceException{
    public NotActivatedEntityException() {
        super("La entidad no está activada");
    }
    public NotActivatedEntityException(String message) {
        super(message);
    }
}
