package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

@UtilityClass
public class PropertyUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getHomePageUrl() {
        return bundle.getString("home.page.url");
    }

    public static int getDefaultTimeout() {
        return Integer.parseInt(bundle.getString("default.driver.timeout"));
    }

    public static String getChromeBrowser() {
        return bundle.getString("chrome.browser");
    }

    public static String getFirefoxBrowser() {
        return bundle.getString("firefox.browser");
    }
}
