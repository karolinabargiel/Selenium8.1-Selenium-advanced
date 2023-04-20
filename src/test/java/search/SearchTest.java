package search;

import base.Pages;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends Pages {

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
