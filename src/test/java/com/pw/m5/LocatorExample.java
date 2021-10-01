package com.pw.m5;

import com.microsoft.playwright.Locator;
import com.pw.ScriptBase;
import org.junit.jupiter.api.Test;


public class LocatorExample extends ScriptBase {

    @Test
    public void locatorExample() {

        page.navigate(home);
        Locator input = page.locator(".form-control");

        System.out.println(input.count());

        input.first().fill("first");
        input.last().fill("last");
        input.nth(1).fill("second"); // 2nd element at index 1

    }
}
