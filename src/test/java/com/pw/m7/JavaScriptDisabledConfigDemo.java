package com.pw.m7;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.pw.ScriptBase.home;

public class JavaScriptDisabledConfigDemo {

    Playwright pw;
    Browser browser;

    @Test
    public void javascriptConfigDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                                    .setJavaScriptEnabled(false)
        );

        Page page = ctx.newPage();
        page.navigate(home);

        page.click("#clap-image");
        Assertions.assertFalse(page.isVisible("#thank-you"));
        Assertions.assertTrue(page.isVisible("#enable-js-msg"));
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
