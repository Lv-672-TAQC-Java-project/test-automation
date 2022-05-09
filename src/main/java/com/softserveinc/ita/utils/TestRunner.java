package com.softserveinc.ita.utils;

import com.codeborne.selenide.Configuration;
import com.softserveinc.ita.pageobjects.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.softserveinc.ita.utils.PropertyUtil.*;

@Listeners(UiTestListener.class)
public class TestRunner {

    protected final HomePage homePage = new HomePage();

    @BeforeSuite
    public void driverConfigurationAndTimeout() {
        Configuration.browser = getDefaultBrowser();
        Configuration.timeout = getDefaultTimeout() * 1000;
    }

    @BeforeMethod
    public void navigateToUrl() {
        homePage.open();
    }
}
