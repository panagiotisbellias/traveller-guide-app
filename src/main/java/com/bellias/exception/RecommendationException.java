package com.bellias.exception;

public class RecommendationException extends Exception {

    public RecommendationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecommendationException(String message) {
        super(message);
    }

}
