package com.softserveinc.ita.rvytrykush;

import com.codeborne.selenide.*;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleTest extends TestRunner {

    @Test
    public void verifyThatGoogleLogoIsPresent() {
        $x("//*[@name = 'q']").setValue("funny dogs").pressEnter();
        $x("//*[@id = 'logo']").shouldBe(Condition.visible);
    }
}
