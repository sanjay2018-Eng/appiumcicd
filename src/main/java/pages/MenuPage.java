package pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseTest {

    @iOSXCUITFindBy(xpath = "//*[@label='UICatalog']")
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"API Demos\")")
    private MobileElement textHeader;

    //new UiSelector().text(\"Accessibility\")

    public String getHeaderText()
    {
        return getText(textHeader);
    }

}
