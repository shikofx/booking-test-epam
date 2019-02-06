package by.booking.pkt.recorded.appManager;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HelperBase {
  protected WebDriver webDriver;
  protected WebDriverWait wait;
  private boolean acceptNextAlert = true;

  public HelperBase(WebDriver webDriver, WebDriverWait wait){
    this.webDriver = webDriver;
    this.wait = wait;
  }
  public WebDriver getWebDriver() {
    return webDriver;
  }

  public WebDriverWait getWait() {
    return wait;
  }

  public void showDropdown(By whatClick, By whatWait) {
    wait.until(presenceOfElementLocated(whatClick));
    clickOn(whatClick);
    WebElement dropDownMenu = wait.until(presenceOfElementLocated(whatWait));
    for (int i = 0; i < 3 && !dropDownMenu.isDisplayed(); i++) {
      clickOn(whatClick);
    }
    wait.until(visibilityOfElementLocated(whatWait));
  }

  @Nullable
  public String textByPatternNoSpace(String regex, String inString) {
    return getTextByPattern(regex, inString).replaceAll("\\s", "");
  }

  @Nullable
  public String getTextByPattern(String regex, String string) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    String result= null;
    while(matcher.find()) {
      result = string.substring(matcher.start(), matcher.end());
    }
    return result;
  }

  public boolean isElementPresent(By locator) {
    return getElements(locator).size() > 0;
  }

  public boolean isElementPresent(WebElement inElement, By locator) {
    return getElements(inElement, locator).size() > 0;
  }

  public boolean isElementPresentNoWait(By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = getElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean isElementPresentNoWait(WebElement inElement, By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = getElements(inElement, locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;
  }

  private boolean isAlertPresent() {
    try {
      webDriver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = webDriver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  protected void clickOn(By locator) {
    webDriver.findElement(locator).click();
  }

  protected void clickOn(WebElement inElement, By locator) {
    inElement.findElement(locator).click();
  }

  protected WebElement getElement(By locator) {
    return webDriver.findElement(locator);
  }

  protected List<WebElement> getElements(By locator) {
    return webDriver.findElements(locator);
  }

  protected List<WebElement> getElements(WebElement inElement, By locator) {
    return inElement.findElements(locator);
  }

  protected void refreshDriver() {
    webDriver.navigate().refresh();
  }

  protected String getAttribute(WebElement inElement, String attributeName) {
    return inElement.getAttribute(attributeName);
  }

  public String getAttribute(By where, String attributeName) {
    return webDriver.findElement(where).getAttribute(attributeName);
  }

  public String getText(By locator) {
    wait.until(presenceOfElementLocated(locator));
    return webDriver.findElement(locator).getText();
  }

  protected void typeText(WebElement inElement, By locator, String text) {
    clickOn(inElement, locator);
    inElement.findElement(locator).clear();
    inElement.findElement(locator).sendKeys(text);
  }

  protected void typeText(By where, String text) {
    clickOn(where);
    getElement(where).clear();
    getElement(where).sendKeys(text);
  }
}
