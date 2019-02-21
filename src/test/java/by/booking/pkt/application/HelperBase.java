package by.booking.pkt.application;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HelperBase {
  protected int implicitlyWait;
  protected WebDriver webDriver;
  protected WebDriverWait wait;

  public HelperBase(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    this.webDriver = webDriver;
    this.wait = wait;
    this.implicitlyWait = implicitlyWait;
    PageFactory.initElements(webDriver, this);
  }

  public HelperBase refreshPage() {
    webDriver.navigate().refresh();
    return this;
  }

  public void displayDropDown(By whatClick, By whatWait, int secondsToWait) {
    wait.until(visibilityOfElementLocated(whatClick));
    for (int i = 0; i < 3; i++) {
      if (!isElementPresent(whatWait, secondsToWait) || !isElementDisplayed(whatWait))
        webDriver.findElement(whatClick).click();
      else break;
    }
  }

  public HelperBase displayDropDown(WebElement whatClick, WebElement whatWait, int secondsToWait) {
    wait.until(visibilityOf(whatClick));
    whatClick.click();
      if (!whatWait.isDisplayed()) {
        whatClick.click();
      }
    return this;
  }

  protected HelperBase hideDropdown(WebElement toClick, WebElement toWait) {
    wait.until((WebDriver d) -> {
      if (toWait.isDisplayed()) {
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

  private boolean isElementDisplayed(By whatWait) {
    return webDriver.findElement(whatWait).isDisplayed();
  }

  public boolean isElementPresent(By locator, int secondsToWait) {
    setImplicitlyWait(secondsToWait);
    boolean isElementPresent = webDriver.findElements(locator).size() > 0;
    setImplicitlyWait(implicitlyWait);
    return isElementPresent;

  }

  public boolean isElementPresent(WebElement e, int secondsToWait) {
    setImplicitlyWait(secondsToWait);
    boolean isElementPresent = false;
//    List<WebElement> elementList = new ArrayList<WebElement>();
//    elementList.add(e);
    try {
      isElementPresent = e.isEnabled();
    } catch (NoSuchElementException exeption){
    }
    setImplicitlyWait(implicitlyWait);
    return isElementPresent;

  }

  private void setImplicitlyWait(int secondsToWait) {
    webDriver.manage().timeouts().implicitlyWait(secondsToWait, TimeUnit.SECONDS);
  }


  public boolean isElementPresent(WebElement inElement, By locator, int secondsToWait) {
    int oldWait = implicitlyWait;
    setImplicitlyWait(secondsToWait);
    boolean isElementPresent = inElement.findElements(locator).size() > 0;
    setImplicitlyWait(oldWait);
    return isElementPresent;
  }

  public boolean isElementPresentAndVisible(WebElement e) {
    return wait.until((WebDriver d) -> {
      return e.isEnabled() && e.isDisplayed();
    });
  }

  protected void inputText(WebElement where, String text) {
    where.click();
    where.clear();
    where.sendKeys(text);
  }

  private Alert alertAfterClick(By toClick) {
    wait.until(visibilityOfElementLocated(toClick));
    return wait.until((WebDriver d) -> {
      d.findElement(toClick).click();
      try {
        return d.switchTo().alert();
      } catch (NoAlertPresentException e) {
        return null;
      }
    });
  }

  protected Alert alertAfterClick(WebElement toClick) {
    wait.until(visibilityOfAllElements(toClick));
    return wait.until((WebDriver d) -> {
      toClick.click();
      try {
        return d.switchTo().alert();
      } catch (NoAlertPresentException e) {
        return null;
      }
    });
  }

}
