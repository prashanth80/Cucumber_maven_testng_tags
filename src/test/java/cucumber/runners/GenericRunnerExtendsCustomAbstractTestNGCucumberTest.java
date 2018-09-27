package cucumber.runners;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/cucumber/features"},
        glue = {"cucumber.steps"},
        format = {
                "pretty",
                "json:target/cucumber_reports/CucumberReport.json",
                "html:target/cucumber_reports/cucumber-pretty",
                "rerun:target/cucumber_reports/rerun.txt"
        }
)
public class GenericRunnerExtendsCustomAbstractTestNGCucumberTest extends CustomAbstractTestNGCucumberTests {

}
