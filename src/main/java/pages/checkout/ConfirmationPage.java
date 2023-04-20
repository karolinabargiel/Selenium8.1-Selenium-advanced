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

    @FindBy(css = ".col-sm-4.col-xs-9.details > span")
    private WebElement productName;
    @FindBy(css = ".col-xs-4.text-sm-center.text-xs-left")
    private WebElement unitPrice;
    @FindBy(css = "tr[class='total-value font-weight-bold'] td:nth-child(2)")
    private WebElement totalPrice;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(3) em")
    private WebElement shippingMethod;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(2)")
    private WebElement paymentMethod;
    @FindBy(css = "tr:nth-of-type(2) > td:nth-of-type(2)")
    private WebElement shippingAndHandling;
    @FindBy(css = "section#content-hook_payment_return .col-md-12")
    private WebElement checkPaymentsDetails;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(1)")
    private WebElement orderReferenceNumber;

    public BigDecimal getTotalPrice() {
        return getProductPrice(totalPrice);
    }
    public String getPaymentMethod() {
        return getTextAfterColon(paymentMethod);
    }

    public String getOrderReferenceNumber() {
        return getTextAfterColon(orderReferenceNumber);
    }

    public ConfirmationPage goToOrderHistory() {
        driver.get(System.getProperty("orderHistoryUrl"));
        return this;
    }
}
