package stepdefinitions.breedlistall;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import support.BreedListAllUtils;

public class BreedListAllSteps {
    private Response response;

    @When("The user sends a GET request to \\/breeds\\/list\\/all")
    public void theUserSendsAGETRequestToBreedsListAll() {
        response = BreedListAllUtils.getBreedListAll();
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        BreedListAllUtils.assertBreedListAllResponse(response);
    }

    @And("the 'status' field should be 'success'")
    public void the_status_field_should_be_success() {
        BreedListAllUtils.assertBreedListAllResponse(response);
    }

    @And("the 'message' field should contain all breeds from the dataset")
    public void the_message_field_should_contain_all_breeds() {
        BreedListAllUtils.assertBreedListAllResponse(response);
    }
}
