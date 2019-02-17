package by.booking.pkt.web;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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

  public void displayDropDown(By whatClick, By whatWait, int secondsToWait) {
    wait.until(visibilityOfElementLocated(whatClick));
    for (int i = 0; i < 3; i++) {
      if (!isElementPresent(whatWait, secondsToWait) || !isElementDisplayed(whatWait))
        webDriver.findElement(whatClick).click();
      else break;
    }
  }
  public void refreshPage() {
    webDriver.navigate().refresh();
  }
  public void displayDropDown(By toClick, By toWait) {
    wait.until((WebDriver d) -> {
      try {
        d.findElement(toClick).click();
        return d.findElement(toWait).isDisplayed() ? d.findElement(toWait) : null;
      } catch (StaleElementReferenceException e) {
        return null;
      }
    });
  }

  protected void hideDropdown(By toClick, By toWait) {
    boolean flag = false;
    wait.until((WebDriver d) -> {
      if (isElementDisplayed(toWait)) {
        d.findElement(toClick).click();
        wait.until(not(visibilityOf(d.findElement(toWait))));
      } else {
        return true;
      }
      return false;
    });
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

  protected void typeText(WebElement inElement, By locator, String text) {
    inElement.findElement(locator).click();
    inElement.findElement(locator).clear();
    inElement.findElement(locator).sendKeys(text);
  }

  protected void typeText(By where, String text) {
    webDriver.findElement(where).click();
    webDriver.findElement(where).clear();
    webDriver.findElement(where).sendKeys(text);
  }

}