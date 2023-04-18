package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;

public class CartProduct extends BasePage {


    @FindBy(css = "[class] .product-line-info:nth-of-type(1)")
    private WebElement productTitle;
    @FindBy(css = "[class] .current-price")
    private WebElement productPrice;
    @FindBy(css = "strong")
    private WebElement subTotalPrice;
    @FindBy(css = "input[name='product-quantity-spin']")
    private WebElement quantity;
    @FindBy(css = ".cart-items .cart-item:nth-of-type(1) .cart-line-product-actions")
    private WebElement removeFromCartBtn;

    public CartProduct(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public CartProduct(WebDriver driver) {
        super(driver);
    }


    public String getProductTitle() {
        return productTitle.getText();
    }


    public BigDecimal getProductPrice() {
        return getProductPrice(productPrice);
    }

    public BigDecimal getSubTotalPrice() {
        return getProductPrice(subTotalPrice);
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getAttribute("value"));
    }

    public void deleteItem() {
        //WebElement deleteIcon = driver.findElement(By.cssSelector(".cart-items .cart-item:nth-of-type(1) .cart-line-product-actions"));
        //wait.until(ExpectedConditions.elementToBeClickable(removeFromCartBtn));
        removeFromCartBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(removeFromCartBtn));
    }

}
