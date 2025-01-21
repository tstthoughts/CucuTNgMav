package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import runner.TestRunner;
import global.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class APIValidationSteps {

    private Response response;

    @When("user sends a GET request to {string}")
    public void userSendsAGETRequestTo(String endpoint) {
        response = APIUtils.sendGetRequest(endpoint);
    }

    @When("user sends a POST request to {string} with payload")
    public void userSendsAPOSTRequestToWithPayload(String endpoint, Map<String, String> payload) {
        response = APIUtils.sendPostRequest(endpoint, payload);
    }

    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int statusCode) {
        APIUtils.validateStatusCode(response, statusCode);
    }

    @Then("response should contain {string} with value {string}")
    public void responseShouldContainWithValue(String jsonPath, String expectedValue) {
        APIUtils.validateJSONField(response, jsonPath, expectedValue);
    }
}
