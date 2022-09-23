package de.cardetecktive.app.services.external;

public interface MakeModelProvider {

    String getMakes();

    String getModelsByMake(String makeId);
}
