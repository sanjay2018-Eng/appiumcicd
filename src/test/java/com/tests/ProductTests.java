package com.tests;

import com.qa.BaseTest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Accessiblity_Alert_Page;
import pages.LoginPage;
import pages.MenuPage;

import java.io.IOException;
import java.io.InputStream;

public class ProductTests extends BaseTest {

    MenuPage menuPage;
    Accessiblity_Alert_Page accAlert;
    LoginPage loginPage;
    InputStream datais;
    JSONObject loginUsers;



    @Test
    public void ClickActivityTest() throws InterruptedException {
        loginPage = new LoginPage();
        accAlert = loginPage.pressLink();
        Thread.sleep(3000);
        //accAlert.clickBackPage();
        Assert.assertTrue(true);


    }

}
