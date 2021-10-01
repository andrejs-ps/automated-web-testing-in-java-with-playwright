package com.pw.m8;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class RouteAbortDemo {

    Playwright pw;
    Browser browser;

    @Test
    public void imageBlockDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext ctx = browser.newContext();
        Page page = ctx.newPage();

        page.route("**/*.{png,jpg,jpeg,svg}", route -> route.abort());
        page.navigate("https://playwright.dev/");
        Assertions.assertTrue(page.isVisible("text=Playwright logo"));


        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("blockedImage.png")));

    }

    @Test
    public void jsBlockDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext ctx = browser.newContext();

        Page page = ctx.newPage();

        page.route("**/*.{js}", route -> route.abort());
        page.navigate("https://playwright.dev/");

        String theme = page.getAttribute("html", "data-theme");
        Assertions.assertEquals("dark", theme);

        page.click(".react-toggle-track");
        String theme2 = page.getAttribute("html", "data-theme");
        Assertions.assertEquals("dark", theme2); // no change
    }

    @Test
    public void jsBlockDemo2() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext ctx = browser.newContext();

        Page page = ctx.newPage();
        // Abort based on the request type
        page.route("**/*", route -> {
            if ("script".equalsIgnoreCase(route.request().resourceType()))
                route.abort();
            else
                route.resume();
        });

        page.navigate("https://playwright.dev/");

        String theme = page.getAttribute("html", "data-theme");
        Assertions.assertEquals("dark", theme);

        page.click(".react-toggle-track");
        String theme2 = page.getAttribute("html", "data-theme");
        Assertions.assertEquals("dark", theme2); // fails to change, so stays dark
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
