package de.cardetecktive.app.services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public abstract class AbstractService {

    private final Supplier<RequestSpecification> requestSpecification;

    protected RequestSpecification given() {
        return RestAssured.given(requestSpecification.get());
    }
}
