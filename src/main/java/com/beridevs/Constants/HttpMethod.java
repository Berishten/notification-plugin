package com.beridevs.Constants;

/*
 * An enumeration of the HTTP methods supported by the plugin.
 * @version 1.0
 */
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

    /**
     * Returns whether the HTTP method requires a body.
     * @return True if the method requires a body, false otherwise
     */
    public boolean requiresBody() {
        return requiresBody;
    }
}
