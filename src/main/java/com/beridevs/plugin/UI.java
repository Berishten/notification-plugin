package com.beridevs.plugin;

import java.util.Arrays;
import java.util.List;

import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.plugins.ServiceNameConstants;
import com.dtolabs.rundeck.plugins.rundeck.UIPlugin;

@Plugin(name = "UI", service = ServiceNameConstants.UI)
public class UI implements UIPlugin {

    @Override
    public boolean doesApply(String arg0) {
        System.out.println("doesApply: " + arg0);
        return true;
    }

    @Override
    public List<String> requires(String arg0) {
        return null;
    }

    @Override
    public List<String> resourcesForPath(String arg0) {
        return null;
    }

    @Override
    public List<String> scriptResourcesForPath(String arg0) {
        return Arrays.asList(
                "/js/main.js",
                "/js/httpNotificationPlugin-init.js");
    }

    @Override
    public List<String> styleResourcesForPath(String arg0) {
        return Arrays.asList(
                "/css/httpNotificationPlugin-style.css"
        );
    }

}
