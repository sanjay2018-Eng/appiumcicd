/*
package qa.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class test1 {

    AppiumDriver driver;

    @Test
    public void invalidUserName()
    {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameTxtFld.sendKeys("invalidusername");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

        String actualErrTxt = errTxt.getAttribute("text");
        System.out.println("actual error txt - "+actualErrTxt);
        String expectedErrTxt = "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt, expectedErrTxt);


    }

    @Test
    public void invalidPassword()
    {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameTxtFld.sendKeys("standard_user");
        passwordTxtFld.sendKeys("invalidPassword");
        loginBtn.click();

        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

        String actualErrTxt = errTxt.getAttribute("text");
        System.out.println("actual error txt - "+actualErrTxt);
        String expectedErrTxt = "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test
    public void successfulLogin()
    {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameTxtFld.sendKeys("standard_user");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement producttitleTxt = (MobileElement) driver.findElementByXPath("//*[@text='PRODUCTS']");

        String actualProductTitle = producttitleTxt.getAttribute("text");
        System.out.println("Actual Product Title --> "+actualProductTitle);
        String expectedProducttitle = "PRODUCTS";

        Assert.assertEquals(actualProductTitle, expectedProducttitle);
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel XL");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID, "HT6A10204885");
        */
/*String appUrl = System.getProperty("user.dir")+ File.separator + "src" +File.separator + "main"
                +File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        caps.setCapability(MobileCapabilityType.APP, appUrl);*//*


        //caps.setCapability(MobileCapabilityType.APP, "/Users/sanjaynaik/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        caps.setCapability("appPackage", "com.swaglabsmobileapp");
        caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

        //caps.setCapability("noSign", "true");
        //caps.setCapability("unlockType", "pin");
        //caps.setCapability("unlockType", "pattern");
        //caps.setCapability("unlockKey", "1234");
        //caps.setCapability("unlockKey", "123654789");

        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }

}
*/
