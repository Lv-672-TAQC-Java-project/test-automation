package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.SMARTPHONES_TV_AND_ELECTRONICS;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryLocationTest extends TestRunner {

    @Description("Add a test script to cover the delivery location selection functionality")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-40")
    @Test(description = "LVTAQC672-40")
    public void verifyDeliveryLocationSelectionFunctionality() {
        var productDetailsPage = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(SMARTPHONES_TV_AND_ELECTRONICS, "Фітнес-браслети")
                .getProduct(1)
                .openDetailsPage();

        String city = "Чернівці";
        String region = "Чернівецька обл.";

        productDetailsPage
                .openDeliveryLocationModal()
                .searchLocation(city, region);

        assertThat(productDetailsPage.getDeliveryCityName())
                .as(format("Delivery location on the product details page should contain %s and %s", city, region))
                .contains(city, region);
    }
}
