package com.softserveinc.ita.kmushynska;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ENTER;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleHomePageIsOpen() {
        String searchTerm = "funny dogs";
        String inputFieldPath = "//input[@class='gLFyf gsfi']";
        $x(inputFieldPath).sendKeys(searchTerm);
        $x(inputFieldPath).sendKeys(ENTER);

        $x("//div[@class='logo']").click();

        String message = "Google home page is opened";
        $x("//img[@class='lnXdpd']")
                .shouldBe(visible.because(message));
        $x("//div[@class='RNNXgb']")
                .shouldBe(visible.because(message));
        $x("(//input[@class='gNO89b'])[2]")
                .shouldBe(visible.because(message));
        $x("(//input[@class='RNmpXc'])[2]")
                .shouldBe(visible.because(message));
        $x("//div[@id='SIvCob']")
                .shouldBe(visible.because(message));
    }
}
