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
public class Models {

    private Collection<Model> models;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Model {

        @JsonProperty("i")
        @JsonAlias({"id", "Key"})
        private String modelValue;

        @JsonProperty("n")
        @JsonAlias({"name", "Value"})
        private String modelKey;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Model model = (Model) o;
            return Objects.equals(modelValue.toLowerCase(), model.modelValue.toLowerCase());
        }

        @Override
        public int hashCode() {
            return Objects.hash(modelValue.toLowerCase());
        }
    }
}
