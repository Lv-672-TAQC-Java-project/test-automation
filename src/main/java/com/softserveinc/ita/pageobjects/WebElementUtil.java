package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

import java.time.Duration;

public class WebElementUtil {

    public static boolean isDisplayed(SelenideElement element, Duration duration) {
        try {
            return element
                    .shouldBe(visible, duration)
                    .isDisplayed();
        } catch (AssertionError ignored) {
            return false;
        }
    }
}
