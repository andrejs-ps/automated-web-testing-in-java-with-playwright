package com.pw.m7;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ViewportSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.pw.ScriptBase.home;

public class ViewPortConfigDemo {

    Playwright pw;
    Browser browser;

    @Test
    public void viewPortConfigDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        BrowserContext ctx = browser.newContext(new Browser.NewContextOptions()
                                    .setViewportSize(ViewPortSizes.IPHONE_X)
        );


        Page page = ctx.newPage();
        page.navigate(home);

        // verify that the UI is still usable
        page.click("#clap-image");
        Assertions.assertTrue(page.isVisible("#thank-you"));
    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }


    static class ViewPortSizes {
        public static final ViewportSize IPHONE_X = new ViewportSize(375, 812);
        public static final ViewportSize GALAXY_S5 = new ViewportSize(360, 640);
    }
}
