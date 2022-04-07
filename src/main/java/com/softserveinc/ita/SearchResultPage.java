package com.softserveinc.ita;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {

    private Header header = new Header();

    private Filter filter = new Filter();

    public Header getHeader() {
        return header;
    }

    public Filter getFilter() {
        return filter;
    }

    public Product getProduct(int index){
        String productPath = String.format("(//*[contains(@class, 'goods-tile__inner')])[%d]",index);

        return new Product(productPath);
    }

}
