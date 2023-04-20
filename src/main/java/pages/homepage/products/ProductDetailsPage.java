package pages.homepage.products;

import models.Cart;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.Random;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement productName;
    @FindBy(css = "#quantity_wanted")
    private WebElement quantity;
    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;
    @FindBy(css = ".current-price span")
    private WebElement productPrice;

    public BigDecimal getProductPrice() {
        return getPrice(productPrice);
    }

    public void setRandomQuantity(int min, int max) {
        Random random = new Random();
        String randomQuantity = String.valueOf(random.nextInt(max - min) + min);
        clearAndInputText(quantity, randomQuantity);
    }

    public String getQuantity() {
        return quantity.getAttribute("value");
    }

    public void setQuantity(String count) {
        clearAndInputText(quantity, count);
    }

    public void addProductToCart(Cart cart) {
        cart.addProduct(new Product(productName.getText(), getProductPrice(), Integer.parseInt(getQuantity())));
        click(addToCartBtn);
    }


}
