package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
  WebDriver webDriver;
  WebDriverWait wait;
  private AccountNavigator navigationHelper;

  private FilterBoxHelper filterBoxHelper;
  private WindowNavigator windowNavigator;
  private SearchHelper searchHelper;
  private SearchResultsHelper searchResultsHelper;
  private TopListNavigator topListNavigator;
  private WishlistHelper wishlistHelper;
  private AccountNavigator accountNavigator;

  public void init() {
    wishlistHelper = new WishlistHelper(webDriver, wait);
    windowNavigator = new WindowNavigator(webDriver, wait);
    accountNavigator = new AccountNavigator(webDriver, wait);
    webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(webDriver, 15);
    windowNavigator.maximazeWindow();
    webDriver.get("https://www.booking.com");

  }

  public void stop() {
    webDriver.quit();

  }

  public AccountNavigator getAccountNavigator() {
    return accountNavigator;
  }

  public FilterBoxHelper getFilterBoxHelper() {
    return filterBoxHelper;
  }

  public WindowNavigator getWindowNavigator() {
    return windowNavigator;
  }

  public SearchHelper getSearchHelper() {
    return searchHelper;
  }

  public SearchResultsHelper getSearchResultsHelper() {
    return searchResultsHelper;
  }

  public TopListNavigator getTopListNavigator() {
    return topListNavigator;
  }

  public WishlistHelper getWishlistHelper() {
    return wishlistHelper;
  }
}
