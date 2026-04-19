package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;

public class BreedImagesUtils {
    public static void assertBreedImagesArray(Response response, int expectedCount) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        assertTrue(body.has("message"));
        JSONArray images = body.getJSONArray("message");
        assertEquals(expectedCount, images.length(), "Returned image count does not match");
        for (int i = 0; i < images.length(); i++) {
            String imageUrl = images.getString(i);
            assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "Returned image does not start with expected prefix");
            assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
        }
    }

    public static void assertBreedImagesString(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        String imageUrl = body.getString("message");
        assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "Returned image does not start with expected prefix");
        assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
    }

    public static Response getBreedImages(String breed) {
        return RestAssured.get(Endpoints.BASE_URI + "/api/breed/" + breed + "/images");
    }

    public static Response getBreedImagesRandom(String breed) {
        return RestAssured.get(Endpoints.BASE_URI + "/api/breed/" + breed + "/images/random");
    }

    public static Response getBreedImagesRandomN(String breed, String n) {
        return RestAssured.get(Endpoints.BASE_URI + "/api/breed/" + breed + "/images/random/" + n);
    }

    public static void assertBreedImagesSuccess(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        assertTrue(body.has("message"));
        JSONArray images = body.getJSONArray("message");
        assertTrue(images.length() > 0, "Should return at least one image");
        for (int i = 0; i < images.length(); i++) {
            String imageUrl = images.getString(i);
            assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "Returned image does not start with expected prefix");
            assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
        }
    }

    public static void assertBreedImagesError(Response response, int expectedStatus, String expectedMessage) {
        assertEquals(expectedStatus, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("error", body.getString("status"));
        assertEquals(expectedMessage, body.get("message"));
    }

    public static void assertBreedImagesRandomN51(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        JSONArray images = body.getJSONArray("message");
        assertEquals(51, images.length(), "Should return exactly 51 images, as now the expected is 51");
        java.util.List<String> expectedImages = support.BreedDatasetHelper.getImagesForBreedContaining("clumber");
        for (int i = 0; i < images.length(); i++) {
            String imageUrl = images.getString(i);
            assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "Returned image does not start with expected prefix");
            assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
            boolean found = expectedImages.stream().anyMatch(img -> imageUrl.endsWith("/" + img));
            assertTrue(found, "Returned image is not in the dataset for breed: clumber");
        }
    }

    public static void assertBreedImagesRandomA(Response response) {
        assertEquals(404, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("error", body.getString("status"));
        String msg = body.has("message") ? body.get("message").toString() : "";
        assertFalse(msg.contains(Endpoints.BASE_URI + "/breeds/"), "Message should not contain image URL");
    }

    public static void assertBreedImagesRandom(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        String imageUrl = body.getString("message");
        assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "Returned image does not start with expected prefix");
        assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
    }
}
