package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.CLOTHES_SHOES_AND_JEWELRY;
import static com.softserveinc.ita.pageobjects.models.ShoeSize.*;
import static org.assertj.core.api.Assertions.*;

public class ProductParameterTest extends TestRunner {
    @Description("Verify that selected product parameter displayed in product details page.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-34")
    @Test(description = "LVTAQC672-34")
    public void verifyPopularBrandsBlockFunctionality() {
        var product = homePage
                .getHeader()
                .openCatalog()
                .openCategoryPage(CLOTHES_SHOES_AND_JEWELRY)
                .openPopularBrandProductsPage("Asics")
                .getProduct("Кросівки Asics Gel-Kayano 28 1011B189-400 Сині");

        var shoeSize = SIZE_42_5;

        var productDetailsPage = product.selectShoeSize(shoeSize);

        assertThat(shoeSize.getSizeNumber())
                .as("active shoe size on the product details page should be equal to selected size")
                .isEqualTo(productDetailsPage.getSelectedShoeSize());
    }
}
