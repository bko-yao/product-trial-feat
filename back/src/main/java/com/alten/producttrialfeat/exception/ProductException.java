package com.alten.producttrialfeat.exception;

/**
 * Exception pour l'entité Product.
 */
public class ProductException extends RuntimeException {

    private int errorCode;

    public ProductException(String message, int errorCode) {
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