package de.cardetecktive.app.enums;

public enum CarSearchType {

    CAR("CAR"),
    TRUCK("TRVU7");

    private String vehicleType;

    CarSearchType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
