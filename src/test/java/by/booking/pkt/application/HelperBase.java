package by.booking.pkt.application;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HelperBase {
  protected int implicitlyWait;
  protected WebDriver webDriver;
  protected WebDriverWait wait;

  public HelperBase(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    this.webDriver = webDriver;
    this.wait = wait;
    this.implicitlyWait = implicitlyWait;

  }

  public HelperBase refreshPage() {
    webDriver.navigate().refresh();
    return this;
  }

  public HelperBase displayDropDown(WebElement whatClick, WebElement whatWait, int secondsToWait) {
    wait.until(visibilityOf(whatClick));
    for (int i = 0; i < 3; i++) {
      if (!isElementPresent(whatWait, secondsToWait) || !isElementDisplayed(whatWait))
        whatClick.click();
      else break;
    }
    return this;
  }


  protected HelperBase hideDropdown(WebElement toClick, WebElement toWait) {
    wait.until((WebDriver d) -> {
      if (isElementDisplayed(toWait)) {
        toClick.click();
        wait.until(not(visibilityOf(toWait)));
      } else {
        return true;
      }
      return false;
    });
    return this;
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

  private boolean isElementDisplayed(WebElement whatWait) {
    return whatWait.isDisplayed();
  }

  public boolean isElementPresent(By locator, int secondsToWait) {
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    boolean isElementPresent = webDriver.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean isElementPresent(WebElement element, int secondsToWait) {
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    boolean isElementPresent = element.isEnabled();
    webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean isElementPresent(WebElement inElement, By locator, int secondsToWait) {
    int oldWait = implicitlyWait;
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
    boolean isElementPresent = inElement.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;
  }

  protected void inputText(WebElement where, String text) {
    where.click();
    where.clear();
    where.sendKeys(text);
  }

}
