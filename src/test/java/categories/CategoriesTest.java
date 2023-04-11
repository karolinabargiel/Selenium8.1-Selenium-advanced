package categories;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.categories.CategoryPage;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



public class CategoriesTest extends TestBase {
    public HeaderPage headerPage;
    public CategoryPage categoryPage;
    public ProductsListPage productsListPage;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);
        categoryPage = new CategoryPage(driver);
        productsListPage = new ProductsListPage(driver);

    }

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
