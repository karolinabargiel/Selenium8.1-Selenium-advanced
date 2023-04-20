package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

public class ShippingMethodPage extends BasePage {
    public ShippingMethodPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "form#js-delivery > button[name='confirmDeliveryOption']")
    private WebElement continueBtn;
    @FindBy(id = "delivery_option_2")
    private WebElement myCarrierOption;

    public void setShippingMethod() {
        wait.until(ExpectedConditions.visibilityOf(continueBtn));
        myCarrierOption.click();
        continueBtn.click();
    }
}
