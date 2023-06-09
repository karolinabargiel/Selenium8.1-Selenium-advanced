package pages.cart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;

public class CartPagePopup extends BasePage {
    public CartPagePopup(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".modal-body .product-price")
    private WebElement productPrice;

    @FindBy(css = ".subtotal")
    private WebElement subtotalPrice;

    @FindBy(css = ".shipping")
    private WebElement shipping;

    @FindBy(css = ".product-total .value")
    private WebElement totalPrice;

    @FindBy(css = ".modal .cart-products-count")
    private WebElement cartAmountInfo;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingBtn;
    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement proceedToCheckoutBtn;

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn));
        click(continueShoppingBtn);
    }

    public String getCartAmountInfo() {
        return cartAmountInfo.getText();
    }

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return getPrice(productPrice);
    }

    public BigDecimal getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return getPrice(totalPrice);
    }

    public CartPage proceedToCheckoutOrder() {
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutBtn));
        proceedToCheckoutBtn.click();
        return new CartPage(driver);
    }
}
