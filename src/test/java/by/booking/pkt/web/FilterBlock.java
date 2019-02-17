package by.booking.pkt.web;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FilterBlock extends HelperBase {
  private int minBudget =0;
  private int maxBudget =0;

  public FilterBlock(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public List<WebElement> allBudgets() {
    return webDriver.findElements(By.cssSelector("a[data-id^=pri]:not([data-google-track^=Click])"));
  }

  public void selectOnlyAvailable() {
    webDriver.findElement(By.cssSelector("[data-name=oos] div")).click();
    refreshPage();
  }

  public int getNigtsCount() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.sb-dates__los")));
    return Integer.parseInt(textByPatternNoSpace("\\d+", webDriver.findElement(By.cssSelector("div.sb-dates__los")).getText()));
  }

  public boolean selectStars(int stars) {
    WebElement filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class-" + stars));
    if (!filterElement.findElement(By.cssSelector("input")).isSelected()) {
      filterElement.findElement(By.cssSelector("div")).click();
      refreshPage();
    }
    filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class-" + stars));
    if (filterElement.findElement(By.cssSelector("input")).isSelected()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean unselectStars(int stars) {
    WebElement filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class-" + stars));
    if (filterElement.findElement(By.cssSelector("input")).isSelected()) {
      filterElement.findElement(By.cssSelector("div")).click();
      refreshPage();
    }
    filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class-" + stars));
    if (!filterElement.findElement(By.cssSelector("input")).isSelected()) {
      return true;
    } else {
      return false;
    }
  }

  @Nullable
  public FilterBlock selectBudget(int min, int max) {
    List<WebElement> all = allBudgets();
    minBudget = 0;
    maxBudget = 0;
    int previous = 0;
    int current = 0;
    for (int i = 0; i < all.size() && maxBudget == 0; i++) {
      current = Integer.parseInt(all.get(i).getAttribute("data-value"));
      if (i < (all.size() - 1)) {
        if (min > previous) {
          if (i > 0) {
            previous = Integer.parseInt(all.get(i - 1).getAttribute("data-value"));
            if(min<current&&min>previous){
              min = Integer.parseInt(all.get(i - 1).getAttribute("data-value"));
            }
          }
          if (min <= current)
            minBudget = previous;
        }
        if (max <= current)
            maxBudget = current;
        if (min < current) {
          selectBudget(all.get(i));
          all = allBudgets();
        }
      } else if (i == (all.size() - 1)) {
        if (max > current) {
          maxBudget = Integer.MAX_VALUE;
          selectBudget(all.get(i));
        }
        if (min > current) {
          minBudget = previous;
          selectBudget(all.get(i));
        }
      }
    }
    return this;
  }

  @Nullable
  public FilterBlock unselectAllBudgets() {
    List<WebElement> all = allBudgets();
    for (WebElement e : allBudgets()) {
      unselectBudget(e);
      all = allBudgets();
    }
    return this;
  }

  private void selectBudget(WebElement e) {
    if(!e.findElement(By.cssSelector("input")).isSelected()) {
      e.click();
      refreshPage();
    }
  }
  private void unselectBudget(WebElement e) {
    if (e.findElement(By.cssSelector("input")).isSelected()) {
      e.click();
      refreshPage();
    }
  }

  public int getMax() {
    return maxBudget;
  }

  public int getMin() {
    return minBudget;
  }

}
