package base;

import configuration.driver.DriverFactory;
import configuration.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


@Slf4j
public class TestBase {

    protected WebDriver driver;
    private static AppProperties appProperties;

    @BeforeAll
    static void setup() {
        appProperties = AppProperties.getInstance();

    }

    @BeforeEach
    void setupDriver() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.getDriver();
        driver.get(System.getProperty("appUrl"));
        log.debug("WebDriver is up and running");
    }

    @AfterEach
    void teardown() {
        driver.quit();
        log.debug("WebDriver is closed");
    }
}
