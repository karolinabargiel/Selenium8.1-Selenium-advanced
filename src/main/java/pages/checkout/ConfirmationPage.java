package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "tr[class='total-value font-weight-bold'] td:nth-child(2)")
    private WebElement totalPrice;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(1)")
    private WebElement orderReferenceNumber;

    public BigDecimal getTotalPrice() {
        return getPrice(totalPrice);
    }

    public String getOrderNumber() {
        return getTextAfterColon(orderReferenceNumber);
    }

    public ConfirmationPage goToOrderHistory() {
        driver.get(System.getProperty("orderHistoryUrl"));
        return this;
    }
}
