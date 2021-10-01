package com.pw.m8;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RouteFulFillDemo {

    Playwright pw;
    Browser browser;
    String token = "your_real_token_here";

    @Test
    public void requestChangeDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                        .setExtraHTTPHeaders(Map.of("Authorization", "token " + token))
        );

        Page page = ctx.newPage();

        page.route("**/*", route -> {
            Map<String, String> headers = new HashMap<>(route.request().headers());
            headers.put("Test", "added header");
            headers.remove("authorization");
            route.resume(new Route.ResumeOptions().setHeaders(headers));
        });


        Response response = page.navigate("https://api.github.com/user");
        System.out.println(response.request().headers());
        System.out.println(response.text());
        Assertions.assertEquals(401, response.status());
    }

    @Test
    public void requestChangeContentDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch( new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions());
        Page page = ctx.newPage();

        page.route("**/*/user", route -> route.fulfill(
                new Route.FulfillOptions()
                        .setStatus(203)
                        .setPath(Paths.get("src\\web\\files\\test_data.json"))
        ));

        Response response = page.navigate("https://api.github.com/user");
        System.out.println(response.text());
        Assertions.assertEquals(203, response.status());
        Assertions.assertTrue(response.text().contains("Route FulFill Demo"));

    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
