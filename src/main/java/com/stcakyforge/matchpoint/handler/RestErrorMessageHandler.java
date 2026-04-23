package com.stcakyforge.matchpoint.handler;

import org.springframework.http.HttpStatus;

public class RestErrorMessageHandler {

    private HttpStatus status;
    private String message;

    public RestErrorMessageHandler(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
