package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class BreedListAllUtils {
    private static JSONObject breedsDataset;
    static {
        try {
            String datasetJson = new String(Files.readAllBytes(Paths.get("data_sets/dataset_breeds.json")));
            breedsDataset = new JSONObject(datasetJson).getJSONObject("message");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load breeds dataset", e);
        }
    }

    public static Response getBreedListAll() {
        return RestAssured.given()
                .baseUri(Endpoints.BASE_URI)
                .when()
                .get(Endpoints.BREEDS_LIST_ALL)
                .then()
                .extract().response();
    }

    public static void assertBreedListAllResponse(Response response) {
        assertEquals(200, response.statusCode(), "Status code should be 200");
        JSONObject body = new JSONObject(response.asString());
        assertEquals("success", body.getString("status"), "'status' field should be 'success'");
        assertNotNull(body.getJSONObject("message"), "'message' field should not be null");
        JSONObject breeds = body.getJSONObject("message");
        assertEquals(breedsDataset.keySet(), breeds.keySet(), "Returned breed list does not match the expected dataset");
    }
}
