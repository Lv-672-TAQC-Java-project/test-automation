package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class PropertyUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getHomePageUrl() {
        return bundle.getString("home.page.url");
    }

    public static int getDefaultTimeout() {
        return Integer.parseInt(bundle.getString("default.driver.timeout"));
    }

    public static String getDefaultBrowser() {
        return bundle.getString("default.browser");
    }
}
