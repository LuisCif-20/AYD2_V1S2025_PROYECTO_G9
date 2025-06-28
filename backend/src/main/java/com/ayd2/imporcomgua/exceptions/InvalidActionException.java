package com.ayd2.imporcomgua.exceptions;

public class InvalidActionException extends ServiceException {

    public InvalidActionException(){}
    
    public InvalidActionException(String message){
        super(message);
    }
    
}
