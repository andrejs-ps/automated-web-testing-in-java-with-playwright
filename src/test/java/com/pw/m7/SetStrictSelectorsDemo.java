package com.pw.m7;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static com.pw.ScriptBase.home;


public class SetStrictSelectorsDemo {

    Playwright pw;

    @Test
    public void setStrictSelectorsDemo() {

        pw = Playwright.create();
        BrowserContext ctx = pw.chromium().launch()
                .newContext(new Browser.NewContextOptions()
                            .setStrictSelectors(true)
        );

        Page page = ctx.newPage();
        page.navigate(home);

//        page.isVisible("text=Submit");
        page.click("text=Submit");
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
