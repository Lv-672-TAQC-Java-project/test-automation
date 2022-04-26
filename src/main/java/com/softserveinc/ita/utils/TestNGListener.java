package com.softserveinc.ita.utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Base64;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        screenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] screenshot() {
        String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
        return Base64.getDecoder().decode(screenshotAsBase64);
    }
}
