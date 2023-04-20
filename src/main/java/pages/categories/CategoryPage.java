package pages.categories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement categoryName;


    public String getCategoryName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".h1")));
        return categoryName.getText();
    }

}
