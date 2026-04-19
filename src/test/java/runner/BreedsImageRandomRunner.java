package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/breedsimagerandom.feature",
    glue = {"stepdefinitions.breedsimagerandom"},
    plugin = {"pretty", "html:target/cucumber-reports/breedsimagerandom-html"},
    tags = "@BREEDSIMAGERANDOM"
)
public class BreedsImageRandomRunner {
}
