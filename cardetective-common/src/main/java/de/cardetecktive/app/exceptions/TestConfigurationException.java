package de.cardetecktive.app.exceptions;

public class TestConfigurationException extends RuntimeException {

    public TestConfigurationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public TestConfigurationException(String errorMessage) {
        super(errorMessage);
    }
}
