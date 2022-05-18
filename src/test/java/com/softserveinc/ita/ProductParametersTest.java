package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.PopularBrandName;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.CLOTHES_SHOES_AND_JEWELRY;
import static com.softserveinc.ita.pageobjects.models.PopularBrandName.*;

public class ProductParametersTest extends TestRunner {
    @Description("Verify that changed product parameters displayed in product name.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-34")
    @Test(description = "LVTAQC672-34")
    public void verifyPopularBrandsBlockFunctionality() {
        var searchResultPage = homePage
                .getHeader()
                .openCatalog()
                .openCategoryPage(CLOTHES_SHOES_AND_JEWELRY)
                .openPopularBrandProductsPage(ASICS);


    }

}
