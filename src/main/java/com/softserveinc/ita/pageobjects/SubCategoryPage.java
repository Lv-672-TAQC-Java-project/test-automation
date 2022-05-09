package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.FilterSection;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;;

public class SubCategoryPage extends SearchResultPage {

    public boolean isOpenedFilterSection() {
        return isDisplayed($x("//div[@class='widget-auto ng-star-inserted']"), ofSeconds(5));
    }

    //This filter section is only on subcategory page "Tires and wheels"
    public FilterSection getTiresSubcategoryFilterSection() {
        return new FilterSection();
    }
}
