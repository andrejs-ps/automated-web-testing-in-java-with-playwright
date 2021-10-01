package com.pw.m5;

import com.microsoft.playwright.Keyboard;
import com.pw.ScriptBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PressOptions extends ScriptBase {

    @Test
    public void pressOptions() {

        page.navigate(home);
        page.fill("#exampleFormControlInput1", "myEmail@inbox.con"); // typo
        Keyboard kb = page.keyboard();

        kb.press("Backspace");
        kb.press("m");

        page.focus("#contactReason");
        kb.press("ArrowDown");
        kb.press("ArrowDown");

        Assertions.assertTrue(page.isVisible("text=OK, but please make it interesting"));
    }
}
