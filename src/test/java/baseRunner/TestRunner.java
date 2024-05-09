package baseRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
		plugin="json:target/jsonReports/cucumber-report.json",
		glue= {"stepDefinitions"},
		tags= "AddPlace")
public class TestRunner {

}
