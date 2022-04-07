package com.softserveinc.ita;

import lombok.Getter;

@Getter
public class SearchResultPage {

    private Header header = new Header();

    public Product getProduct(int index) {

        return new Product(String.format("(//div[@class='goods-tile__inner'])[%s]", index));
    }
}
