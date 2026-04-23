package com.stcakyforge.matchpoint.Exception;

public class InvalidFormatException extends RuntimeException {

    public InvalidFormatException(){
        super("Bad Request");
    }

    public InvalidFormatException(String message){
        super(message);
    }
}
