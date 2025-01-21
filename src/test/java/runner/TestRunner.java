package runner;

import global.APIUtils;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import global.Config;
import global.ConfigReader;
import org.junit.Before;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = {"steps", "support"}, // Path to your step definition files and hooks
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json"},
        monochrome = true, // Ensures console output is readable
        tags = "@Smoke" // Specify the tag to execute
//        tags = "@Smoke and @Regression": Executes scenarios with both tags.
//        tags = "@Smoke or @Regression": Executes scenarios with either tag.
//        tags = "not @WIP": Excludes scenarios tagged as @WIP.
)
public class TestRunner extends AbstractTestNGCucumberTests {
    private static Config config;

    @BeforeClass
    public static void setUp() {
        String env = System.getProperty("env", "Dev");
        try {
            config = ConfigReader.getConfigForEnv(env);
            APIUtils.setupBaseURI(config.getApi().get("baseURI").toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration for environment: " + env, e);
        }
    }

    public static Config getConfig() {
        return config;
    }
}