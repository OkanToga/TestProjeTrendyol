package runners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utils.ExtentReportManager;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "stepdef",
        plugin = {"pretty",
                  "html:target/cucumber-reports.html",
                  "json:target/cucumber.json","summary",
                  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags= "@All")
public class runner{
}