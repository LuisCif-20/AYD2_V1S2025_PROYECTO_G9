package com.ayd2.imporcomgua.exceptions;

public class ServiceException extends Exception{
    public ServiceException(){}
    
    public ServiceException(String message){
        super(message);
    }
}
