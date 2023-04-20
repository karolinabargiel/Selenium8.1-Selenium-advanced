package pages.checkout;

import models.Address;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;


public class AddressesPage extends BasePage {
    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "p:nth-of-type(4) > a")
    private WebElement addInvoiceAddressFormBtn;
    @FindBy(css = "input[name='address1']")
    private WebElement addressInput;
    @FindBy(css = "input[name='postcode']")
    private WebElement zipcodeInput;
    @FindBy(css = "input[name='city']")
    private WebElement cityInput;
    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement continueBtn;
    @FindBy(css = "article:nth-of-type(2) .delete-address.text-muted")
    private WebElement deleteShippingAddress;
    @FindBy(css = "a[data-link-action='different-invoice-address']")
    private WebElement billingAddressDifferFromShippingBtn;

    public void setAddInvoiceAddressForm(Address address) {
        billingAddressDifferFromShippingBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(addressInput));
        addressInput.sendKeys(address.getAddress());
        cityInput.sendKeys(address.getCity());
        zipcodeInput.sendKeys(address.getZipCode());
        continueBtn.click();
    }
}
