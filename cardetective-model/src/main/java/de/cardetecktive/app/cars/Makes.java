package de.cardetecktive.app.cars;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@ToString
public class Makes {

    private Collection<Make> makes;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Make {

        @JsonProperty("i")
        @JsonAlias({"id", "slugLabel"})
        private String makeValue;

        @JsonProperty("n")
        @JsonAlias({"name", "label"})
        private String makeKey;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Makes.Make make = (Makes.Make) o;
            return Objects.equals(makeValue.toLowerCase(), make.makeValue.toLowerCase());
        }

        @Override
        public int hashCode() {
            return Objects.hash(makeValue.toLowerCase());
        }

        @Override
        public String toString() {
            return "[" + makeKey + "] (" +
                    "makeValue=" + makeValue + ")";
        }
    }
}
