package com.pw.m6;

import com.pw.AuthScriptBase;
import org.junit.jupiter.api.Test;

public class AuthenticationRefactored extends AuthScriptBase {

    @Test
    public void auth() {
        page.navigate("https://github.com");
        // verify things being already logged in
        page.click("[aria-label=\"View profile and more\"]");
        page.click("text=Signed in as andrejs-ps");
    }


    @Test
    public void auth2() {
        page.navigate("https://github.com/andrejs-ps");
        // verify things being already logged in
        page.click("[aria-label=\"View profile and more\"]");
        page.click("text=Signed in as andrejs-ps");
    }
}
