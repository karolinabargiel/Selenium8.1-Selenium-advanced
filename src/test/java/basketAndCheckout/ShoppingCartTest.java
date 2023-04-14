package basketAndCheckout;

import base.TestBase;
import models.Cart;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.cart.CartPagePopup;
import pages.cart.CartProduct;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;
import pages.homepage.products.ProductDetailsPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;
    public ProductDetailsPage productDetailsPage;
    public CartPagePopup cartPagePopup;
    public CartPage cartPage;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPagePopup = new CartPagePopup(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void addProductsToCart(){
        Cart cart = new Cart();
        String productQuantity = System.getProperty("cartProductsAmount");
        String productName = System.getProperty("cartTestProduct");
        headerPage.goToArtCategory();
        productsListPage.openProductByName(productName);
        productDetailsPage.setQuantity(productQuantity);
        assertThat(productDetailsPage.getQuantity()).isEqualTo(productQuantity);
        productDetailsPage.addProductToCart(cart);
        Product expectedProduct = cart.getProduct(productName);
        assertThat(cartPagePopup.getProductName()).isEqualTo(productName);
        assertThat(cartPagePopup.getProductPrice()).isEqualTo(expectedProduct.getPrice());
        assertThat(cartPagePopup.getTotalPrice()).isEqualTo(cart.getTotalOrderCostWithShipping());
        assertThat(cartPagePopup.getCartAmountInfo()).isEqualTo("There are "+ productQuantity +" items in your cart.");
        cartPagePopup.continueShopping();
        assertThat(productQuantity).isEqualTo(headerPage.getCartIconQty());

    }

    @Test
    public void addRandomProductsToCart() {
        Cart expectedCart = new Cart();
        for (int i = 0; i<4; i++) {
            productsListPage.openRandomProduct();
            productDetailsPage.setRandomQuantity(1, 5);
            productDetailsPage.addProductToCart(expectedCart);
            cartPagePopup.continueShopping();
            headerPage.openHomePage();
        }
        System.out.println(expectedCart);
        headerPage.goToCart();
        System.out.println("Actual cart = " + cartPage.getItemsFromCart());
        assertThat(cartPage.getTotalPrice()).isEqualTo(expectedCart.getTotalOrderCostWithShipping());
        assertThat(cartPage.getItemsFromCart()).isEqualTo(expectedCart);


    }


}
