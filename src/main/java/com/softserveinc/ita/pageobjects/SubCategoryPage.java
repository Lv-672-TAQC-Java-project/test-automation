package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.TiresSubcategoryFilterSection;

import static com.codeborne.selenide.Selenide.$x;

public class SubCategoryPage extends SearchResultPage {

    //This filter section is applicable only for "Tires and wheels" subcategory page
    public TiresSubcategoryFilterSection getTiresSubcategoryFilterSection() {
        return new TiresSubcategoryFilterSection();
    }

    public String getSubCategoryLabel() {
        return $x("//*[@class='catalog-heading ng-star-inserted']").text();
    }
}
