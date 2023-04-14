package pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsListPage extends BasePage {
    public ProductsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[itemprop='item']")
    List<WebElement> productsList;
    @FindBy(css = ".product-title")
    List<WebElement> productsNamesList;
    @FindBy(css = "div .price")
    List<WebElement> productsPricesList;
    @FindBy(css = "#search_filters")
    WebElement filtersSideMenu;
    @FindBy(css = ".col-md-6.hidden-sm-down.total-products")
    WebElement productCountLabel;

    public List<WebElement> getAllItems() {
        return productsNamesList;
    }

    public WebElement getRandomItemFromList() {
        return getRandomElement(productsNamesList);
    }

    public void openRandomProduct(){
        click(getRandomItemFromList());
    }

    public void openProductByName(String productName){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".product-title")));
        productsNamesList.stream()
                .filter(product -> product.getText().equals(productName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getNameOfItemFrom(WebElement element) {
        return element.findElement(By.cssSelector("a")).getAttribute("textContent");
    }

    public String getNameForRandomProduct() {
        return getNameOfItemFrom(getRandomItemFromList());
    }

    public int getNumberOfProducts() {
        return getAllItems().size();
    }


    public List<String> getAllItemsName() {
        return productsNamesList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForProductsToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#products")));
    }

    public List<WebElement> getProductsPricesList() {
        return productsPricesList;
    }

    public List<BigDecimal> getProductPrices() {
        waitForProductsToLoad();
        List<WebElement> productsPricesList = getProductsPricesList();
        List<BigDecimal> list = new ArrayList<>();
        for (WebElement webElement : productsPricesList) {
            BigDecimal price = getPrice(webElement);
            list.add(price);
        }
        return list;
    }

    public String getTheFirstItemName() {
        wait.until(ExpectedConditions.visibilityOf(productsNamesList.get(0)));
        return productsNamesList.get(0).getText();
    }

    public WebElement getFilterBox() {
        waitUntilFilterIsVisible();
        return filtersSideMenu;
    }

    public void waitUntilFilterIsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#search_filters")));
    }

    public int getNumberOfProductsFromProductCountLabel() {
        WebElement productCountLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-md-6.hidden-sm-down.total-products")));
        return Integer.parseInt(productCountLabel.getText().replaceAll("\\D", ""));
    }


}
