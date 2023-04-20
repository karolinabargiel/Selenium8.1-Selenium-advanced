package basketAndCheckout;

import base.TestBase;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.account.OrderHistoryDetailsPage;
import pages.account.OrderHistoryPage;
import pages.account.OrderHistoryRow;
import pages.cart.CartPage;
import pages.cart.CartPagePopup;
import pages.cart.CartProduct;
import pages.checkout.AddressesPage;
import pages.checkout.ConfirmationPage;
import pages.checkout.PaymentPage;
import pages.checkout.ShippingMethodPage;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;
import pages.homepage.SignInPage;
import pages.homepage.products.ProductDetailsPage;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static models.Cart.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ShoppingCartTest extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;
    public ProductDetailsPage productDetailsPage;
    public CartPagePopup cartPagePopup;
    public CartPage cartPage;
    public CartProduct cartProduct;
    public SignInPage signInPage;
    public AddressesPage addressesPage;
    public ShippingMethodPage shippingMethodPage;
    public PaymentPage paymentPage;
    public UserFactory userFactory;
    public ConfirmationPage confirmationPage;
    public OrderHistoryPage orderHistoryPage;
    public OrderHistoryDetailsPage orderHistoryDetailsPage;


    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPagePopup = new CartPagePopup(driver);
        cartPage = new CartPage(driver);
        cartProduct = new CartProduct(driver);
        signInPage = new SignInPage(driver);
        addressesPage = new AddressesPage(driver);
        shippingMethodPage = new ShippingMethodPage(driver);
        paymentPage = new PaymentPage(driver);
        userFactory = new UserFactory();
        confirmationPage = new ConfirmationPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryDetailsPage = new OrderHistoryDetailsPage(driver);
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

    @Test
    public void checkoutTest() {
        Cart cart = new Cart();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateNow = formatter.format(date);
        String productName = System.getProperty("cartTestProduct");
        headerPage.goToLogin();
        signInPage.signIn(UserFactory.getAlreadyRegisteredUserCredentials());
        headerPage.goToArtCategory();
        productsListPage.openProductByName(productName);
        productDetailsPage.addProductToCart(cart);
        cartPagePopup.proceedToCheckoutOrder();
        cartPage.proceedToCheckout();
        addressesPage.setAddInvoiceAddressForm(AddressFactory.getAddressForUser());
        shippingMethodPage.setShippingMethod();
        paymentPage.setPaymentOptions();
        BigDecimal totalOrderPrice = confirmationPage.getTotalPrice();
        String orderNumber = confirmationPage.getOrderReferenceNumber();
        confirmationPage.goToOrderHistory();
        OrderHistoryRow order = orderHistoryPage.getOrder(orderNumber);
        assertThat(totalOrderPrice).isEqualTo(order.getTotalPrice());
        assertThat("Awaiting check payment").isEqualTo(order.getStatus());
        assertThat(dateNow).isEqualTo(order.getOrderDate());
        order.goToDetails();
        String orderAddressData = orderHistoryDetailsPage.getDeliveryAddressData();
        String orderInvoiceData= orderHistoryDetailsPage.getInvoiceAddressData();
        assertThat(orderAddressData).as("String not contains").contains(orderInvoiceData);

    }


}
