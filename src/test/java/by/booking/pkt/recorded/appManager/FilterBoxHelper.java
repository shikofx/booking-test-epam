package by.booking.pkt.recorded.appManager;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FilterBoxHelper extends HelperBase{


  public FilterBoxHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public List<WebElement> getAllBudgets() {
    return getElements(By.cssSelector("a[data-id^=pri]"));
  }

  public void selectOnlyAvailable() {
    clickOn(By.cssSelector("[data-name=oos] div"));
    refreshDriver();
  }

  public int getNigtsCount() {
    return Integer.parseInt(textByPatternNoSpace("\\d+", getText(By.cssSelector("div.sb-dates__los"))));
  }

  public void setStarsCount(int stars) {
    clickOn(By.cssSelector("a[data-id^=class][data-value='" + stars + "']"));
  }

  @Nullable
  public int selectBudgetAndGetMax(int budget) {
    List<WebElement> filterItems = getAllBudgets();
    WebElement filterElement = null;
    for (int i = 0; i < filterItems.size(); i++) {
      filterElement = filterItems.get(i);
      if (budget < Integer.parseInt(getAttribute(filterElement, "data-value"))) {
        break;
      }
    }
    filterElement.click();
    int totalBudget = getNigtsCount() * Integer.parseInt(getAttribute(filterElement, "data-value"));
    if (budget * getNigtsCount() > totalBudget)
      totalBudget = Integer.MAX_VALUE;
    return totalBudget;
  }

}
