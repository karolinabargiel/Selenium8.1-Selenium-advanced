package pages.cart;

import models.Cart;
import models.Product;
import org.openqa.selenium.By;
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
    @FindBy(css = ".product-line-info > a")
    private WebElement productName;
    @FindBy(css = "span.product-price")
    private WebElement productPrice;
    @FindBy(css = "div[class='cart-summary-line cart-total'] span[class='value']")
    private WebElement totalPrice;
    @FindBy(css = ".cart-item")
    private List<WebElement> cartList;
    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement totalItemsPrice;
    @FindBy(css = ".js-cart > .no-items")
    private WebElement emptyCartLabel;
    @FindBy(css = ".cart-detailed-actions .btn-primary")
    private WebElement proceedToCheckoutBtn;


//    public BigDecimal totalItemsPrice() {
//        return BigDecimal.valueOf(getProductPrice(totalPrice));
//    }

    public BigDecimal getTotalPrice(){
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return getPrice(totalPrice);
    }

    public List<CartProduct> getListOfProductsInCart() {
        List<CartProduct> products = new ArrayList<>();
        for (WebElement item : cartList) {
            products.add(new CartProduct(driver, item));
        }
        return products;
    }

    public void goToCart() {
        String cartLink = "http://146.59.32.4/index.php?controller=cart&action=show";
        driver.get(cartLink);
    }

    public String deleteItem(int item) {
        List<CartProduct> cartProducts = getListOfProductsInCart();
        String productNameToDelete = cartProducts.get(item).getProductTitle();
        cartProducts.get(item).deleteItem();
        return productNameToDelete;
    }

    public CartProduct getProductFromCartByName(List<CartProduct> products, String productName) {
        return products.stream().filter(p -> p.getProductTitle().equals(productName)).findFirst().orElse(null);
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
