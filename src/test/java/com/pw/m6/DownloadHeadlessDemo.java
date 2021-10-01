package com.pw.m6;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Download;
import com.pw.ScriptBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

public class DownloadHeadlessDemo extends ScriptBase {

    @BeforeEach
    @Override
    protected void createContextAndPage() {

        browser = playwright.chromium().launch(); // headless by default

        context = browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));

        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @Test
    public void downloadHeadlessTest() {

        page.navigate(home);

        Download download = page.waitForDownload(
                () -> page.click("text=Download")
        );

        System.out.println(download.path());
        download.saveAs(Paths.get(new File("C:\\Users\\Andre\\PlaywrightDownloads\\downloaded-headless.pdf").toURI()));
    }
}
