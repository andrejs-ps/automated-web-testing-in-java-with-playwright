package com.pw.m2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

public class FirstPlaywrightScript {

    @Test
    public void firstPlaywrightScript() {
        try(Playwright playwright = Playwright.create()){
            BrowserType browserType = playwright.chromium();
            Browser browser = browserType.launch();
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());
        }
    }

    @Test
    public void firstPlaywrightScriptRefactored() {
        try(Playwright playwright = Playwright.create()){
            Page page = playwright.chromium().launch().newPage();
            page.navigate("https://playwright.dev");
            System.out.println(page.title());
        }
    }
}
