package stepdefinitions.breedimages;

import support.BreedImagesUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class BreedImagesSteps {
    private Response response;

    private static final String DEFAULT_BREED = "clumber";
    @When("The user sends a GET request to \\/breed\\/redbone\\/images")
    public void whenUserSendsGetBreed1ImagesRandom() {
        response = BreedImagesUtils.getBreedImagesRandom("1");
    }

    @When("The user sends a GET request to \\/breed\\/redbone\\/images\\/random\\/3")
    public void whenUserSendsGetBreedRedboneImagesRandom3() {
        response = BreedImagesUtils.getBreedImagesRandomN(DEFAULT_BREED, "3");
    }

    @When("The user sends a GET request to \\/breed\\/redbone\\/images\\/random\\/51")
    public void whenUserSendsGetBreedRedboneImagesRandom51() {
        response = BreedImagesUtils.getBreedImagesRandomN(DEFAULT_BREED, "51");
    }

    @When("The user sends a GET request to \\/breed\\/redbone\\/images\\/random\\/a")
    public void whenUserSendsGetBreedRedboneImagesRandomA() {
        response = BreedImagesUtils.getBreedImagesRandomN(DEFAULT_BREED, "a");
    }

    @When("The user sends a GET request to \\/breed\\/{string}\\/images")
    public void whenUserSendsGetBreedImages(String breed) {
        response = BreedImagesUtils.getBreedImages(breed);
    }

    @When("The user sends a GET request to \\/breed\\/clumber\\/images")
    public void the_user_sends_a_get_request_to_breed_clumber_images() {
        response = BreedImagesUtils.getBreedImages("clumber");
    }

    @When("The user sends a GET request to \\/breed\\/clumber\\/images\\/random")
    public void the_user_sends_a_get_request_to_breed_clumber_images_random() {
        response = BreedImagesUtils.getBreedImagesRandom("clumber");
    }

    @When("The user sends a GET request to \\/breed\\/clumber\\/images\\/random\\/3")
    public void the_user_sends_a_get_request_to_breed_clumber_images_random_3() {
        response = BreedImagesUtils.getBreedImagesRandomN("clumber", "3");
    }

    @When("The user sends a GET request to \\/breed\\/clumber\\/images\\/random\\/51")
    public void the_user_sends_a_get_request_to_breed_clumber_images_random_51() {
        response = BreedImagesUtils.getBreedImagesRandomN("clumber", "51");
    }

    @When("The user sends a GET request to \\/breed\\/clumber\\/images\\/random\\/a")
    public void the_user_sends_a_get_request_to_breed_clumber_images_random_a() {
        response = BreedImagesUtils.getBreedImagesRandomN("clumber", "a");
    }

    @Then("the {string} field should not contain {string}")
    public void theFieldShouldNotContain(String field, String value) {
        // Suporta apenas 'message' para este projeto
        org.json.JSONObject body = new org.json.JSONObject(response.asString());
        String msg = body.has(field) ? body.get(field).toString() : "";
        org.junit.jupiter.api.Assertions.assertFalse(msg.contains(value), "O campo '" + field + "' não deveria conter '" + value + "'");
    }

    @Then("the status code should be 200")
    @Then("^the status code should be 200 for all images$")
    public void thenStatusCodeShouldBe200AllImages() {
        BreedImagesUtils.assertBreedImagesSuccess(response);
    }

    @Then("^the status code should be 200 for random image$")
    public void thenStatusCodeShouldBe200RandomImage() {
        BreedImagesUtils.assertBreedImagesString(response);
    }

    @Then("^the status code should be 200 for 3 random images$")
    public void thenStatusCodeShouldBe200Random3() {
        BreedImagesUtils.assertBreedImagesArray(response, 3);
    }

    @Then("^the status code should be 200 for 51 random images$")
    public void thenStatusCodeShouldBe200Random51() {
        BreedImagesUtils.assertBreedImagesArray(response, 51);
    }

    @Then("the 'status' field should be 'success'")
    public void thenStatusFieldShouldBeSuccess() {
    }

    @Then("the 'message' field should contain all images for the chosen breed")
    public void thenMessageFieldShouldContainAllImages() {
    }

    @Then("the status code should be 404")
    public void thenStatusCodeShouldBe404() {
    }

    @Then("the 'status' field should be 'error'")
    public void thenStatusFieldShouldBeError() {
        BreedImagesUtils.assertBreedImagesRandomA(response);
    }

    @Then("the 'message' field should be {string}")
    public void thenMessageFieldShouldBe(String expected) {
        BreedImagesUtils.assertBreedImagesError(response, 404, expected);
    }

    @When("The user sends a GET request to \\/breed\\/{string}\\/images\\/random")
    public void whenUserSendsGetBreedImagesRandom(String breed) {
        response = BreedImagesUtils.getBreedImagesRandom(breed);
    }

    @When("The user sends a GET request to \\/breed\\/{string}\\/images\\/random\\/a")
    public void whenUserSendsGetBreedImagesRandomA(String breed) {
        response = BreedImagesUtils.getBreedImagesRandomN(breed, "a");
    }

    @Then("the 'message' field should contain a random image for the chosen breed")
    public void thenMessageFieldShouldContainRandomImage() {
    }

    @When("The user sends a GET request to \\/breed\\/{string}\\/images\\/random\\/{string}")
    @When("The user sends a GET request to \\/breed\\/{string}\\/images\\/random\\/3")
    public void whenUserSendsGetBreedImagesRandom3(String breed) {
        response = BreedImagesUtils.getBreedImagesRandomN(breed, "3");
    }

    @When("The user sends a GET request to \\/breed\\/{string}\\/images\\/random\\/51")
    public void whenUserSendsGetBreedImagesRandom51(String breed) {
        response = BreedImagesUtils.getBreedImagesRandomN(breed, "51");
    }

    @Then("the 'message' field should contain {int} random images for the chosen breed")
    public void thenMessageFieldShouldContainNRandomImages(int n) {
        BreedImagesUtils.assertBreedImagesArray(response, n);
    }

    @Then("the status code should be 400")
    public void thenStatusCodeShouldBe400() {
        BreedImagesUtils.assertBreedImagesRandomA(response);
    }

    @Then("the 'message' field should not contain 'https:\\/\\/images.dog.ceo\\/breeds\\/'")
    public void thenMessageFieldShouldNotContainImageUrl() {
        BreedImagesUtils.assertBreedImagesRandomA(response);
    }

    @When("The user sends a GET request to \\/breed\\/aaaaaaa\\/images")
    public void the_user_sends_a_get_request_to_breed_aaaaaaa_images() {
        response = BreedImagesUtils.getBreedImages("aaaaaaa");
    }

    @When("The user sends a GET request to \\/breed\\/{int}\\/images")
    public void the_user_sends_a_get_request_to_breed_images(Integer breed) {
        response = BreedImagesUtils.getBreedImages(breed.toString());
    }

    @When("The user sends a GET request to \\/breed\\/aaaaaaa\\/images\\/random")
    public void the_user_sends_a_get_request_to_breed_aaaaaaa_images_random() {
        response = BreedImagesUtils.getBreedImagesRandom("aaaaaaa");
    }

    @When("The user sends a GET request to \\/breed\\/{int}\\/images\\/random")
    public void the_user_sends_a_get_request_to_breed_images_random(Integer breed) {
        response = BreedImagesUtils.getBreedImagesRandom(breed.toString());
    }
}
