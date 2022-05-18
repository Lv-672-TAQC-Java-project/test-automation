package com.softserveinc.ita.pageobjects;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CharacteristicTab extends BasePage {

    public Map<String, String> getProductCharacteristics() {
        var productCharacteristicsMap = new HashMap<String, String>();
        var characteristicSectionsPath = "//section[@class='characteristics-full__group ng-star-inserted']";
        var sections = $$x(characteristicSectionsPath);

        for (int sectionNumber = 1; sectionNumber <= sections.size(); sectionNumber++) {
            var characteristicSectionPath = format("%s[%d]", characteristicSectionsPath, sectionNumber);
            var parametersRowPath = "//div[@class='characteristics-full__item ng-star-inserted']";
            var characteristicParametersPath = characteristicSectionPath + parametersRowPath;
            var characteristicParameters = $$x(characteristicParametersPath);

            for (int parameterNumber = 1; parameterNumber <= characteristicParameters.size(); parameterNumber++) {
                var characteristicParameterPath = format("%s[%d]", characteristicParametersPath, parameterNumber);
                var name = $x(characteristicParameterPath + "/dt/span").getText();
                var value = $x(characteristicParameterPath + "/descendant::li/*").getText();

                productCharacteristicsMap.put(name, value);
            }
        }

        return productCharacteristicsMap;
    }
}
