package com.softserveinc.ita;

import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class SearchResultPage {

    private Header header = new Header();

    private Filter filter = new Filter();

    @Step("get {index} product")
    public Product getProduct(int index){
        String productPath = String.format("(//*[contains(@class, 'goods-tile__inner')])[%d]",index);

        return new Product(productPath);
    }

}
