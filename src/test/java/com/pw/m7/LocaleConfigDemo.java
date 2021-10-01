package com.pw.m7;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LocaleConfigDemo {

    Playwright pw = Playwright.create();
    Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));

    @Test
    public void localeConfigDemo() {
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                .setLocale("de-DE") // German locale
        );

        Page page = ctx.newPage();
        page.navigate("https://google.com/");

        page.click("text=Ich stimme zu");
    }


    @ParameterizedTest
    @CsvSource({"de-DE, Ich stimme zu",
                "es-ES, Acepto"})
    public void localeConfigDemoParametrized(String locale, String buttonText) {
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                .setLocale(locale)
        );

        Page page = ctx.newPage();
        page.navigate("https://google.com/");

        page.click("text=" + buttonText);
        // no failure means passed test
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
