package com.beridevs.Constants;

import java.util.Arrays;

public enum ContentType {
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_JAVASCRIPT("text/javascript"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif");

    private final String value;

    ContentType(String type) {
        this.value = type;
    }

    @Override
    public String toString() {
        return value;
    }

    public static ContentType fromString(String text) {
        for (ContentType type : ContentType.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }

}
