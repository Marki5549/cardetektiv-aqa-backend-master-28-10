package de.cardetecktive.app.utils;

import de.cardetecktive.app.exceptions.TestDataException;

import java.util.Arrays;
import java.util.Collection;

//TODO: remove this class and find more elegance way of json formatting
public final class JsonFormatter {

    private static final Collection<String> REGEX = Arrays.asList("\\d+\\[", "]$", "(\\d+:\\d+)");

    private JsonFormatter() {
    }

    public static String getValidJson(String invalidJson) {
        for (String regex : REGEX) {
            invalidJson = invalidJson.replaceAll(regex, "");
        }
        if (!JsonParser.isValidJson(invalidJson)) {
            throw new TestDataException("Json is invalid after formatting");
        }
        return invalidJson;
    }
}
