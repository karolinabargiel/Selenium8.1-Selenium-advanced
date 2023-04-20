package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class OrderHistoryDetailsPage extends BasePage {
    public OrderHistoryDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "article[id='delivery-address'] address")
    private WebElement deliveryAddresData;
    @FindBy(css = "article[id='invoice-address'] address")
    private WebElement invoiceAddresData;


    public String getDeliveryAddressData() {
        return deliveryAddresData.getText();
    }

    public String getInvoiceAddressData() {
        return invoiceAddresData.getText();
    }


}
