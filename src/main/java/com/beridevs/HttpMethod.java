package com.beridevs;

public enum HttpMethod {
    GET("GET", false),
    POST("POST", true),
    PUT("PUT", true),
    PATCH("PATCH", true),
    DELETE("DELETE", false),
    HEAD("HEAD", false);

    private final boolean requiresBody;
    private final String name;

    HttpMethod(String name, boolean requiresBody) {
        this.name = name;
        this.requiresBody = requiresBody;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean requiresBody() {
        return requiresBody;
    }
}
