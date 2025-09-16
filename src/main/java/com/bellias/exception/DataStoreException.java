package com.bellias.exception;

public class DataStoreException extends RuntimeException {

    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataStoreException(String message) {
        super(message);
    }

}
