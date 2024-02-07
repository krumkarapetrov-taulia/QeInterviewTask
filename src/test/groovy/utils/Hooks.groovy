package utils

import io.cucumber.java.After
import io.cucumber.java.Before
import org.openqa.selenium.WebDriverException

class Hooks {

  private DriverManager driverManager
  private PropertyReader propertyReader

  @Before
  void beforeScenario() throws WebDriverException {
    try {
      propertyReader = new PropertyReader()
      String browser = propertyReader.getBrowser()
      driverManager = new DriverManager()
      driverManager.initDriver(browser)
      DriverManager.getDriver().manage().window().maximize()
      DriverManager.getDriver().manage().deleteAllCookies()
    }
    catch (WebDriverException exception) {
      propertyReader = new PropertyReader()
      String browser = propertyReader.getBrowser()
      driverManager = new DriverManager()
      driverManager.initDriver(browser)
      DriverManager.getDriver().manage().window().maximize()
      DriverManager.getDriver().manage().deleteAllCookies()
    }
  }

  @After
  void afterScenario() {
    driverManager.getDriver().quit()
  }
}
