package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class PropertyUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getHomePageUrl() {
        return getProperty("home.page.url");
    }

    public static int getDefaultTimeout() {
        return Integer.parseInt(getProperty("default.driver.timeout"));
    }

    public static String getDefaultBrowser() {
        return getProperty("default.browser");
    }

    private static String getProperty(String name) {
        String systemEnvVariable = System.getProperty(name);
        if (systemEnvVariable != null && !systemEnvVariable.isEmpty() && !systemEnvVariable.isBlank()) {
            return systemEnvVariable;
        } else {
            return bundle.getString(name);
        }
    }
}
