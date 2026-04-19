package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import support.BreedsImageRandomUtils;

public class BreedsImageRandomTests {
    @Test
    public void testSingleRandomImageFromAllDogsCollection() {
        Response response = BreedsImageRandomUtils.getSingleRandomImage();
        BreedsImageRandomUtils.assertSingleImageResponse(response);
    }

    @Test
    public void testThreeRandomImagesFromAllDogsCollection() {
        Response response = BreedsImageRandomUtils.getThreeRandomImages();
        BreedsImageRandomUtils.assertThreeImagesResponse(response);
    }

    @Test
    public void testFiftyOneRandomImagesFromAllDogsCollection() {
        Response response = BreedsImageRandomUtils.getFiftyOneRandomImages();
        BreedsImageRandomUtils.assertFiftyImagesResponse(response);
    }

    @Test
    public void testRandomImagesNegative() {
        Response response = BreedsImageRandomUtils.getRandomImagesNegative();
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }

    @Test
    public void testRandomImagesA() {
        Response response = BreedsImageRandomUtils.getRandomImagesA();
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }

    @Test
    public void testRandomImagesZero() {
        Response response = BreedsImageRandomUtils.getRandomImagesZero();
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }

    @Test
    public void testRandomImagesAt() {
        Response response = BreedsImageRandomUtils.getRandomImagesAt();
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }

}
