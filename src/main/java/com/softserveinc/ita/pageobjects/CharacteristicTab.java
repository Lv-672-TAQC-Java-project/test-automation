package com.softserveinc.ita.pageobjects;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;

public class CharacteristicTab {

    public Map<String, String> getProductCharacteristics() {
        var productCharacteristicsMap = new HashMap<String, String>();
        var sections = $$x("//section[@class='characteristics-full__group ng-star-inserted']");

        for (var section : sections) {
            var parameters = section.$$x(".//div[@class='characteristics-full__item ng-star-inserted']");
            parameters
                    .asFixedIterable()
                    .forEach(parameter -> {
                        var name = parameter.$x("./dt/span").getText();
                        var value = parameter.$x("./descendant::li/*").getText();

                        productCharacteristicsMap.put(name, value);
                    });
        }

        return productCharacteristicsMap;
    }
}
