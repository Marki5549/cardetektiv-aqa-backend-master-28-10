package de.cardetecktive.app.utils;

import de.cardetecktive.app.constants.enums.WebSocketMessageCode;
import org.jetbrains.annotations.NotNull;

public final class WsMessageUtils {

    private static final String MESSAGE_PATTERN = "%d[\"%s\",%s]";

    private WsMessageUtils() {
    }

    public static <Mbody> String createMessage(@NotNull final WebSocketMessageCode messageCode,
                                               @NotNull final Mbody messageBody) {
        return String.format(MESSAGE_PATTERN, messageCode.getCode(),
                messageCode.getType(),
                JsonParser.objectToJson(messageBody));
    }

    public static String createWithNoBody(@NotNull final WebSocketMessageCode messageCode) {
        return String.valueOf(messageCode.getCode());
    }
}