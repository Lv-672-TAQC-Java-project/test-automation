package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CharacteristicTab {

    public String getTireCharacteristic() {
        var tireWidth = $x("(//li[@class='ng-star-inserted']/a)[1]")
                .getText()
                .split(" ")[0];
        var tireProfile = $x("(//li[@class='ng-star-inserted']/a)[3]").getText();
        var tireDiameter = $x("(//li[@class='ng-star-inserted']/a)[4]").getText();

        return format("%s/%s %s", tireWidth, tireProfile, tireDiameter);
    }
}
