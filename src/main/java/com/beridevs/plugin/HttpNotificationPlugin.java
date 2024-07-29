package com.beridevs.plugin;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dtolabs.rundeck.core.plugins.configuration.StringRenderingConstants;
import com.beridevs.Constants.ContentType;
import com.beridevs.Constants.HttpMethod;
import com.beridevs.Services.HttpRequest;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.plugins.ServiceNameConstants;
import com.dtolabs.rundeck.plugins.descriptions.PluginDescription;
import com.dtolabs.rundeck.plugins.descriptions.PluginProperty;
import com.dtolabs.rundeck.plugins.descriptions.RenderingOption;
import com.dtolabs.rundeck.plugins.descriptions.RenderingOptions;
import com.dtolabs.rundeck.plugins.descriptions.SelectValues;
import com.dtolabs.rundeck.plugins.notification.NotificationPlugin;

import okhttp3.OkHttpClient;

@Plugin(name = HttpNotificationPlugin.SERVICE_PROVIDER_NAME, service = ServiceNameConstants.Notification)
@PluginDescription(title = HttpNotificationPlugin.TITLE, description = HttpNotificationPlugin.DESCRIPTION)
public class HttpNotificationPlugin implements NotificationPlugin {
    private HttpRequest httpRequest;
    static Logger logger = LoggerFactory.getLogger(HttpNotificationPlugin.class);

    public static final String SERVICE_PROVIDER_NAME = "httpNotificationPlugin";
    public static final String TITLE = "Http Notification Plugin";
    public static final String DESCRIPTION = "Notification plugin, to make flexible HTTP requests.";

    @PluginProperty(title = "Http Method", description = "HTTP Method to use for the request", defaultValue = "GET", required = true)
    @SelectValues(values = { "GET", "POST", "PUT", "PATCH", "DELETE", "HEAD" })
    private String httpMethod;

    @PluginProperty(title = "URL", description = "URL to send the HTTP request to", required = true)
    private String url;

    @PluginProperty(title = "Content Type", description = "Type of content in the request body", defaultValue = "text/html", required = true)
    @SelectValues(values = {
            "text/plain", "text/html", "text/css",
            "text/javascript", "application/javascript",
            "application/json", "application/xml", "application/x-www-form-urlencoded",
            "multipart/form-data", "application/octet-stream", "image/jpeg", "image/png", "image/gif" })
    private String contentType;

    @PluginProperty(title = "Body Content", description = "Content to include inthe request body")
    @RenderingOptions({
            @RenderingOption(key = StringRenderingConstants.DISPLAY_TYPE_KEY, value = "CODE")
    })
    private String bodyContent;

    public HttpNotificationPlugin() {
    }

    public boolean postNotification(String trigger, Map executionData, Map config) {
        HttpMethod method = HttpMethod.valueOf(this.httpMethod);
        ContentType contentType = ContentType.fromString(this.contentType);
        httpRequest = new HttpRequest(new OkHttpClient());
        try {
            httpRequest.request(method, url, contentType, bodyContent);
        } catch (IOException e) {
            System.err.printf("ERROR: %s\n", e);
            return false;
        }
        return true;
    }
}