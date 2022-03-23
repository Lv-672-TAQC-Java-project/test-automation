package com.softserveinc.ita;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;

public class TestRunner {
    @BeforeSuite
    public void driverConfigurationAndTimeout() {
        Configuration.browser = "chrome";
        Configuration.timeout = 60 * 1000;
    }

    @BeforeMethod
    public void navigateToUrl() {
        open("https://google.com");
    }
}
