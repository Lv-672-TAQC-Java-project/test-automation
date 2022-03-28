package com.softserveinc.ita;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestRunner {

    protected final GoogleHomePage googleHomePage = new GoogleHomePage();

    @BeforeSuite
    public void driverConfigurationAndTimeout() {
        Configuration.browser = "chrome";
        Configuration.timeout = 60 * 1000;
    }

    @BeforeMethod
    public void navigateToUrl() {
        googleHomePage.open();
    }
}
