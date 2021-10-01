package com.pw.m5;

import com.microsoft.playwright.Page;
import com.pw.ScriptBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ClickFillOptions extends ScriptBase {

    @Test
    public void clickOptionsCount() {

        page.navigate(home);
        page.click("#clap-image", new Page.ClickOptions().setClickCount(2));

        Assertions.assertTrue(page.isVisible("#thank-you-2"));
        // OR
        Assertions.assertTrue(page.isVisible("text=You can only clap once, but thanks for your enthusiasm"));
    }

    @Test
    public void fillOptions() {

        page.navigate(home);
        page.fill("id=exampleMessage", "So I was thinking the other day...",
                                                new Page.FillOptions().setForce(true));
    }

    @Test
    public void checkScenario() {

        page.navigate(home);
        page.fill("#exampleFormControlInput1", "my@email.com");
        page.fill("#exampleMessage", "Not sure how to say this...");

        page.check("#sendCopy");
//        page.uncheck("#sendCopy");

        page.click("#submit-contact");
        Assertions.assertTrue(page.isVisible("text=We sent you a copy of your message : Not sure how to say this..."));
    }
}
