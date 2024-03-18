import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class APIRestAssured {
	private String token;
    private String email;
    private String userId;

    @BeforeClass
    public void setup() {
        // Replace "YOUR_TOKEN" with the actual token value
        token = "d1e624f80a168c16c53c80db76f14042a00514f7e27a8e036dff947dc2155a59";

        String dateName = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());

        email = "riyaritz"+dateName+"@gmail.com";
        userId = null;
    }

    @Test(priority = 1)
    public void createNewUser() {
    	SoftAssert softAssert = new SoftAssert();
        JSONObject postRequestBody = new JSONObject();
        postRequestBody.put("name", "Priyadharshini");
        postRequestBody.put("email", email);
        postRequestBody.put("gender", "male");
        postRequestBody.put("status", "active");

        Response postResponse = given()
        		.baseUri("https://gorest.co.in")
                .basePath("/public/v2/users")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(postRequestBody.toString())
                .when()
                .post();

        String postResponseBody = postResponse.getBody().asString();
        System.out.println("POST Response Body: " + postResponseBody);

        int postStatusCode = postResponse.getStatusCode();
        softAssert.assertEquals(postResponse.getStatusCode(), 201, "POST request failed. Status code is not 201.");
        System.out.println("POST Status Code: " + postStatusCode);

        JSONObject postResponseJson = new JSONObject(postResponseBody);
        userId = String.valueOf(postResponseJson.getInt("id"));
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void updateUser() {
    	SoftAssert softAssert = new SoftAssert();
        JSONObject putRequestBody = new JSONObject();
        putRequestBody.put("name", "Riya Ritz");
        putRequestBody.put("email", email);
        putRequestBody.put("gender", "Female");
        putRequestBody.put("status","active");

        Response putResponse = given()
                .baseUri("https://gorest.co.in")
                .basePath("/public/v2/users/" + userId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(putRequestBody.toString())
                .when()
                .put();

        String putResponseBody = putResponse.getBody().asString();
        System.out.println("PUT Response Body: " + putResponseBody);

        int putStatusCode = putResponse.getStatusCode();
        softAssert.assertEquals(putResponse.getStatusCode(), 200, "PUT request failed. Status code is not 200.");
        System.out.println("PUT Status Code: " + putStatusCode);
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void getUser() {
    	SoftAssert softAssert = new SoftAssert();
        Response getResponse = given()
                .baseUri("https://gorest.co.in")
                .basePath("/public/v2/users/" + userId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get();

        String getResponseBody = getResponse.getBody().asString();
        System.out.println("GET Response Body: " + getResponseBody);

        int getStatusCode = getResponse.getStatusCode();
        softAssert.assertEquals(getResponse.getStatusCode(), 200, "GET request failed. Status code is not 202.");
        System.out.println("GET Status Code: " + getStatusCode);
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void deleteUser() {
    	SoftAssert softAssert = new SoftAssert();
        Response deleteResponse = given()
                .baseUri("https://gorest.co.in")
                .basePath("/public/v2/users/" + userId)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete();

        int deleteStatusCode = deleteResponse.getStatusCode();
        softAssert.assertEquals(deleteStatusCode, 204, "DELETE request failed. Status code is not 204.");
        System.out.println("DELETE Status Code: " + deleteStatusCode);
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        // Perform any necessary cleanup after all the test methods have been executed.
    }
}
