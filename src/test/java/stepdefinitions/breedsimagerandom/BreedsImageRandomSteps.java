package stepdefinitions.breedsimagerandom;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import support.BreedsImageRandomUtils;

public class BreedsImageRandomSteps {
    private Response response;

    @When("the user sends a GET request to \\/breeds\\/image\\/random")
    public void whenUserSendsGetRequestToBreedsImageRandom() {
        response = BreedsImageRandomUtils.getSingleRandomImage();
    }

    @Then("the status code should be 200 for single image")
    public void thenStatusCodeShouldBe200Single() {
        BreedsImageRandomUtils.assertSingleImageResponse(response);
    }

    @Then("the status code should be 200 for three images")
    public void thenStatusCodeShouldBe200Three() {
        BreedsImageRandomUtils.assertThreeImagesResponse(response);
    }

    @Then("the status code should be 200 for fifty images")
    public void thenStatusCodeShouldBe200Fifty() {
        BreedsImageRandomUtils.assertFiftyImagesResponse(response);
    }

    @And("the 'message' field should be 50 valid image URLs for the 50 chosen breeds")
    public void thenMessageFieldShouldBe50ValidImageUrlsForThe50ChosenBreeds() {
    BreedsImageRandomUtils.assertFiftyImagesResponse(response);
    }

    @And("the 'status' field should be 'success' for single image")
    public void thenStatusFieldShouldBeSuccessSingle() {
    }

    @And("the 'status' field should be 'success' for three images")
    public void thenStatusFieldShouldBeSuccessThree() {
    }

    @And("the 'status' field should be 'success' for fifty images")
    public void thenStatusFieldShouldBeSuccessFifty() {
    }

    @And("the 'message' field should be a valid image URL for the chosen breed")
    public void thenMessageFieldShouldBeAValidImageUrlForTheChosenBreed() {
        BreedsImageRandomUtils.assertSingleImageResponse(response);
    }

    @And("the 'message' field should be 3 valid image URLs for the 3 chosen breeds")
    public void thenMessageFieldShouldBe3ValidImageUrlsForThe3ChosenBreeds() {
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/3")
    public void whenUserSendsGetRequestToBreedsImageRandom3() {
        response = BreedsImageRandomUtils.getThreeRandomImages();
    }

    @And("the 'message' field should be 3 valid image URLs for the 3 chosen breed")
    public void thenMessageFieldShouldBe3ValidImageUrlsForThe3ChosenBreed() {
        BreedsImageRandomUtils.assertThreeImagesResponse(response);
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/51")
    public void whenUserSendsGetRequestToBreedsImageRandom51() {
        response = BreedsImageRandomUtils.getFiftyOneRandomImages();
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/-1")
    public void whenUserSendsGetRequestToBreedsImageRandomNegative() {
        response = BreedsImageRandomUtils.getRandomImagesNegative();
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/a")
    public void whenUserSendsGetRequestToBreedsImageRandomA() {
        response = BreedsImageRandomUtils.getRandomImagesA();
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/0")
    public void whenUserSendsGetRequestToBreedsImageRandomZero() {
        response = BreedsImageRandomUtils.getRandomImagesZero();
    }

    @When("the user sends a GET request to \\/breeds\\/image\\/random\\/@")
    public void whenUserSendsGetRequestToBreedsImageRandomAt() {
        response = BreedsImageRandomUtils.getRandomImagesAt();
    }

    @Then("the status code should be 400")
    public void thenStatusCodeShouldBe400() {
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }

    @And("the 'status' field should be 'error'")
    public void thenStatusFieldShouldBeError() {
        BreedsImageRandomUtils.assertStatusError(response);
    }

    @And("the 'message' field should not contain 'https:\\/\\/images.dog.ceo\\/breeds\\/'")
    public void thenMessageFieldShouldNotContainImageUrl() {
        BreedsImageRandomUtils.assertInvalidRandomImagesResponse(response);
    }
}
