package global;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverManager {
    // Remove the static WebDriver instance
    // Each call to getDriver() will create a new instance
    public static WebDriver getDriver() {
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
        return new ChromeDriver(options);
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            System.out.println("Quitting WebDriver...");
            driver.quit();
        } else {
            System.out.println("Driver is already null, no action taken.");
        }
    }
}