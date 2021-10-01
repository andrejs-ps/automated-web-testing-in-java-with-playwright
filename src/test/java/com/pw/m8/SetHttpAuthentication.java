package com.pw.m8;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

public class SetHttpAuthentication {

    Playwright pw;
    Browser browser;
    String token = "your_real_token_here";
    @Test
    public void setHttpAuthentication() {

        pw = Playwright.create();
        browser = pw.chromium().launch();

        // --------------- UI part ---------------
        BrowserContext uiContext = browser.newContext();

        Page uiPage = uiContext.newPage();
        uiPage.navigate("https://github.com/andrejs-ps");
        Assertions.assertTrue(uiPage.isVisible("text=Repositories 10"));

        // --------------- Web API part - cross-check ---------------

        BrowserContext apiContext = browser.newContext(new Browser.NewContextOptions()
//                                        .setHttpCredentials("usr", "pwd")
                                        .setExtraHTTPHeaders(Map.of("Authorization", "token " + token))
        );

        Page webApiPage = apiContext.newPage();
        Response response = webApiPage.navigate("https://api.github.com/user");
        System.out.println(response.text());
        Assertions.assertEquals(200, response.status());
        Assertions.assertTrue(response.text().contains("\"public_repos\": 10"));
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
