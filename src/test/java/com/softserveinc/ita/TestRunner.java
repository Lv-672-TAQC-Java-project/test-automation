package com.softserveinc.ita;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class TestRunner {
    @BeforeMethod
    public void initDriver() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10 * 1000;
        open("https://google.com");
    }
}
