package base;

import models.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import pages.account.OrderHistoryDetailsPage;
import pages.account.OrderHistoryPage;
import pages.cart.CartPage;
import pages.cart.CartPagePopup;
import pages.cart.CartProduct;
import pages.categories.CategoryPage;
import pages.categories.FilterPage;
import pages.checkout.AddressesPage;
import pages.checkout.ConfirmationPage;
import pages.checkout.PaymentPage;
import pages.checkout.ShippingMethodPage;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;
import pages.homepage.SignInPage;
import pages.homepage.products.ProductDetailsPage;

public class Pages extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;
    public CategoryPage categoryPage;
    public FilterPage filterPage;
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
    public void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
        categoryPage = new CategoryPage(driver);
        filterPage = new FilterPage(driver);
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
}
