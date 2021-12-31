package pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Accessiblity_Alert_Page extends MenuPage {

    public Accessiblity_Alert_Page()
    {

    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Accessibility Node Provider\")")
    @iOSXCUITFindBy(accessibility = "Simple")
    private MobileElement accessiblity_alter_PageHeader;

    //@AndroidFindBy(uiAutomator = "")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='UICatalog']")
    private MobileElement backButton;


    public String getAcessAlertPageHeader()
    {
        return getText(accessiblity_alter_PageHeader);
    }

    public void clickBackPage()
    {
        clickBack(backButton);
    }


}

