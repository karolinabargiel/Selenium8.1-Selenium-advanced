package pages.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;

public class OrderHistoryRow extends BasePage {
    public OrderHistoryRow(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = "td:nth-child(2)")
    private WebElement orderDate;
    @FindBy(css = "td:nth-child(3)")
    private WebElement totalPrice;
    @FindBy(css = "td:nth-child(5) .label")
    private WebElement status;
    @FindBy(xpath = "//tbody/tr[1]/td[6]/a[1]")
    private WebElement detailsBtn;


    public String getOrderDate() {
        return orderDate.getText();
    }

    public String getStatus() {
        return status.getText();
    }

    public BigDecimal getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return getPrice(totalPrice);
    }

    public OrderHistoryDetailsPage goToDetails() {
        detailsBtn.click();
        return new OrderHistoryDetailsPage(driver);
    }


}
