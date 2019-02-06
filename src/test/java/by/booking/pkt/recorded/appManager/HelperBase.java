package by.booking.pkt.recorded.appManager;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
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

  public void app_activateDropdownMenu(By whatClick, By dropDownMenu) {
    wait.until(presenceOfElementLocated(whatClick));
    webDriver.findElement(whatClick).click();
    WebElement dropDownMenuElement = wait.until(presenceOfElementLocated(dropDownMenu));
    for (int i = 0; i < 3 && !dropDownMenuElement.isDisplayed(); i++) {
      webDriver.findElement(whatClick).click();
    }
    wait.until(visibilityOfElementLocated(dropDownMenu));
  }

  @Nullable
  public String app_getTextByPatternNoSpace(String regex, String inString){
    return app_getTextByPattern(regex, inString).replaceAll("\\s", "");
  }

  @Nullable
  public String app_getTextByPattern(String regex, String string) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    String result= null;
    while(matcher.find()) {
      result = string.substring(matcher.start(), matcher.end());
    }
    return result;
  }

  public boolean app_isElementPresent(By locator) {
    return webDriver.findElements(locator).size() > 0;
  }

  public boolean app_isElementPresent(WebElement webElement, By locator) {
    return webElement.findElements(locator).size() > 0;
  }

  public boolean app_isElementInPresentNoWait(By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = webDriver.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean app_isElementInPresentNoWait(WebElement webElement, By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = webElement.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;
  }

  private boolean app_isAlertPresent() {
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

  public String app_getAttributeWithWait(By locatorForWait, String attributeName) {
    wait.until(presenceOfElementLocated(locatorForWait));
    return webDriver.findElement(locatorForWait).getAttribute(attributeName);
  }

  public String app_getTextWithWait(By locator) {
    wait.until(presenceOfElementLocated(locator));
    return webDriver.findElement(locator).getText();
  }
}
