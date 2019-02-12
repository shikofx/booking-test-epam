package by.booking.pkt.application.web;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HelperBase {
  protected int implicitlyWait;
  protected WebDriver webDriver;
  protected WebDriverWait wait;
  private boolean acceptNextAlert = true;

  public HelperBase(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    this.webDriver = webDriver;
    this.wait = wait;
    this.implicitlyWait = implicitlyWait;

  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public WebDriverWait getWait() {
    return wait;
  }

  public void showDropdown(By whatClick, By whatWait, int secondsToWait) {
    wait.until(visibilityOfElementLocated(whatClick));
    for (int i = 0; i < 3; i++) {
      if (!isElementPresent(whatWait, secondsToWait) || !isElementDisplayed(whatWait))
        clickOn(whatClick);
      else break;
    }
  }

  protected void hideDropdown(By whatClick, By whatWait, int secondsToWait) {
    wait.until(presenceOfElementLocated(whatClick));
    for (int i = 0; i < 3; i++) {
      if (isElementPresent(whatWait, secondsToWait))
        clickOn(whatClick);
      else break;
    }
  }

  @Nullable
  public String textByPatternNoSpace(String regex, String inString) {
    return getTextByPattern(regex, inString).replaceAll("\\s", "");
  }

  @Nullable
  public String getTextByPattern(String regex, String string) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    String result = null;
    while (matcher.find()) {
      result = string.substring(matcher.start(), matcher.end());
    }
    return result;
  }

  private boolean isElementDisplayed(By whatWait) {
    return webDriver.findElement(whatWait).isDisplayed();
  }

  private boolean isElementDisplayed(WebElement inElement, By whatWait) {
    return inElement.findElement(whatWait).isDisplayed();
  }

  public boolean isElementPresent(By locator) {
    return webDriver.findElements(locator).size() > 0;
  }

  public boolean isElementPresent(By locator, int secondsToWait) {
    int oldWait = implicitlyWait;
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    boolean isElementPresent = webDriver.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean isElementPresent(WebElement inElement, By locator) {
    return inElement.findElements(locator).size() > 0;
  }

  public boolean isElementPresent(WebElement inElement, By locator, int secondsToWait) {
    int oldWait = implicitlyWait;
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    boolean isElementPresent = inElement.findElements(locator).size() > 0;
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

  protected void clickOn(WebElement element) {
    element.click();
  }

  protected void clickOn(WebElement inElement, By locator) {
    inElement.findElement(locator).click();
  }

  protected void refreshDriver() {
    webDriver.navigate().refresh();
  }

  protected String getAttribute(WebElement inElement, String attributeName) {
    return inElement.getAttribute(attributeName);
  }

  @Nullable
  public String getAttribute(By where, String attributeName) {
    return webDriver.findElement(where).getAttribute(attributeName);
  }

  protected String getAttribute(WebElement inElement, By locator, String attributeName) {
    return inElement.findElement(locator).getAttribute(attributeName);
  }

  public String getText(By locator) {
    return webDriver.findElement(locator).getText();
  }

  protected void typeText(WebElement inElement, By locator, String text) {
    clickOn(inElement, locator);
    inElement.findElement(locator).clear();
    inElement.findElement(locator).sendKeys(text);
  }
  protected void typeText(By where, String text) {
    clickOn(where);
    webDriver.findElement(where).clear();
    webDriver.findElement(where).sendKeys(text);
  }
}
