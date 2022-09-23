package de.cardetecktive.app.utils;

import io.qameta.allure.Attachment;

public final class AllureUtils {

    @Attachment(value = "Attachement", type = "text/plain")
    public static String attachement(final String message) {
        return message;
    }
}
