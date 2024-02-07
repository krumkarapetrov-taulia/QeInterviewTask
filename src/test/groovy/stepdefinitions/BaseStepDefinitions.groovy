package stepdefinitions

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import utils.DriverManager
import utils.PropertyReader

import java.time.Duration
import java.util.concurrent.TimeUnit

class BaseStepDefinitions {


  Duration timeout = Duration.ofSeconds(15)
  WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout)

  JavascriptExecutor javaScriptExecutor
  PropertyReader prop
  WebDriver driver = DriverManager.getDriver()
  private static final String CLICK_COMMAND = "arguments[0].click();"
  Actions actions = new Actions(driver)

  void launchURL(String url) {
    prop = new PropertyReader()
    driver = DriverManager.getDriver()
    driver.manage().deleteAllCookies()
    driver.get(url)
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
    driver.manage().window().maximize()
  }

  void clickElement(WebElement e, String name) {

    try {
      if (e.isDisplayed())
        clickElementJS(e)
      else {
        wait.until(ExpectedConditions.visibilityOf(e))
        e.click()
      }
      System.out.println("Successfully clicked on the Webelement : " + name)
    }
    catch (Exception exception) {
      exception.printStackTrace()
      System.out.println("Element is not present on the webpage!!")
    }
  }

  void clickElement(WebElement e) {
    e.click()
  }

  void clickElement(String xpathOfElement) {
    driver.findElement(By.xpath(xpathOfElement)).click()

  }

  void clickElementCss(String cssOfElement) {
    driver.findElement(By.cssSelector(cssOfElement)).click()
  }

  void clickElementJS(final WebElement element) {
    javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver()
    javaScriptExecutor.executeScript(CLICK_COMMAND, element)
  }


  void sendKeys(WebElement e, String value) {

    e.sendKeys(value)
  }

  void sendKeysClear(WebElement e, String value) {
    e.clear()
    e.sendKeys(value)
  }

  void sendKeysBackspace(WebElement e, String value) throws InterruptedException {
    while (e.getAttribute("value") != "") {
      e.sendKeys(Keys.BACK_SPACE)
    }
    e.sendKeys(value)
  }

  String getText(WebElement e) {

    String txt = ""
    try {
      if (e.isDisplayed())
        txt = e.getText()
      else {
        wait.until(ExpectedConditions.visibilityOf(e))
        txt = e.getText()
      }
      System.out.println("Successfully fetched text on the Webelement !!")
    }
    catch (Exception exception) {
      exception.printStackTrace()
      System.out.println("Element is not present on the webpage!!")
    }
    return txt
  }

  static String getTextValue(String xpathOfElement) {

    String value = ""
    try {
      WebDriver driver = DriverManager.getDriver()
      WebElement e = driver.findElement(By.xpath(xpathOfElement))
      value = e.getText()
    }
    catch (Exception exception) {
      exception.printStackTrace()
      System.out.println("Element is not present on the webpage!!")
    }
    return value
  }

  void waitForPageLoad() {
    wait.until((ExpectedCondition<Boolean>) (wd) ->
      ((JavascriptExecutor) wd).executeScript("return document.readyState") == "complete")
  }

  /**
   * To scroll to the webelement
   * @param element webelement
   */
  void scrollByElementVisibility(WebElement element) {
    javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver()
    javaScriptExecutor.executeScript("arguments[0].scrollIntoView();", element)


  }

  /**
   * To mousehover element and click on it
   * @param element to mousehover
   */
  void mouseHoverClick(WebElement element) throws InterruptedException {
    actions.moveToElement(element).perform()
    waitForElementClickable(element)
    clickElement(element)
  }

  /**
   * To switch to child window
   */
  static void switchChildWindow() {
    String parentHandle3 = DriverManager.getDriver().getWindowHandle()
    Set<String> allHandles3 = DriverManager.getDriver().getWindowHandles()

    for (String handle : allHandles3) {
      if (!handle.equals(parentHandle3)) {
        DriverManager.getDriver().switchTo().window(handle)
      }
    }
  }

  /**
   * To scroll down to bottom of page
   */
  void scrollToBottom() {
    javaScriptExecutor = (JavascriptExecutor) DriverManager.getDriver()
    javaScriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)")
  }

  /**
   * To sendkeys if element throws StaleElement exception
   * @param ele
   * @param value
   */
  static void sendKeysWithRetry(WebElement ele, String value) {
    boolean retryNeeded = false
    do {
      try {
        ele.sendKeys(value)
      }
      catch (StaleElementReferenceException e) {
        retryNeeded = true
      }
      catch (NoSuchElementException e) {
        break
      }
    } while (retryNeeded)
  }

  static void clickWithRetry(WebElement elementToClick) {
    boolean retryNeeded = false
    do {
      try {
        sleepFor(2000)
        elementToClick.click()
        println("clicked element")
      }
      catch (StaleElementReferenceException e) {
        retryNeeded = true
      }
      catch (NoSuchElementException e) {
        break
      }
    } while (retryNeeded)
  }


  /**
   * To switch for parent window
   * @param parentwindow
   */
  static void switchParentWindow(String parentWindow) {
    String parentHandle3 = parentWindow
    Set<String> allHandles3 = DriverManager.getDriver().getWindowHandles()

    for (String handle : allHandles3) {
      if (!handle.equals(parentHandle3)) {
        DriverManager.getDriver().switchTo().window(handle)
        break
      }
    }
  }

  /**
   * To wait for particular seconds
   * @param timeforsleep
   * @throws InterruptedException
   */
  static void sleepFor(int timeForSleep) throws InterruptedException {
    Thread.sleep(timeForSleep)
  }


  void waitForElementInvisibility(WebElement e) {
    wait.until(ExpectedConditions.invisibilityOf(e))

  }

  void waitForElementVisibility(WebElement e) {
    wait.until(ExpectedConditions.visibilityOf(e))
  }

  void waitForElementVisibility(String xpath) {
    WebElement elementToWait = driver.findElement(By.xpath(xpath))
    wait.until(ExpectedConditions.visibilityOf(elementToWait))
  }

  void waitForElementClickable(WebElement e) {
    wait.until(ExpectedConditions.elementToBeClickable(e))
  }

  /**
   * To refresh the page
   */
  void refreshPage() {
    driver.navigate().refresh()
  }


  /**
   * To select value from div dropdwon
   * @param dropdown
   * @param dropdownValue
   */
  void divDropdown(WebElement dropdown, WebElement dropdownValue) throws InterruptedException {
    scrollByElementVisibility(dropdown)
    mouseHoverClick(dropdown)
    mouseHoverClick(dropdownValue)
  }


  static boolean isElementPresent(WebElement e) {
    e.isDisplayed()
  }

