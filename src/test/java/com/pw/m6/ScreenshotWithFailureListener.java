package com.pw.m6;

import com.microsoft.playwright.Page;
import com.pw.ScriptBase;
import com.pw.TestResultHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultHandler.class)
public class ScreenshotWithFailureListener extends ScriptBase {

    public Page page;

    @BeforeEach
    @Override
    protected void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        page.setViewportSize(1920, 1080);
    }

    @Test
    public void screenshotTest() {

        page.setViewportSize(1920, 1080);
        page.navigate(home);
        assertFalse(page.isVisible("#boredOption"));

        page.selectOption("select#contactReason", "Bored");
        assertTrue(page.isVisible("#boredOption"));

        page.selectOption("select#contactReason", "Question");

        // this will fail - it's expected
        assertFalse(page.isVisible("#boredOption"),
                "The blue box should've disappeared after selecting another option");
    }
}
