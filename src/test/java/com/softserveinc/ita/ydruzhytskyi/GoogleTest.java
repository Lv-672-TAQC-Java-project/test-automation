package com.softserveinc.ita.ydruzhytskyi;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertTrue;

public class GoogleTest {

    @Test
    public void verifyThatLinkHasHrefAttributeWithValidUrl() {
        Configuration.browser = "chrome";
        Configuration.timeout = 30 * 1000;
        open("https://google.com");

        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        String url = $x("(//div[@class='g dFd2Tb'])[9]/descendant::div[@class='ct3b9e']/a").getAttribute("href");
        assertTrue(isValidURL(url));
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }
}
