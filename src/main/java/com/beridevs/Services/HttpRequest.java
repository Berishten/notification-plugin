package com.beridevs.Services;

import java.io.IOException;

import com.beridevs.Constants.ContentType;
import com.beridevs.Constants.HttpMethod;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    private OkHttpClient client;

    public HttpRequest(OkHttpClient client) {
        this.client = client;
    }

    public String request(HttpMethod httpMethod, String url, ContentType contentType, String body) throws IOException {
        RequestBody requestBody;
        Request.Builder requestBuilder = new Request.Builder().url(url);

        if (httpMethod.toString().equalsIgnoreCase(HttpMethod.GET.toString())) {
            requestBuilder.get();
        } else if (httpMethod.toString().equalsIgnoreCase(HttpMethod.POST.toString())) {
            requestBody = RequestBody.create(MediaType.parse(contentType.toString()), body);
            requestBuilder.post(requestBody);
        } else if (httpMethod.toString().equalsIgnoreCase(HttpMethod.PUT.toString())) {
            requestBody = RequestBody.create(MediaType.parse(contentType.toString()), body);
            requestBuilder.put(requestBody);
        } else if (httpMethod.toString().equalsIgnoreCase(HttpMethod.PATCH.toString())) {
            requestBody = RequestBody.create(MediaType.parse(contentType.toString()), body);
            requestBuilder.delete(requestBody);
        } else if (httpMethod.toString().equalsIgnoreCase(HttpMethod.DELETE.toString())) {
            requestBuilder.delete();
        } else if (httpMethod.toString().equalsIgnoreCase(HttpMethod.HEAD.toString())) {
            requestBuilder.head();
        } else {
            throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod.toString());
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

}