/**
 * To check if element not present
 * @param e
 * @return false
 */
  static boolean verifyElementNotDisplayed(WebElement e) {
    try {
      return (!e.isDisplayed())
    }
    catch (Exception exceptionMsg) {
      return false
    }
  }

  void navigateBack() {
    driver.navigate().back()

  }

  void switchToIframe(WebElement frameElement) {
    driver.switchTo().frame(frameElement)
  }

  void exitIframe() {
    driver.switchTo().parentFrame()
  }


  List<WebElement> getListOfWebElements(String xpath) {
    sleepFor(1500)
    List<WebElement> elements = driver.findElements(By.xpath(xpath))
    return elements
  }

  void fileUploader(String filePath, WebElement input) {
    input.sendKeys(filePath)
  }

  int generateRandomNumber() {
    Random random = new Random();
    int rand = random.nextInt();
    return rand
  }

  void typeInFieldSlowly(WebElement textField, String value) {
    String val = value
    textField.clear()

    for (int i = 0; i < val.length(); i++) {
      char c = val.charAt(i)
      String s = new StringBuilder().append(c).toString()
      textField.sendKeys(s)
      sleepFor(100)
    }
  }

  static String getCurrentUrl() {
    String currentUrl = DriverManager.getDriver().getCurrentUrl()
    currentUrl
  }
}


