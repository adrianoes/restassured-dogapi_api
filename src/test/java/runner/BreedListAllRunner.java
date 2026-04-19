package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/breedlistall.feature",
    glue = {"stepdefinitions.breedlistall"},
    plugin = {"pretty", "html:target/cucumber-reports/breedlistall.html"},
    tags = "@BREEDLISTALL"
)
public class BreedListAllRunner {
}
