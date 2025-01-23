package steps;

import global.Config;
import global.Utilities;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import global.DriverManager;
import runner.TestRunner;

import java.util.List;
import java.util.Map;

public class StepDefinitions {
    WebDriver driver = DriverManager.getDriver();
    Utilities utilities = new Utilities(driver);

    private Config config = TestRunner.getConfig();

    @Given("user is on the home page")
    public void userIsOnTheHomePage() {
        if (config == null || config.getUi() == null) {
            throw new IllegalStateException("Configuration is not initialized!");
        }
        String homeURL = (String) config.getUi().get("homeURL");
        System.out.println("Navigating to: " + homeURL);
        driver.get(homeURL);
    }

    @Given("^user is on the \"([^\"]*)\" page$")
    public void userIsOnThePage(String pageName) {
        if (config == null || config.getUi() == null) {
            throw new IllegalStateException("Configuration is not initialized!");
        }

        String url;
        switch (pageName.toLowerCase()) {
            case "home":
                url = "https://www.saucedemo.com/";
                break;
            case "login":
                url = "https://example.com/login";
                break;
            case "dashboard":
                url = "https://example.com/dashboard";
                break;
            case "google":
                url = "https://www.google.com/";
                break;
            default:
                throw new IllegalArgumentException("Invalid page name: " + pageName);
        }

        driver.get(url);
        System.out.println("Navigated to: " + url);
    }

    @When("user enters {string} on object {string} on {string} page")
    public void userEntersText(String text, String objectName, String pageName) {
        utilities.enterText(objectName, pageName, text);
    }

    @When("user clicks on object {string} on {string} page")
    public void userClicksOnObject(String objectName, String pageName) {
        utilities.clickOnObject(objectName, pageName);
    }

    @When("user handles the alert by {string} with text {string}")
    public void userHandlesTheAlertByActionWithText(String action, String text) {
        try {
            String result = utilities.handleAlert(action, text);

            if ("gettext".equalsIgnoreCase(action)) {
                System.out.println("Alert text retrieved: " + result);
            }
        } catch (Exception e) {
            System.err.println("Failed to handle the alert: " + e.getMessage());
        }
    }

    @When("user waits for {int} seconds")
    public void userWaitsForSeconds(int seconds) {
        try {
            System.out.println("Waiting for " + seconds + " seconds...");
            Thread.sleep(seconds * 1000L); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted during sleep: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    @When("I search for the following items:")
    public void iSearchForTheFollowingItems(DataTable dataTable) {
        // Convert DataTable to a List of Maps
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> item : items) {
            String searchItem = item.get("Item");
            String category = item.get("Category");

            System.out.println("Searching for item: " + searchItem + " in category: " + category);
            // Code to perform the search
        }
    }
}
