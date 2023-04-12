package basketAndCheckout;

import base.TestBase;
import models.Cart;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;
import pages.homepage.products.ProductDetailsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;
    public ProductDetailsPage productDetailsPage;
    public CartPage cartPage;
    public Cart cart;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
        cart = new Cart();
    }

    @Test
    public void addProductsToCart(){
        String productQuantity = System.getProperty("cartProductsAmount");
        String productName = System.getProperty("cartTestProduct");
        headerPage.goToArtCategory();
        productsListPage.openProductByName(productName);
        productDetailsPage.setQuantity(productQuantity);
        assertThat(productDetailsPage.getQuantity()).isEqualTo(productQuantity);
        productDetailsPage.addProductToCart(this.cart);
        Product expectedProduct = this.cart.getProduct(productName);
        assertThat(cartPage.getProductName()).isEqualTo(productName);
        assertThat(cartPage.getProductPrice()).isEqualTo(expectedProduct.getPrice());
        assertThat(cartPage.getSubtotalPrice()).isEqualTo(cart.getTotalOrderCost());
        assertThat(cartPage.getCartAmountInfo()).isEqualTo("There are "+ productQuantity +" items in your cart.");
        cartPage.continueShopping();
        assertThat(productQuantity).isEqualTo(headerPage.getCartIconQty());

    }


}
