package com.beridevs;

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
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    AUDIO_MPEG("audio/mpeg"),
    VIDEO_MP4("video/mp4"),
    APPLICATION_PDF("application/pdf"),
    APPLICATION_ZIP("application/zip"),
    APPLICATION_GZIP("application/gzip"),
    APPLICATION_X_TAR("application/x-tar"),
    APPLICATION_X_RAR_COMPRESSED("application/x-rar-compressed"),
    APPLICATION_VND_MS_EXCEL("application/vnd.ms-excel"),
    APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_SPREADSHEETML_SHEET(
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    APPLICATION_VND_MS_POWERPOINT("application/vnd.ms-powerpoint"),
    APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_PRESENTATIONML_PRESENTATION(
            "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    APPLICATION_VND_MS_WORD("application/vnd.ms-word"),
    APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_WORDPROCESSINGML_DOCUMENT(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    APPLICATION_X_SHOCKWAVE_FLASH("application/x-shockwave-flash"),
    APPLICATION_X_FONT_TTF("application/x-font-ttf"),
    APPLICATION_X_FONT_OTF("application/x-font-otf");

    private final String value;

    ContentType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String[] getValues() {
        return Arrays.stream(ContentType.values())
                .map(ContentType::getValue)
                .toArray(String[]::new);
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
