package com.pw.m9;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class TraceViewerDemoRefactored {

    Playwright pw;
    Browser browser;
    BrowserContext context;

    @Test
    public void traceViewerFailingTestDemo() {

        pw = Playwright.create();
        browser = pw.chromium().launch();

        context = browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));

        Page page = context.newPage();
        page.setDefaultTimeout(8_000);
        page.navigate("https://playwright.dev/java/");
        page.click("text=Get Started");
        page.click("text=Guidess");
        page.click("text=Trace Viewer");
        Assertions.assertTrue(page.isVisible("text=Recording a trace"));

    }

    @AfterEach
    public void cleanup() {
        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));
        pw.close();
    }
}
