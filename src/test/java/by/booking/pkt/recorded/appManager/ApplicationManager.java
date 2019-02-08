package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
  protected WebDriver webDriver;
  protected WebDriverWait wait;


  private int implicitlyWait;
  private AccountManager navigationHelper;

  private FilterManager filterManager;
  private WindowManager windowManeger;
  private SearchManager searchManager;
  private ResultsManager resultsManager;
  private TopListManager topListManager;
  private WishlistManager wishlistManager;
  private AccountManager accountManager;

  public void init(int implicitlyWait) {
    webDriver = new ChromeDriver();
    setImplicitlyWait(implicitlyWait);
    wait = new WebDriverWait(webDriver, 15);

    accountManager = new AccountManager(webDriver, wait, implicitlyWait);
    filterManager = new FilterManager(webDriver, wait, implicitlyWait);
    searchManager = new SearchManager(webDriver, wait, implicitlyWait);
    resultsManager = new ResultsManager(webDriver, wait, implicitlyWait);
    topListManager = new TopListManager(webDriver, wait, implicitlyWait);
    windowManeger = new WindowManager(webDriver, wait, implicitlyWait);
    wishlistManager = new WishlistManager(webDriver, wait, implicitlyWait);

    windowManeger.maximazeWindow();
    windowManeger.goToUrl("https://www.booking.com");

  }

  public void stop() {
    // webDriver.quit();

  }

  public int getImplicitlyWait() {
    return implicitlyWait;
  }

  public void setImplicitlyWait(int implicitlyWait) {
    webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    this.implicitlyWait = implicitlyWait;
  }

  public AccountManager getAccountManager() {
    return new AccountManager(webDriver, wait, implicitlyWait);
  }

  public FilterManager getFilterManager() {
    return filterManager;
  }

  public WindowManager getWindowManeger() {
    return windowManeger;
  }

  public SearchManager getSearchManager() {
    return searchManager;
  }

  public ResultsManager getResultsManager() {
    return resultsManager;
  }

  public TopListManager getTopListManager() {
    return topListManager;
  }

  public WishlistManager getWishlistManager() {
    return wishlistManager;
  }
}
