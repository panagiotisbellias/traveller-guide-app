package com.bellias.exception;

/**
 * The construction of a custom runtime exception that represents errors
 * occurring in the data storage layer.
 * This exception can wrap an underlying cause or be used with a custom message only.
 * It extends {@link RuntimeException}, so it is unchecked.
 *
 * @author Panagiotis Bellias
 */
public class DataStoreException extends RuntimeException {

    /**
     * Constructs a new DataStoreException with the specified detail message
     * and cause.
     *
     * @param message the detail message explaining the exception
     * @param cause the underlying cause of the exception
     */
    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new DataStoreException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public DataStoreException(String message) {
        super(message);
    }

}
