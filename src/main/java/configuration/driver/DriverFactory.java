package configuration.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverFactory {
    private WebDriver driver;

    public WebDriver getDriver() {
        switch (Browser.valueOf(System.getProperty("browser").toUpperCase()) ) {
            case FIREFOX -> driver = getFirefoxDriver();
            case EDGE -> driver = getEdgeDriver();
            case IE -> driver = getIEDriver();
            default -> driver = getChromeDriver();
        }
        return driver;
    }

    private WebDriver getChromeDriver() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        return new ChromeDriver(optionsChrome);
    }

    private WebDriver getFirefoxDriver() {
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        return new FirefoxDriver(optionsFirefox);
    }

    private WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver getIEDriver() {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }


}
