package com.beridevs.plugin;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.rundeck.app.spi.Services;

import com.beridevs.Constants.ContentType;
import com.beridevs.Constants.HttpMethod;
import com.beridevs.Services.HttpRequest;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.core.plugins.configuration.DynamicProperties;
import com.dtolabs.rundeck.core.plugins.configuration.StringRenderingConstants;
import com.dtolabs.rundeck.plugins.descriptions.PluginDescription;
import com.dtolabs.rundeck.plugins.descriptions.PluginProperty;
import com.dtolabs.rundeck.plugins.descriptions.RenderingOption;
import com.dtolabs.rundeck.plugins.descriptions.RenderingOptions;
import com.dtolabs.rundeck.plugins.descriptions.SelectValues;
import com.dtolabs.rundeck.plugins.notification.NotificationPlugin;

import okhttp3.OkHttpClient;

@Plugin(service = "Notification", name = "httpNotificationPlugin")
@PluginDescription(title = "HttpNotificationPlugin", description = "Notification plugin, to make flexible HTTP requests.")
public class HttpNotificationPlugin
        implements NotificationPlugin, DynamicProperties {

    @PluginProperty(title = "Http Method", description = "HTTP Method to use for the request", defaultValue = "GET", required = true)
    @SelectValues(values = { "GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "OPTIONS" })
    private String httpMethod;

    @PluginProperty(title = "URL", description = "URL to send the HTTP request to", required = true)
    private String url;

    @PluginProperty(title = "Content Type", description = "Type of content in the request body", defaultValue = "text/plain", required = true)
    @SelectValues(values = { "text/plain", "text/html", "text/css", "text/javascript", "application/javascript",
            "application/json", "application/xml", "application/x-www-form-urlencoded", "multipart/form-data",
            "application/octet-stream", "image/jpeg", "image/png", "image/gif", "audio/mpeg", "video/mp4",
            "application/pdf", "application/zip", "application/gzip", "application/x-tar",
            "application/x-rar-compressed", "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", "application/vnd.ms-word",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/x-shockwave-flash",
            "application/x-font-ttf", "application/x-font-otf" })
    private String contentType;

    @PluginProperty(title = "Body Content", description = "Content to include in the request body")
    @RenderingOptions({
            @RenderingOption(key = StringRenderingConstants.DISPLAY_TYPE_KEY, value = "CODE")
    })
    private String bodyContent;

    public boolean postNotification(String trigger, Map executionData, Map config) {
        HttpMethod method = HttpMethod.valueOf(this.httpMethod);
        ContentType contentType = ContentType.fromString(this.contentType);
        HttpRequest httpRequest = new HttpRequest(new OkHttpClient());
        try {
            httpRequest.request(method, url, contentType, bodyContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Map<String, Object> dynamicProperties(
            final Map<String, Object> projectAndFrameworkValues,
            Services services) {
        Map<String, Object> list = new LinkedHashMap<>();

        Map<String, String> fields = new TreeMap<>();
        fields.put("test1", "atest");
        fields.put("test2", "another");

        list.put("customFields", fields);

        return list;
    }
}
