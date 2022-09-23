package de.cardetecktive.app.authentication.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;

@Getter
@EqualsAndHashCode
public class SidResponse {

    private String sid;
    private Collection<String> upgrades;
    private long pingInterval;
    private long pingTimeout;

}
