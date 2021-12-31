package com.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();

    protected static ThreadLocal<String> platform = new ThreadLocal<String>();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal<String> deviceName = new ThreadLocal<String >();

    TestUtils utils;

    public AppiumDriver getDriver(){
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);
    }

    public Properties getProps(){
        return props.get();
    }

    public void setProps(Properties props2){
        props.set(props2);
    }

    public String getPlatform(){
        return platform.get();
    }

    public void setPlatform(String platform2){
        platform.set(platform2);
    }

    public String getDateTime(){
        return dateTime.get();
    }

    public void setDateTime(String dateTime2){
        dateTime.set(dateTime2);
    }

    public String getDeviceName(){
        return deviceName.get();
    }

    public void setDeviceName(String deviceName2){
        deviceName.set(deviceName2);
    }

    public BaseTest()
    {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("super before method");
        ((CanRecordScreen)getDriver()).startRecordingScreen();
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result){
        System.out.println("super after method");
        String media = ((CanRecordScreen)getDriver()).stopRecordingScreen();

        if (result.getStatus() == 2){
            Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
            String dir = "videos" + File.separator
                    + params.get("platformName") + "_" + params.get("platformVersion") + "_"
                    + params.get("deviceName") + File.separator
                    + dateTime + File.separator
                    + result.getTestClass().getRealClass().getSimpleName();

            File videoDir = new File(dir);
            synchronized (videoDir){
                if(!videoDir.exists()){
                    videoDir.mkdirs();
                }
            }


            try {
                FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName()
                        + ".mp4");
                stream.write(Base64.decodeBase64(media));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Parameters({"platformName", "platformVersion", "deviceName", "emulator", "udid", "systemPort", "chromeDriverPort",
    "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName, @Optional("androidOnly")String emulator,
                           String udid, @Optional("androidOnly") String systemPort, @Optional("androidOnly") String chromeDriverPort,
                           @Optional("iOSOnly") String wdaLocalPort, @Optional("iOSOnly") String webkitDebugProxyPort) throws IOException {

         utils = new TestUtils();
         setDateTime(utils.dateTime());
        setPlatform(platformName);
        setDeviceName(deviceName);
        URL url;
        InputStream inputStream = null;
        Properties props = new Properties();
        AppiumDriver driver;
        try{

            props = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            setProps(props);

            DesiredCapabilities caps = new DesiredCapabilities();
            /*caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);*/
            url = new URL(props.getProperty("appiumURL") + "4723/wd/hub");

            switch(platformName){
                case "Android":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
                    caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                    caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability(MobileCapabilityType.UDID, "HT6A10204885");
                    caps.setCapability("systemPort", systemPort);
                    caps.setCapability("chromeDriverPort", chromeDriverPort);

                    if (emulator.equalsIgnoreCase("true"))
                    {
                        caps.setCapability("avd", deviceName);
                    }
              //caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    //caps.setCapability("appActivity", props.getProperty("androidAppActivity"));

                    //URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));

                    //String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();

                    String appUrl = System.getProperty("user.dir")+ File.separator + "src" +File.separator + "test"
                            +File.separator + "resources" + File.separator + "app" + File.separator + "ApiDemos-debug.apk";

                    System.out.println("appURL is --> "+appUrl);
                    caps.setCapability(MobileCapabilityType.APP, appUrl);
                    //url = new URL(props.getProperty("appiumURL") + "4723/wd/hub");
                    driver = new AndroidDriver(url, caps);
                    break;

                case "iOS":
                    caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
                    caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                    caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                    String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
                    System.out.println("appURL is --> "+iOSAppUrl);
                    //caps.setCapability(MobileCapabilityType.APP, iOSAppUrl);
                    caps.setCapability("bundleId", props.getProperty("iOSBundleId"));

                    caps.setCapability("simulatorStartupTimeout", 180000);
                    //iPhone UDID - B4E2AB39-245D-4BB0-B0CB-A5BB4C106703
                    caps.setCapability("wdaLocalPort", wdaLocalPort);
                    caps.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
                    //url = new URL(props.getProperty("appiumURL") + "4724/wd/hub");

                    driver = new IOSDriver(url, caps);
                    break;

                default:
                    throw new Exception("Invalid platform - "+platformName);
            }

            setDriver(driver);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null)
            {
                inputStream.close();
            }
        }


        //String appUrl = System.getProperty("user.dir")+ File.separator + "src" +File.separator + "main"
          //      +File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        //caps.setCapability(MobileCapabilityType.APP, appUrl);


        //caps.setCapability(MobileCapabilityType.APP, "/Users/sanjaynaik/Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");


    }

    public void clear(MobileElement e)
    {
        waitForVisibility(e);
        e.clear();
    }

    public void waitForVisibility(MobileElement e)
    {
        WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void click(MobileElement e)
    {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt)
    {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute)
    {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e)
    {
        switch (getPlatform()){
            case "Android":
                return getAttribute(e, "text");
            case "iOS":
                return getAttribute(e, "label");
        }
        return null;
    }

    public void clickBack(MobileElement e)
    {
        switch (getPlatform()){
            case "Android":
                ((AndroidDriver)getDriver()).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
            case "iOS":
                click(e);
        }

    }

 /*   public void scrollToElement()
    {
        return (MobileElement)((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()"+".className(\"Web View\")"
        )
    }*/




    @AfterTest
    public void afterTest()
    {
        //driver.quit();
    }

}
