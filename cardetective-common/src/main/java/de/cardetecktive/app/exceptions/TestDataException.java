package de.cardetecktive.app.exceptions;

public class TestDataException extends RuntimeException {

    public TestDataException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public TestDataException(String errorMessage) {
        super(errorMessage);
    }
}
