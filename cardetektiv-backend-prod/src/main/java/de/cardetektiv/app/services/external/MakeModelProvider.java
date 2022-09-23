package de.cardetektiv.app.services.external;

public interface MakeModelProvider {

    String getMakes();

    String getModelsByMake(String makeId);
}
