package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import support.BreedImagesUtils;
import support.BreedDatasetHelper;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BreedImagesTests {
    @Test
    public void testAllImagesFromValidBreed() {
        Response response = BreedImagesUtils.getBreedImages("clumber");
        BreedImagesUtils.assertBreedImagesSuccess(response);
        List<String> expectedImages = BreedDatasetHelper.getImagesForBreedContaining("clumber");
        List<String> actualImages = response.jsonPath().getList("message");
        java.util.List<String> notFound = new java.util.ArrayList<>();
        for (String imgUrl : actualImages) {
            boolean found = expectedImages.stream().anyMatch(img -> imgUrl.endsWith("/" + img));
            if (!found) {
                notFound.add(imgUrl);
            }
        }
        if (!notFound.isEmpty()) {
            try {
                java.nio.file.Files.write(
                    java.nio.file.Paths.get("reports/not_found_clumber.txt"),
                    notFound,
                    java.nio.charset.StandardCharsets.UTF_8
                );
            } catch (Exception e) {
                System.err.println("Erro ao salvar imagens não encontradas: " + e.getMessage());
            }
        }
    }

    @Test
    public void testRandomImageFromValidBreed() {
        Response response = BreedImagesUtils.getBreedImagesRandom("clumber");
        BreedImagesUtils.assertBreedImagesRandom(response);
        String imageUrl = response.jsonPath().getString("message");
        List<String> expectedImages = BreedDatasetHelper.getImagesForBreedContaining("clumber");
        boolean found = expectedImages.stream().anyMatch(img -> imageUrl.endsWith("/" + img));
        assertTrue(found, "Returned image is not in the dataset for breed: clumber");
    }

    @Test
    public void testMultipleRandomImagesFromValidBreed() {
        int n = 3;
        Response response = BreedImagesUtils.getBreedImagesRandomN("clumber", String.valueOf(n));
        BreedImagesUtils.assertBreedImagesSuccess(response);
        List<String> images = response.jsonPath().getList("message");
        assertEquals(n, images.size());
        List<String> expectedImages = BreedDatasetHelper.getImagesForBreedContaining("clumber");
        for (String image : images) {
            boolean found = expectedImages.stream().anyMatch(img -> image.endsWith("/" + img));
            assertTrue(found, "Returned image is not in the dataset for breed: clumber");
        }
    }

    @Test
    public void test51RandomImagesFromValidBreed() {
        Response response = BreedImagesUtils.getBreedImagesRandomN("clumber", "51");
        BreedImagesUtils.assertBreedImagesRandomN51(response);
    }

    @Test
    public void testAllImagesFromInvalidBreed_aaaaaaa() {
        Response response = BreedImagesUtils.getBreedImages("aaaaaaa");
        BreedImagesUtils.assertBreedImagesError(response, 404, "Breed not found (main breed does not exist)");
    }

    @Test
    public void testAllImagesFromInvalidBreed_1() {
        Response response = BreedImagesUtils.getBreedImages("1");
        BreedImagesUtils.assertBreedImagesError(response, 404, "Breed not found (main breed does not exist)");
    }

    @Test
    public void testRandomImageFromInvalidBreed_aaaaaaa() {
        Response response = BreedImagesUtils.getBreedImagesRandom("aaaaaaa");
        BreedImagesUtils.assertBreedImagesError(response, 404, "Breed not found (main breed does not exist)");
    }

    @Test
    public void testRandomImageFromInvalidBreed_1() {
        Response response = BreedImagesUtils.getBreedImagesRandom("1");
        BreedImagesUtils.assertBreedImagesError(response, 404, "Breed not found (main breed does not exist)");
    }

    @Test
    public void testRandomImagesWithInvalidN_FeatureScenario() {
        String n = "a";
        Response response = BreedImagesUtils.getBreedImagesRandomN("clumber", n);
        assertEquals(400, response.statusCode(), "Status should be 400 for invalid N: " + n);
        assertEquals("error", response.jsonPath().getString("status"), "Field 'status' should be 'error'");
        String msg = response.jsonPath().getString("message");
        assertFalse(msg != null && msg.contains("https://images.dog.ceo/breeds/"), "Message should not contain image URL for invalid N: " + n);
    }
}
