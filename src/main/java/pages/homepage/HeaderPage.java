package pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class HeaderPage extends BasePage {
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[placeholder='Search our catalog']")
    private WebElement searchInput;
    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    @FindBy(css = ".logo")
    private WebElement logoBtn;
    @FindBy(css = "#ui-id-1 .product")
    private List<WebElement> searchDropdownList;
    @FindBy(css = "#top-menu > li")
    private List<WebElement> categories;
    @FindBy(css = "#category-6")
    private WebElement accessories;
    @FindBy(css = "#category-9")
    private WebElement artCategory;
    @FindBy(css = "span.cart-products-count")
    private WebElement cartIconQty;
    @FindBy(css = "div#_desktop_user_info .hidden-sm-down")
    private WebElement signInBtn;


    public void insertTextToSearch(String text) {
        click(searchInput);
        inputText(searchInput, text);
        waitUntilSearchLoads();
    }

    private void waitUntilSearchLoads() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-1")));
    }

    public void searchForProduct(String text) {
        insertTextToSearch(text);
        click(searchBtn);
    }

    public List<String> getSearchDropdownTexts() {
        List<String> searchDropdownTexts = new ArrayList<>();
        for (WebElement element : searchDropdownList) {
            searchDropdownTexts.add(element.getText());
        }
        return searchDropdownTexts;
    }

    public int countResultsInDropdown() {
        return getSearchDropdownTexts().size();
    }

    public void openHomePage(){
        wait.until(ExpectedConditions.elementToBeClickable(logoBtn));
        click(logoBtn);
    }

    public void openCategoryByName(String categoryName) {
        for (WebElement category : categories) {
            if (category.getText().equals(categoryName)) {
                click(category);
                return;
            }
        }
    }

    public List<String> getCategoriesNames() {
        List<String> categoriesNames = new ArrayList<>();
        for (WebElement category : categories) {
            categoriesNames.add(category.getText());
        }
        return categoriesNames;
    }

    public void goToAccessoriesCategory() {
        click(accessories);
    }

    public void goToArtCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(artCategory));
        click(artCategory);
    }

    public String getCartIconQty() {
        return cartIconQty.getText().replaceAll("\\D", "");
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIconQty));
        click(cartIconQty);
    }

    public void goToLogin() {
        signInBtn.click();
    }


}
