package com.beridevs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.beridevs.Constants.ContentType;
import com.beridevs.Constants.HttpMethod;
import com.beridevs.Services.HttpRequest;

import java.io.IOException;

import okhttp3.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class HttpRequestTest {

    @Mock
    private OkHttpClient mockClient;

    @Mock
    private Call mockCall;

    @Mock
    private Response mockResponse;

    private HttpRequest httpRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpRequest = new HttpRequest(mockClient);
    }

    @Test
    void testGetRequestSuccess() throws IOException {
        String expectedResponse = "response body";
        Request request = new Request.Builder()
                .url("https://example.com")
                .get()
                .build();

        mockResponse = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(expectedResponse, MediaType.parse(ContentType.TEXT_PLAIN.toString())))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        String actualResponse = httpRequest.request(HttpMethod.GET, "https://example.com", ContentType.TEXT_PLAIN, "");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testPostRequestSuccess() throws IOException {
        String expectedResponse = "response body";
        Request request = new Request.Builder()
                .url("https://example.com")
                .post(RequestBody.create("{}", MediaType.parse("application/json")))
                .build();

        mockResponse = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(expectedResponse, MediaType.parse(ContentType.APPLICATION_JSON.toString())))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        String actualResponse = httpRequest.request(HttpMethod.POST, "https://example.com",
                ContentType.APPLICATION_JSON, "{}");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testRequestFailure() throws IOException {
        Request request = new Request.Builder()
                .url("https://example.com")
                .get()
                .build();

        mockResponse = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(500)
                .message("Internal Server Error")
                .body(ResponseBody.create("", MediaType.parse(ContentType.TEXT_PLAIN.toString())))
                .build();

        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);

        IOException exception = assertThrows(IOException.class,
                () -> httpRequest.request(HttpMethod.GET, "https://example.com", ContentType.TEXT_PLAIN, ""));

        assertEquals(
                "Unexpected code Response{protocol=http/1.1, code=500, message=Internal Server Error, url=https://example.com/}",
                exception.getMessage());
    }
}