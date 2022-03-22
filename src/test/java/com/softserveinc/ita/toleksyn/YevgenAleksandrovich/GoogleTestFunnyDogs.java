package com.softserveinc.ita.toleksyn.YevgenAleksandrovich;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


@Test
public class GoogleTestFunnyDogs {
    public void CheckTextFunnyDogs()

    {
       Configuration.browser = "chrome";
        open("https://google.com");
        $(By.name("q")).val("funny dogs").pressEnter();
        $$("#employees tbody tr").shouldHave(
                texts("funny dogs"));
        $x("(//div[@class='g dFd2Tb'])[9]/descendant::div[@class='ct3b9e']/a")
                .shouldHave(attribute("href"))
                .shouldHave(text("https://")
                        .because("URL should be valid"));
    }

}
