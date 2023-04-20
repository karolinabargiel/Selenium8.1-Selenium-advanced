package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody > tr")
    private List<WebElement> listOfOrders;

    public OrderHistoryRow getOrder(String orderReferenceNumber) {
        for (WebElement order : listOfOrders) {
            if (order.findElement(By.cssSelector("th")).getText().equals(orderReferenceNumber)) {
                return new OrderHistoryRow(driver, order);
            }
        }
        return null;
    }


}
