package com.pw;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

public class AuthScriptBase {

    static Playwright playwright;
    static Browser browser;

    protected static BrowserContext context;
    protected static Page page;

    @BeforeAll
    static void setupAuthenticatedBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                // headless=false for demo purposes
                .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://github.com/login");

        page.fill("#login_field", "your_login");
        page.fill("#password", "password_placeholder");
        page.click("'Sign in'");
        Assertions.assertEquals(page.url(), "https://github.com/");

        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
        context.close();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));
        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
