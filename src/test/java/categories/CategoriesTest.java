package categories;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import pages.homepage.HeaderPage;
import pages.homepage.ProductsListPage;

public class CategoriesTest extends TestBase {
    public HeaderPage headerPage;

    @BeforeEach
    public  void testSetup() {
        headerPage = new HeaderPage(driver);

    }
    
}
