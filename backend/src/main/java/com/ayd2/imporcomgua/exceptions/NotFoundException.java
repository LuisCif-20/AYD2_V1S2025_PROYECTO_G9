package com.ayd2.imporcomgua.exceptions;

public class NotFoundException extends ServiceException{
    public NotFoundException(){}
    
    public NotFoundException(String message){
        super(message);
    }
}
