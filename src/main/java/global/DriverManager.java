package global;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Initializing WebDriver...");

            // Set up WebDriverManager for ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Create ChromeOptions to configure ChromeDriver
            ChromeOptions options = new ChromeOptions();

            // Add --user-data-dir with a unique path
            String userDataDir = System.getProperty("java.io.tmpdir") + "chrome-user-data-" + System.currentTimeMillis();
            new File(userDataDir).mkdirs(); // Create the directory if it doesn't exist
            options.addArguments("--user-data-dir=" + userDataDir);

            // Add other Chrome options for CI/CD environments
            options.addArguments("--headless"); // Run in headless mode
            options.addArguments("--disable-gpu"); // Disable GPU acceleration
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
            options.addArguments("--remote-allow-origins=*"); // Allow all origins for remote debugging

            // Initialize ChromeDriver with the options
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            System.out.println("Quitting WebDriver...");
            driver.quit();
            driver = null;
        } else {
            System.out.println("Driver is already null, no action taken.");
        }
    }
}