package utils

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class DriverManager {

  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>()

  static WebDriver initDriver(String browser) {

    if (browser.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup()
      ChromeOptions chromeOptions = new ChromeOptions()
      chromeOptions.addArguments('--ignore-certificate-errors', '--no-sandbox', '--disable-dev-shm-usage', '--whitelisted-ips=')
      tlDriver.set(new ChromeDriver(chromeOptions))
    } else if (browser.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup()
      FirefoxOptions firefoxOptions = new FirefoxOptions()
      firefoxOptions.setAcceptInsecureCerts(true)
      tlDriver.set(new FirefoxDriver(firefoxOptions))
    } else {
      System.out.println("Please enter correct browser")
      tlDriver.set(null)
    }

    return getDriver()
  }

  static synchronized WebDriver getDriver() {
    return tlDriver.get()
  }

}
