package de.cardetecktive.app.mobilede;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileDeMakes {

    private Collection<Make> makes;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Make {

        @JsonProperty("i")
        private long id;

        @JsonProperty("n")
        private String name;
    }
}
