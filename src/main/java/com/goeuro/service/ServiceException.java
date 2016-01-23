package com.goeuro.service;

/**
 * Created by sujay on 23/01/16.
 */
public class ServiceException extends RuntimeException {
    private final int statusCode;

    public ServiceException(final int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
