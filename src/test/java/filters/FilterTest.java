package filters;

import base.Pages;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest extends Pages {

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
