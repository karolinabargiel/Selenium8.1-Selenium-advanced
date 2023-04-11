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
    @FindBy(css = "#ui-id-1 .product")
    private List<WebElement> searchDropdownList;
    @FindBy(css = "#top-menu > li")
    private List<WebElement> categories;

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


}