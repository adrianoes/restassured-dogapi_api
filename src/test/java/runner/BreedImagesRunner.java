package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/breedimages.feature",
    glue = {"stepdefinitions.breedimages"},
    plugin = {"pretty", "json:target/cucumber-reports/breedimages.json", "html:target/cucumber-reports/breedimages-html"},
    tags = "@BREEDIMAGES"
)
public class BreedImagesRunner {
}
