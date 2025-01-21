package global;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIUtils {

    private static RequestSpecification request;

    /**
     * Set up the base URI for the API.
     * @param baseURI The base URI.
     */
    public static void setupBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
        request = RestAssured.given().relaxedHTTPSValidation();
    }

    /**
     * Add headers to the API request.
     * @param headers Map of headers.
     */
    public static void addHeaders(Map<String, String> headers) {
        if (headers != null) {
            request.headers(headers);
        }
    }

    /**
     * Send a GET request.
     * @param endpoint The API endpoint.
     * @return Response object.
     */
    public static Response sendGetRequest(String endpoint) {
        return request.get(endpoint);
    }

    /**
     * Send a POST request with payload.
     * @param endpoint The API endpoint.
     * @param payload The request payload.
     * @return Response object.
     */
    public static Response sendPostRequest(String endpoint, Object payload) {
        System.out.println("payload:"+payload);
        return request.body(payload).post(endpoint);
    }

    /**
     * Validate the status code.
     * @param response The API response.
     * @param expectedStatusCode The expected status code.
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            throw new AssertionError("Expected status code: " + expectedStatusCode +
                    ", but got: " + response.statusCode());
        }
        System.out.println("response.statusCode(): "+ response.statusCode());
    }

    /**
     * Validate a JSON field in the response.
     * @param response The API response.
     * @param jsonPath The JSON path of the field.
     * @param expectedValue The expected value of the field.
     */
    public static void validateJSONField(Response response, String jsonPath, Object expectedValue) {
        // Log the entire response body
        String responseBody = response.getBody().asString();
        System.out.println("Full Response: \n" + responseBody);

        // Get the actual value from the response using the provided JSON path
        Object actualValue = response.jsonPath().get(jsonPath);

        // Log the JSON path and the corresponding actual value
        System.out.println("Validating JSON path: " + jsonPath);
        System.out.println("Actual value from response: " + actualValue);

        // Compare the expected and actual values as strings
        if (actualValue == null || !String.valueOf(actualValue).equals(String.valueOf(expectedValue))) {
            throw new AssertionError("Validation failed for JSON path: " + jsonPath +
                    ". Expected value: " + expectedValue +
                    ", but got: " + actualValue);
        }

        // Print success message
        System.out.println("Validation successful for JSON path: " + jsonPath +
                ". Expected and actual values match: " + actualValue);
    }

}
