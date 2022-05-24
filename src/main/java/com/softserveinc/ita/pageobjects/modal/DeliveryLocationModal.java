package com.softserveinc.ita.pageobjects.modal;

import com.softserveinc.ita.pageobjects.ProductDetailsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DeliveryLocationModal {

    @Step("Searched delivery location for {city} and {region}")
    public ProductDetailsPage searchLocation(String city, String region) {
        var inputFieldPath = "//div[@class='autocomplete']/input[@name='search']";
        $x(inputFieldPath).clear();
        $x(inputFieldPath).sendKeys(city);
        $x(String.format("//ul[@class='autocomplete__list dialog']/li/b[text()='%s']/ancestor::li[text()[contains(.,'%s')]]", city, region)).click();
        $x("//div[@class='header-location__footer']/button").click();

        return new ProductDetailsPage();
    }
}
