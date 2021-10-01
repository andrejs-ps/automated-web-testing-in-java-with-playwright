package com.pw.m7;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseUrlConfigDemo {

    private String base = "file:///" + System.getProperty("user.dir") + "\\src\\web\\";

    Playwright pw;
    Browser browser;

    @Test
    public void baseUrlConfigDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch();

        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                                    .setBaseURL(base)
        );

        // should be this
        Page page = ctx.newPage();

        page.navigate("home.html");
        Assertions.assertEquals(page.title(), "Home Page");

        page.navigate("advantages.html");
        Assertions.assertEquals(page.title(), "Advantages");
    }

    @AfterEach
    public void cleanup() {
        browser.close();
        pw.close();
    }
}
