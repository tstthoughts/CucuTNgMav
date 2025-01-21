package global;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;

public class Utilities {
    WebDriver driver;

    public Utilities(WebDriver driver) {
        this.driver = driver;
    }

    // Click on an element
    public void clickOnObject(String objectName, String pageName) {
        WebElement element = getWebElement(objectName, pageName);
        element.click();
    }

    // Enter text into an element
    public void enterText(String objectName, String pageName, String text) {
        WebElement element = getWebElement(objectName, pageName);
        element.clear();
        element.sendKeys(text);
    }

    // Select an option from a dropdown
    public void selectDropdown(String objectName, String pageName, String option) {
        WebElement element = getWebElement(objectName, pageName);
        new org.openqa.selenium.support.ui.Select(element).selectByVisibleText(option);
    }

    // Dynamically get the WebElement based on page and object name
    private WebElement getWebElement(String objectName, String pageName) {
        try {
            String className = "locators." + pageName + "Locators";
            Class<?> locatorClass = Class.forName(className);
            Field field = locatorClass.getDeclaredField(objectName);
            By locator = (By) field.get(null); // Static field
            return driver.findElement(locator);
        } catch (Exception e) {
            throw new RuntimeException("Error accessing locator: " + e.getMessage());
        }
    }

    /**
     * Handles browser alerts based on the provided action.
     *
     * @param action   The action to perform: "accept", "dismiss", "gettext", or "sendtext".
     * @param text     The text to send to the alert (only applicable for "sendtext" action).
     * @return The alert text if the action is "gettext"; otherwise, returns null.
     */
    public String handleAlert(String action, String text) {
        Alert alert = waitForAlert();
        if (alert == null) {
            System.out.println("No alert present to handle.");
            return null;
        }

        switch (action.toLowerCase()) {
            case "accept":
                System.out.println("Accepting alert with text: " + alert.getText());
                alert.accept();
                return null;

            case "dismiss":
                System.out.println("Dismissing alert with text: " + alert.getText());
                alert.dismiss();
                return null;

            case "gettext":
                String alertText = alert.getText();
                System.out.println("Alert text: " + alertText);
                return alertText;

            case "sendtext":
                if (text != null) {
                    System.out.println("Sending text to alert: " + text);
                    alert.sendKeys(text);
                    alert.accept();
                } else {
                    System.out.println("No text provided for 'sendtext' action.");
                }
                return null;

            default:
                System.out.println("Invalid action specified: " + action);
                return null;
        }
    }

    /**
     * Waits for an alert to be present.
     *
     * @return The alert object, or null if no alert is present within the timeout.
     */
    private Alert waitForAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            System.out.println("No alert present within the timeout.");
            return null;
        }
    }
}
