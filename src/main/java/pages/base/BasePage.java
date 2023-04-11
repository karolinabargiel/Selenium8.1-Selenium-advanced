package pages.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Long.parseLong(System.getProperty("browserTimeout")));
        actions = new Actions(driver);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void inputText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public BigDecimal getPrice(WebElement element) {
        return new BigDecimal(element.getText().replace("$", ""));
    }

    public void clearAndInputText(WebElement element, String text) {
        element.clear();
        if (element.getText().length() != 0) {
            element.sendKeys(Keys.ALT + "a" + Keys.DELETE);
        } else {
            element.sendKeys(text);
        }
    }

    public void takeScreenshot() {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File("src/main/resources/" + System.currentTimeMillis()+" screenshot.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
