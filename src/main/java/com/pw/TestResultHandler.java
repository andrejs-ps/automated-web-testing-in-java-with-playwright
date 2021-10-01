package com.pw;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;
import java.nio.file.Paths;

public class TestResultHandler implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        Boolean testFailed = context.getExecutionException().isPresent();

        if (testFailed) {
            Field resultField = null;
            try {
                resultField = testInstance.getClass().getDeclaredField("page");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            Page page = null;
            try {
                page = (Page) resultField.get(testInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("failure.png")));
        }
    }
}
