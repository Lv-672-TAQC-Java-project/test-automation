package com.softserveinc.ita.utils;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

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
