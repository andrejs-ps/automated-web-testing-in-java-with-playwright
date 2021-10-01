package com.pw.m7;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.pw.ScriptBase.home;
public class ChannelDemo {

    Playwright pw;
    Browser browser;

    @ParameterizedTest
    @ValueSource(strings = {
            "chrome",
            "msedge"
    })
    public void channelDemo(String channel) {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel(channel)
                .setHeadless(false)
                .setSlowMo(2000)
        );

        Page page = browser.newContext().newPage();
        page.navigate(home);
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
