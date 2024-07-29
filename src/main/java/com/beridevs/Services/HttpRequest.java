package com.beridevs.Services;

import java.io.IOException;

import com.beridevs.Constants.ContentType;
import com.beridevs.Constants.HttpMethod;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A class to build and make HTTP requests using the OkHttp library.
 * 
 * @author Pablo Escobar Vega
 * @version 1.0
 */
public class HttpRequest {
    private OkHttpClient client;

    public HttpRequest(OkHttpClient client) {
        this.client = client;
    }

    /**
     * Make an HTTP request to the given URL with the given method, content type,
     * and body.
     * 
     * @param httpMethod  The HTTP method to use for the request
     * @param url         The URL to send the request to
     * @param contentType The content type of the request body
     * @param body        The content to include in the request body
     * @return The response body as a string
     * @throws IOException If an error occurs while making the request
     */
    public String request(HttpMethod httpMethod, String url, ContentType contentType, String body) throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        switch (httpMethod) {
            case GET:
                requestBuilder.get();
                break;
            case POST:
            case PUT:
            case PATCH:
                RequestBody requestBody = RequestBody.create(MediaType.parse(contentType.toString()), body);
                requestBuilder.method(httpMethod.name(), requestBody);
                break;
            case DELETE:
                requestBuilder.delete();
                break;
            case HEAD:
                requestBuilder.head();
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

}
