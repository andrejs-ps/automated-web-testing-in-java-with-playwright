package com.pw.m9;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class TraceViewerDemo {

    Playwright pw;
    Browser browser;

    @Test
    public void traceViewerDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch();

        BrowserContext context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
        );

        Page page = context.newPage();
        page.setDefaultTimeout(8_000);
        page.navigate("https://playwright.dev/java/");
        page.click("text=Get Started");
        page.click("text=Guidess");
        page.click("text=Trace Viewer");
        Assertions.assertTrue(page.isVisible("text=Recording a trace"));

        context.tracing().stop(new Tracing.StopOptions()
                            .setPath(Paths.get("trace.zip"))
        );

    }

    @AfterEach
    public void cleanup() {
        pw.close();
    }
}
