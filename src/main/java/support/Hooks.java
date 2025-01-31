package support;

import global.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setup() {
        // Initialize a new WebDriver instance for each scenario
        driver = DriverManager.getDriver();
    }

    @After
    public void teardown() {
        // Quit the WebDriver instance after each scenario
        DriverManager.quitDriver(driver);
    }
}