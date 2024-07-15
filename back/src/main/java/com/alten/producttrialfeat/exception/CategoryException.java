package com.alten.producttrialfeat.exception;

/**
 * Exception pour l'entité Categorie
 */
public class CategoryException extends RuntimeException {

    private int errorCode;

    public CategoryException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return this.errorCode + " : " + this.getMessage();
    }
}