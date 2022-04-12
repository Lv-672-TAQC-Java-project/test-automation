package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private Header header = new Header();

    public String getSearchTermLabel() {
        return $x("//div[@class='search-header ng-star-inserted']/h1").getText();
    }
}
