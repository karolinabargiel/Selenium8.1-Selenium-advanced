package categories;

import base.Pages;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CategoriesTest extends Pages {


    @Test
    public void openEachMainCategory() {
        List<String> categoriesInMenuNames = headerPage.getCategoriesNames();
        assertThat(categoriesInMenuNames).isNotEmpty();

        for (String category : categoriesInMenuNames) {
            headerPage.openCategoryByName(category);
            String categoryHeader = categoryPage.getCategoryName();
            assertThat(categoryHeader).isEqualTo(category);
            boolean filtersVisible = productsListPage.getFilterBox().isDisplayed();
            assertThat(filtersVisible).isTrue();
            int productsCount = productsListPage.getNumberOfProducts();
            int productsCountFromLabel = productsListPage.getNumberOfProductsFromProductCountLabel();
            assertThat(productsCount).isEqualTo(productsCountFromLabel);
        }
    }

}
