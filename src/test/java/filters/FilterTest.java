package filters;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.categories.CategoryPage;
import pages.categories.FilterPage;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest extends TestBase {
    public HeaderPage headerPage;
    public CategoryPage categoryPage;
    public ProductsListPage productsListPage;
    public FilterPage filterPage;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        categoryPage = new CategoryPage(driver);
        productsListPage = new ProductsListPage(driver);
        filterPage = new FilterPage(driver);

    }
    @Test
    public void filterProductsInAccessoriesCategory() {
        BigDecimal lowestPrice = new BigDecimal(System.getProperty("priceFilterLeft"));
        BigDecimal highestPrice = new BigDecimal(System.getProperty("priceFilterRight"));
        headerPage.goToAccessoriesCategory();
        productsListPage.waitForProductsToLoad();
        int numOfProducts = productsListPage.getNumberOfProducts();
        filterPage.moveLeftSlider(lowestPrice);
        filterPage.moveRightSlider(highestPrice);
        List<BigDecimal> productPrices = productsListPage.getProductPrices();
        assertThat(productPrices).filteredOn(Objects::nonNull)
                .allSatisfy(price -> assertThat(price).isBetween(lowestPrice, highestPrice));
        filterPage.clearFilters();
        int numOfProductsAfterClear = productsListPage.getNumberOfProducts();
        assertThat(numOfProducts).isEqualTo(numOfProductsAfterClear);

    }

}
