package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.CategoryName;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CreditTypeNumber.*;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.PRODUCT_AVAILABILITY;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

public class CreditTest extends TestRunner {

    @Description("Add test script to cover 'Купити в кредит' section has correct price")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-45")
    @Test(description = "LVTAQC672-45")
    public void verifyReceivedPaymentOfPriceOfInstallments() {
        var detailsPage = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(CategoryName.LAPTOPS_AND_COMPUTERS, "Lenovo")
                .getFilter()
                .filterBySection(PRODUCT_AVAILABILITY, "Є в наявності")
                .getProduct(1)
                .openDetailsPage();

        var creditModal = detailsPage.openCreditModal();

        var productPrice = (double) detailsPage.getPrice();

        range(1, creditModal.getAmountCreditsTypes())
                .forEach(i -> assertThat(
                        (int) Math.ceil(productPrice / creditModal.getCreditPaymentsAmount(values()[i])))
                        .as("price division to periods of payment should equal credit price for period")
                        .isEqualTo(creditModal.getCreditPrice()));
    }
}
