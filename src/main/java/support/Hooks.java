package support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import global.DriverManager;

public class Hooks {
    @Before
    public void setup() {
        DriverManager.getDriver();
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
        System.out.println("Browser closed after test execution.");
    }
}
