package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.TiresSubcategoryFilterSection;

public class SubCategoryPage extends SearchResultPage {

    //This filter section is applicable only for "Tires and wheels" subcategory page
    public TiresSubcategoryFilterSection getTiresSubcategoryFilterSection() {
        return new TiresSubcategoryFilterSection();
    }
}
