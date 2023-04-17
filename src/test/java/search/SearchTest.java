package search;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends TestBase {
    public HeaderPage headerPage;
    public ProductsListPage productsListPage;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        productsListPage = new ProductsListPage(driver);
    }

    @Test
    public void searchRandomItem() {
        int initialProductsActualCount = productsListPage.getNumberOfProducts();
        assertThat(initialProductsActualCount).isPositive();
        String randomProductText = productsListPage.getNameForRandomProduct();
        headerPage.searchForProduct(randomProductText);
        String resultOfSearch = productsListPage.getTheFirstItemName();
        assertThat(resultOfSearch).isEqualTo(randomProductText);

    }

    @Test
    public void searchDropdownResults() {
        headerPage.insertTextToSearch(System.getProperty("searchValue"));
        List<String> searchDropdownTexts = headerPage.getSearchDropdownTexts();
        assertThat(searchDropdownTexts).allMatch(results -> results.contains(System.getProperty("searchValue")));

    }

}
