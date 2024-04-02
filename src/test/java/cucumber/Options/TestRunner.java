package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		// Provide feature file package path here
		features = "src/test/java/features",plugin="json:target/jsonReports/cucumber-report.json",
		glue = {"stepDefinations"})
		// Provide StepDefination package path here to link


public class TestRunner {

}
