package by.booking.pkt.application.web;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FilterBlock extends HelperBase {
  private int minBudget;
  private int maxBudget;

  public FilterBlock(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public List<WebElement> getAllBudgets() {
    return webDriver.findElements(By.cssSelector("a[data-id^=pri]"));
  }

  public void selectOnlyAvailable() {
    clickOn(By.cssSelector("[data-name=oos] div"));
    refreshDriver();
  }

  public int getNigtsCount() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.sb-dates__los")));
    return Integer.parseInt(textByPatternNoSpace("\\d+", getText(By.cssSelector("div.sb-dates__los"))));
  }

  public boolean selectStarsCount(int stars) {
    WebElement filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class][data-value='" + stars + "']"));
    if (!filterElement.findElement(By.cssSelector("input")).isSelected())
      clickOn(filterElement.findElement(By.cssSelector("div")));
    if (filterElement.findElement(By.cssSelector("input")).isSelected()) {
      return true;
    } else {
      return false;
    }
  }

  @Nullable
  public FilterBlock selectBudget(int min, int max) {
    List<WebElement> all = getAllBudgets();
    int previous = 0;
    int current = 0;
    WebElement e_current;
    WebElement e_previous = null;
    for(int i=0;i<all.size();i++){
      e_current = all.get(i);
      current = Integer.parseInt(getAttribute(e_current, "data-value"));
      if(i<(all.size()-1)) {
        if (i>0) {
          e_previous = all.get(i-1);
          previous = Integer.parseInt(getAttribute(e_previous, "data-value"));
        }
        if (min <= current && min > previous)
          minBudget = previous;
        if (max < current)
          maxBudget = current;
        if (min < current || max >= current)
          clickOn(e_current);
      } else if(i==(all.size()-1)){
        if(max>current){
          maxBudget = Integer.MAX_VALUE;
          clickOn(e_current);
        }
        if(min>current){
          minBudget = previous;
          clickOn(e_current);
        }

      }
    }
    refreshDriver();
    return this;
  }

  public int getMax() {
    return maxBudget;
  }

  public int getMin() {
    return minBudget;
  }

}
