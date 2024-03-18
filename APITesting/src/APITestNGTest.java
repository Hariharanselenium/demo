import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class APITestNGTest {

    private static String baseUrl = "https://gorest.co.in";
    private static String endpoint = "/public/v2/users";
    private static String bearerToken = "730e722e5968aec3305b6d65dbeac1694ea4e37bd8a8d4d9f6bb124f1d64f0b2";
    private static String userId;
    
//	 String pattern = "yyyyMMddHHmmss";
//     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//     String dateName = LocalDateTime.now().format(formatter);
//     String email = "priya"+dateName+"@gamil.com";
     String randomEmail = UUID.randomUUID().toString() + "@example.com";

    @Test(priority = 1)
    public void testPostRequest() {
    	SoftAssert softAssert = new SoftAssert();
        // Request body JSON for POST request
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "priya");
        requestBody.put("gender", "female");
//        requestBody.put("email", "priya25@gmail.com");
        requestBody.put("email",randomEmail );
        requestBody.put("status", "active");

        // Perform the POST request
        HttpResponse postResponse = performPostRequest(baseUrl, endpoint, bearerToken, requestBody);
        softAssert.assertEquals(postResponse.getStatusCode(), 201);
         System.out.println("POST Status Code: " + postResponse.getStatusCode());
         System.out.println("POST Response Body: " + postResponse.getBody());
        // Extract ID from the POST response
        JSONObject responseJson = new JSONObject(postResponse.getBody());
        userId = String.valueOf(responseJson.getInt("id"));
        softAssert.assertAll();
    }

    @Test(priority = 2, dependsOnMethods = "testPostRequest")
    public void testPutRequest() {
    	SoftAssert softAssert = new SoftAssert();
        // Modify the response body as needed (PUT Method)
        JSONObject modifiedJson = new JSONObject();
        modifiedJson.put("name", "riya");
        String modifiedResponseBody = modifiedJson.toString();

        // Perform the PUT request
        HttpResponse putResponse = performPutRequest(baseUrl, endpoint, bearerToken, userId, modifiedResponseBody);
        softAssert.assertEquals(putResponse.getStatusCode(), 200);
        System.out.println("PUT Status Code: " + putResponse.getStatusCode());
        System.out.println("PUT Response Body: " + putResponse.getBody());
        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = "testPutRequest")
    public void testGetRequest() {
    	SoftAssert softAssert = new SoftAssert();
        // Perform the GET request
        HttpResponse getResponse = performGetRequest(baseUrl, endpoint, bearerToken, userId);
        softAssert.assertEquals(getResponse.getStatusCode(), 202);
        System.out.println("GET Status Code: " + getResponse.getStatusCode());
        System.out.println("GET Response Body: " + getResponse.getBody());
        softAssert.assertAll();
    }

    @Test(priority = 4, dependsOnMethods = "testGetRequest")
    public void testDeleteRequest() {
    	SoftAssert softAssert = new SoftAssert();
        // Perform the DELETE request
        int deleteStatusCode = performDeleteRequest(baseUrl, endpoint, bearerToken, userId);
        softAssert.assertEquals(deleteStatusCode, 204);
        System.out.println("DELETE Status Code: " + deleteStatusCode);
        softAssert.assertAll();
    }

    private static HttpResponse performPostRequest(String baseUrl, String endpoint, String bearerToken, JSONObject requestBody) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl + endpoint);

        StringEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Authorization", "Bearer " + bearerToken);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            int statusCode = response.getStatusLine().getStatusCode();
            return new HttpResponse(statusCode, responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse(-1, null);
        }
    }

    private static HttpResponse performPutRequest(String baseUrl, String endpoint, String bearerToken, String userId, String modifiedRequestBody) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(baseUrl + endpoint + "/" + userId);

        StringEntity requestEntity = new StringEntity(modifiedRequestBody, ContentType.APPLICATION_JSON);
        httpPut.setEntity(requestEntity);
        httpPut.setHeader("Authorization", "Bearer " + bearerToken);

        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            int statusCode = response.getStatusLine().getStatusCode();
            return new HttpResponse(statusCode, responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse(-1, null);
        }
    }

    private static HttpResponse performGetRequest(String baseUrl, String endpoint, String bearerToken, String userId) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + endpoint + "/" + userId);

        httpGet.setHeader("Authorization", "Bearer " + bearerToken);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            int statusCode = response.getStatusLine().getStatusCode();
            return new HttpResponse(statusCode, responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse(-1, null);
        }
    }

    private static int performDeleteRequest(String baseUrl, String endpoint, String bearerToken, String userId) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(baseUrl + endpoint + "/" + userId);

        httpDelete.setHeader("Authorization", "Bearer " + bearerToken);

        try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static class HttpResponse {
        private final int statusCode;
        private final String body;

        public HttpResponse(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getBody() {
            return body;
        }
    }
}

