package com.tests;


import com.qa.BaseTest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws IOException {
        InputStream datais = null;
        try {
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        }
        catch (Exception e)
        {
                e.printStackTrace();
                throw e;
        }
        finally {
            if (datais != null)
            {
                datais.close();
            }
        }

    }



    @BeforeMethod
    public void beforeMethod(Method m)
    {
        loginPage = new LoginPage();
        System.out.println("\n" + "********* Starting test: " + m.getName() + " ******" + "\n");
    }

    @Test(description = "Hello")
    public void androidiOSTest()
    {
        loginPage.pressLink();
    }

    @Test(enabled = false)
    public void invalidUserName()
    {

            loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("userName"));
            loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
            loginPage.pressLoginBtn();

            String actualErrTxt = loginPage.getErrTxt();

            String expectedErrTxt = "Username and password do not match any user in this service.";
            System.out.println("actual error txt - "+actualErrTxt + "\n" + "expected error Txt" +expectedErrTxt);

            Assert.assertEquals(actualErrTxt, expectedErrTxt);



    }

    @Test(enabled = false)
    public void invalidPassword()
    {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrTxt();

        String expectedErrTxt = "Username and password do not match any user in this service.";
        System.out.println("actual error txt - "+actualErrTxt + "\n" + "expected error Txt" +expectedErrTxt);

        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test(enabled = false)
    public void successfulLogin()
    {
        loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("userName"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.pressLoginBtn();

        String actualProductTitle = productsPage.getTitle();
        String expectedProducttitle = "PRODUCTS";

        System.out.println("Actual product Title - "+actualProductTitle+"\n"+"expected product title - "+expectedProducttitle);

        Assert.assertEquals(actualProductTitle, expectedProducttitle);

    }


}
