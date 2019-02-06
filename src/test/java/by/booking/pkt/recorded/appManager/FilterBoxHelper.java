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

  public List<WebElement> fb_getItemsOfFilterByBudget() {
    return webDriver.findElements(By.cssSelector("a[data-id^=pri]"));
  }

  public void fb_selectOnlyAvailableON() {
    webDriver.findElement(By.cssSelector("[data-name=oos] div")).click();
    webDriver.navigate().refresh();
  }

  public int fb_getNigtsCount() {
    return Integer.parseInt(app_getTextByPatternNoSpace("\\d+", webDriver.findElement(By.cssSelector("div.sb-dates__los")).getText()));
  }

  public void fb_setStarsCount(int stars) {
    webDriver.findElement(By.cssSelector("a[data-id^=class][data-value='" + stars + "']")).click();
  }

  @Nullable
  public int fb_selectBudget(int budget) {
    List<WebElement> filterItems = fb_getItemsOfFilterByBudget();
    WebElement filterElement = null;
    for (int i = 0; i < filterItems.size(); i++) {
      filterElement = filterItems.get(i);
      if (budget < Integer.parseInt(filterElement.getAttribute("data-value"))) {
        break;
      }
    }
    filterElement.click();
    int totalBudget = fb_getNigtsCount() * Integer.parseInt(filterElement.getAttribute("data-value"));
    if (budget * fb_getNigtsCount() > totalBudget)
      totalBudget = Integer.MAX_VALUE;
    return totalBudget;
  }
}
