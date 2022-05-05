package com.softserveinc.ita.pageobjects;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CharacteristicTab {

    public Map<String, String> getProductCharacteristics() {
        var productCharacteristicsMap = new HashMap<String, String>();
        var characteristicSectionsPath = "//section[@class='characteristics-full__group ng-star-inserted']";
        var sections = $$x(characteristicSectionsPath);

        for (int i = 1; i <= sections.size(); i++) {
            var characteristicParametersPath = format("%s[%d]%s", characteristicSectionsPath, i, "//div[@class='characteristics-full__item ng-star-inserted']");
            var characteristicParameters = $$x(characteristicParametersPath);

            for (int a = 1; a <= characteristicParameters.size(); a++) {
                var characteristicParameterPath = format("%s[%d]", characteristicParametersPath, a);
                var name = $x(characteristicParameterPath + "/dt/span").getText();
                var value = $x(characteristicParameterPath + "/descendant::li/*").getText();

                productCharacteristicsMap.put(name, value);
            }
        }

        return productCharacteristicsMap;
    }
}
