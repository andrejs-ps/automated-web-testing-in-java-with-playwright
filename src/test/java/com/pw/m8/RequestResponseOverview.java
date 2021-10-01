package com.pw.m8;

import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;
import com.pw.ScriptBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RequestResponseOverview extends ScriptBase {

    @Test
    public void responseApiDemo() {

        Response r = page.navigate(home); // .html
        System.out.println(r.status()); // 0

        Response r2 = page.navigate("https://playwright.dev");

        System.out.println(r2.url());
        System.out.println(r2.status());
        System.out.println(r2.ok()); // true if in the 200-299 range
        System.out.println(r2.headers());

        System.out.println(r2.body());
        System.out.println("Converted byte array");
        System.out.println(new String(r2.body(), StandardCharsets.UTF_8));
        System.out.println("PW convenience method");
        System.out.println(r2.text());

    }

    @Test
    public void requestApiDemo() {

        Response response = page.navigate("https://playwright.dev/");
        Request request = response.request();

        System.out.println(request.headers());
        System.out.println(request.postData());
        System.out.println(request.method());
    }

    @Test
    public void monitorHttpTrafficDemo() {

        List<Integer> responses = new ArrayList<>();
        page.onResponse(response -> responses.add(response.status()));

        page.navigate("https://playwright.dev/");

        System.out.println(responses);

        boolean foundMatch = responses.stream()
                .anyMatch(i -> i < 200 || i >= 300);

        Assertions.assertFalse(foundMatch);
    }
}
