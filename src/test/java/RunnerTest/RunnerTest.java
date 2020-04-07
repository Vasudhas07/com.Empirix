package RunnerTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = "src/test/resources/Features"
, tags = {
    "@Automated"}, snippets = SnippetType.CAMELCASE, monochrome = true, plugin = {
    		"pretty", "json:target/cucumber-report-html/cucumber.json"})

public class RunnerTest {

}
