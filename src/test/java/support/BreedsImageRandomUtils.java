
package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.*;

public class BreedsImageRandomUtils {
    public static Response getSingleRandomImage() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM);
    }
    public static void assertSingleImageResponse(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        String imageUrl = body.getString("message");
        assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "URL does not start with https://images.dog.ceo/breeds/");
        assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
    }

    public static Response getThreeRandomImages() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_3);
    }
    public static void assertThreeImagesResponse(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        JSONArray images = body.getJSONArray("message");
        assertEquals(3, images.length());
        for (int i = 0; i < images.length(); i++) {
            String imageUrl = images.getString(i);
            assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "URL não começa com https://images.dog.ceo/breeds/");
            assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
        }
    }

    public static Response getFiftyOneRandomImages() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_51);
    }
    public static void assertFiftyImagesResponse(Response response) {
        assertEquals(200, response.statusCode());
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"));
        JSONArray images = body.getJSONArray("message");
        assertEquals(50, images.length(), "Should return exactly 50 images, even when requesting 51");
        for (int i = 0; i < images.length(); i++) {
            String imageUrl = images.getString(i);
            assertTrue(imageUrl.startsWith("https://images.dog.ceo/breeds/"), "URL does not start with https://images.dog.ceo/breeds/");
            assertTrue(imageUrl.matches("https://images\\.dog\\.ceo/breeds/.+\\.(jpg|jpeg|png)"), "Returned image is not a valid image URL");
        }
    }

    public static Response getRandomImagesNegative() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_NEGATIVE);
    }

    public static Response getRandomImagesA() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_A);
    }

    public static Response getRandomImagesZero() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_ZERO);
    }

    public static Response getRandomImagesAt() {
        return RestAssured.get(Endpoints.BASE_URI + Endpoints.BREEDS_IMAGE_RANDOM_AT);
    }

    public static void assertInvalidRandomImagesResponse(Response response) {
        JSONObject body = new JSONObject(response.asString());
        assertEquals(400, response.statusCode(), "Status code should be 400");
        assertEquals("error", body.getString("status"), "Status field should be 'error'");
        String msg = body.has("message") ? body.get("message").toString() : "";
        assertFalse(msg.contains(Endpoints.BASE_URI + "/breeds/"), "Message should not contain image URL");
    }

    public static void assertStatusError(Response response) {
        JSONObject body = new JSONObject(response.asString());
        assertEquals("error", body.getString("status"), "Status field deveria ser 'error'");
    }
}
