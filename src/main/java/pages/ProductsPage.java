package pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {

    @AndroidFindBy(xpath = "//*[@text='PRODUCTS']")
    private MobileElement productTitleTxt;

    public String getTitle()
    {
        return getAttribute(productTitleTxt, "text");
    }

}
