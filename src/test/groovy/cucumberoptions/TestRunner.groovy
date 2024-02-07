package cucumberoptions

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.AfterClass
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(
  features = ["src/test/groovy/features"],
  glue = [
    "stepdefinitions",
    "utils"],
  plugin = ["pretty", "html:build/reports/Platform-Test-Network-UI-Report.html", "json:src/test/groovy/com/taulia/test/ui/utils/javareport.json"],
  tags = "@Login")

class TestRunner {

  @AfterClass
  static void tearDown() {
  }

}

