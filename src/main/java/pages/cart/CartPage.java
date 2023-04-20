package pages.cart;

import models.Cart;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class='cart-summary-line cart-total'] span[class='value']")
    private WebElement totalPrice;
    @FindBy(css = ".cart-item")
    private List<WebElement> itemsInCartList;
    @FindBy(css = ".js-cart > .no-items")
    private WebElement emptyCartLabel;
    @FindBy(css = ".cart-detailed-actions .btn-primary")
    private WebElement proceedToCheckoutBtn;


    public BigDecimal getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return getPrice(totalPrice);
    }

    public List<CartProduct> getListOfProductsInCart() {
        List<CartProduct> products = new ArrayList<>();
        for (WebElement item : itemsInCartList) {
            products.add(new CartProduct(driver, item));
        }
        return products;
    }

    public String deleteItem(int item) {
        List<CartProduct> cartProducts = getListOfProductsInCart();
        String productNameToDelete = cartProducts.get(item).getProductTitle();
        cartProducts.get(item).deleteItem();
        return productNameToDelete;
    }

    public String getEmptyCartLabel() {
        return emptyCartLabel.getText();
    }

    public void proceedToCheckout() {
        proceedToCheckoutBtn.click();
    }


    public Cart getItemsFromCart() {
        Cart cart = new Cart();
        for (CartProduct product : getListOfProductsInCart()) {
            cart.addProduct(new Product(product));
        }
        return cart;
    }

}
