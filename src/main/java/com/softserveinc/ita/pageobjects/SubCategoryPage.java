package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.TiresSubcategoryFilterSection;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

public class SubCategoryPage extends SearchResultPage {

    //This filter section is applicable only for "Tires and wheels" subcategory page
    public TiresSubcategoryFilterSection getTiresSubcategoryFilterSection() {
        return new TiresSubcategoryFilterSection();
    }

    public boolean isOpened() {
        return isDisplayed($x("//div[@class='ng-star-inserted']/h1"), ofSeconds(5));
    }
}
