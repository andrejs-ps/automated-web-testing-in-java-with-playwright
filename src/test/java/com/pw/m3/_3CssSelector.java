package com.pw.m3;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;


public class _3CssSelector {

    String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";

    @Test
    public void cssSelectorsTest() {
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium();
            Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
            Page page = browser.newPage();
            page.navigate(home);

            // most generic option
            page.fill("input", "first input box that PW finds");

            // finds the FIRST element and fills it in
            page.fill(".form-control", "First box with this class");

            // works too, but still wrong
            page.fill("form #exampleFormControlInput1", "Combined");

            // works, but overkill
            page.fill(":nth-match(.form-control, 2)", "Hello there stranger");
        }
    }
}
