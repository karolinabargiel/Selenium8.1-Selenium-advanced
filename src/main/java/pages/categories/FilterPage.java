package pages.categories;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;

public class FilterPage extends BasePage {

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".faceted-slider p")
    private WebElement priceFilterValues;
    @FindBy(css = ".ui-slider a:first-of-type")
    private WebElement leftSlider;
    @FindBy(css = ".ui-slider a:last-of-type")
    private WebElement rightSlider;
    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearAllFilters;

    public void moveLeftSliderToPrice(BigDecimal price) {
        moveSlider("left", price);
    }

    public void moveRightSliderToPrice(BigDecimal price) {
        moveSlider("right", price);
    }

    private void moveSlider(String direction, BigDecimal price) {
        BigDecimal sliderValue;
        WebElement slider;
        if (direction.equals("left")) {
            sliderValue = getLeftSliderValue();
        } else {
            sliderValue = getRightSliderValue();
        }

        if (direction.equals("left")) {
            slider = leftSlider;
        } else {
            slider = rightSlider;
        }
        if (price.compareTo(sliderValue) == 0) {
            return;
        }

        if (price.compareTo(sliderValue) > 0) {
            sendKeyToSlider(slider, price.subtract(sliderValue).doubleValue(), Keys.ARROW_RIGHT);
        } else {
            sendKeyToSlider(slider, sliderValue.subtract(price).doubleValue(), Keys.ARROW_LEFT);
        }
        waitForFilteredPageToReload();
    }

    private void sendKeyToSlider(WebElement element, double count, Keys key) {
        for (int i = 0; i < count; i++) {
            waitForFilteredPageToReload();
            element.sendKeys(key);
        }
    }

    public void waitForFilteredPageToReload() {
        wait.until((ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner"))));
    }

    public BigDecimal getLeftSliderValue() {
        return new BigDecimal(StringUtils.substringBetween(priceFilterValues.getText(), "$", " -"));
    }

    public BigDecimal getRightSliderValue() {
        return new BigDecimal(StringUtils.substringAfterLast(priceFilterValues.getText(), "$"));
    }

    public void clearFilters() {
        clearAllFilters.click();
        waitForFilteredPageToReload();
    }



}
