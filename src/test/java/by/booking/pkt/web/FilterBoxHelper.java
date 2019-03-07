package by.booking.pkt.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FilterBoxHelper extends HelperBase {
  public static final String REGEX_NIGHTS = "\\d+";
  private int minBudget = 0;
  private int maxBudget = 0;


  public FilterBoxHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(css = "a[data-id^=pri]:not([data-google-track^=Click])")
  private List<WebElement> allBudgets;

  @FindBy(css = "div.sb-dates__los")
  private WebElement nigtsCountField;

  public boolean selectStars(int stars) {
    By starsLocator = By.cssSelector("a[data-id^=class-" + stars);
    WebElement starsElement = webDriver.findElement(starsLocator);
    if (!starsElement.findElement(By.cssSelector("input")).isSelected()) {
      starsElement.findElement(By.cssSelector("div")).click();
      refreshPage();
    }
    starsElement = webDriver.findElement(starsLocator);
    if (starsElement.findElement(By.cssSelector("input")).isSelected()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean selectBudget(int min, int max) {
    List<WebElement> all = allBudgets;
    minBudget = 0;
    maxBudget = 0;
    WebElement previous;
    WebElement current;
    boolean isSelected = false;
    for (int i = 0; i < all.size() && maxBudget == 0; i++) {
      current = all.get(i);
      if (i > 0) {
        previous = all.get(i - 1);
        if (min > budgetValue(previous) && min <= budgetValue(current)) {
          minBudget = budgetValue(previous);
        }
      }
      if (i < (all.size() - 1)) {
        if (max <= budgetValue(current))
          maxBudget = budgetValue(current);
        if (min < budgetValue(current)) {
          isSelected = selectBudget(current);
          all = allBudgets;
        }
      } else if (i == (all.size() - 1)) {
        if (max > budgetValue(current)) {
          if (min > budgetValue(current)) {
            previous = all.get(i - 1);
            minBudget = budgetValue(previous);
          }
          maxBudget = Integer.MAX_VALUE / 100;
          isSelected = selectBudget(current);
        }
      }
    }
    return isSelected;
  }

  private int budgetValue(WebElement element) {
    return Integer.parseInt(element.getAttribute("data-value"));
  }

  private boolean selectBudget(WebElement e) {
    boolean isSelected = false;
    if (!e.findElement(By.cssSelector("input")).isSelected()) {
      e.click();
       wait.until((WebDriver d) -> e.findElement(By.cssSelector("input")));
      if (e.findElement(By.cssSelector("input")).isSelected())
        isSelected = true;
      refreshPage();
    }
    return isSelected;
  }

  public int getMaxTotalPrice() {
    return getNigtsCount() * maxBudget;
  }

  public int getMinTptalPrice() {
    return getNigtsCount() * minBudget;
  }

  public int getNigtsCount() {
    return Integer.parseInt(textByPatternWithout(REGEX_NIGHTS, "\\s", nigtsCountField.getText()));
  }
}
