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

import java.util.*;
import java.util.stream.Collectors;

import static models.Cart.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;
    public ProductDetailsPage productDetailsPage;
    public CartPagePopup cartPagePopup;
    public CartPage cartPage;
    public CartProduct cartProduct;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPagePopup = new CartPagePopup(driver);
        cartPage = new CartPage(driver);
        cartProduct = new CartProduct(driver);
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
        for (int i = 0; i<10; i++) {
            productsListPage.openRandomProduct();
            productDetailsPage.setRandomQuantity(1, 5);
            productDetailsPage.addProductToCart(expectedCart);
            cartPagePopup.continueShopping();
            headerPage.openHomePage();
        }
        List<Product> sortedProductsInExpectedCart = getListOfProductsExpCart(expectedCart).stream().sorted(Comparator.comparing(Product::getProductName)).collect(Collectors.toList());
        headerPage.goToCart();
        List<Product> sortedProductsActualCart = cartPage.getItemsFromCart().getProducts().stream().sorted(Comparator.comparing(Product::getProductName)).collect(Collectors.toList());
        assertThat(cartPage.getTotalPrice()).isEqualTo(expectedCart.getTotalOrderCostWithShipping());
        assertThat(sortedProductsActualCart).isEqualTo(sortedProductsInExpectedCart);

    }

    @Test
    public void removeProductsFromCart() {
        Cart expectedCart = new Cart();
        for (int i = 0; i<2; i++) {
            productsListPage.openRandomProduct();
            productDetailsPage.addProductToCart(expectedCart);
            cartPagePopup.continueShopping();
            headerPage.openHomePage();
        }
        headerPage.goToCart();
        assertThat(cartPage.getTotalPrice()).isEqualTo(expectedCart.getTotalOrderCostWithShipping());
        String productRemovedFromCart = cartPage.deleteItem(0);
        expectedCart.checkDeletedProducts(productRemovedFromCart);
        assertThat(cartPage.getTotalPrice()).isEqualTo(expectedCart.getTotalOrderCostWithShipping());
        productRemovedFromCart = cartPage.deleteItem(0);
        expectedCart.checkDeletedProducts(productRemovedFromCart);
        assertThat(cartPage.getTotalPrice()).isEqualTo(expectedCart.getTotalOrderCost());
        assertThat(cartPage.getEmptyCartLabel()).isEqualTo("There are no more items in your cart");
    }

}
