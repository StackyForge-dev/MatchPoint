package com.stcakyforge.matchpoint.Exception;

public class ConflictException extends RuntimeException {

    public ConflictException (){
        super("Usuário já cadastrado, tente novamente.");
    }

    public ConflictException(String message) {
        super(message);
    }
}
