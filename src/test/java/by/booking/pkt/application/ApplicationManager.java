package by.booking.pkt.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected WebDriver webDriver;
  protected WebDriverWait wait;


  private int implicitlyWait;
  private AccountHelper accountHelper;
  private WindowHelper windowHelper;
  private SearchPageHelper searchPageHelper;
  private ResultsPageHelper resultsPageHelper;
  private HotelPageHelper hotelPageHelper;
  private WishlistsPageHelper wishlistsPageHelper;


  private FilterBoxHelper filterBoxHelper;


  public void init(int implicitlyWait) {
    webDriver = new ChromeDriver();
    setImplicitlyWait(implicitlyWait);
    wait = new WebDriverWait(webDriver, 30);
    accountHelper = new AccountHelper(webDriver, wait, implicitlyWait);
    searchPageHelper = new SearchPageHelper(webDriver, wait, implicitlyWait);
    resultsPageHelper = new ResultsPageHelper(webDriver, wait, implicitlyWait);
    filterBoxHelper = new FilterBoxHelper(webDriver, wait, implicitlyWait);
    hotelPageHelper = new HotelPageHelper(webDriver, wait, implicitlyWait);
    windowHelper = new WindowHelper(webDriver, wait, implicitlyWait);
    wishlistsPageHelper = new WishlistsPageHelper(webDriver, wait, implicitlyWait);
    windowHelper.maximazeWindow();
  }

  public void stop() {
    // webDriver.quit();

  }

  public void clear() {
    webDriver.manage().deleteAllCookies();
  }

  public int getImplicitlyWait() {
    return implicitlyWait;
  }

  public void setImplicitlyWait(int implicitlyWait) {
    webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    this.implicitlyWait = implicitlyWait;
  }

  public AccountHelper account() {
    return accountHelper;
  }

  public WindowHelper windows() {
    return windowHelper;
  }

  public SearchPageHelper searchPage() {
    return searchPageHelper;
  }

  public ResultsPageHelper results() {
    return resultsPageHelper;
  }

  public HotelPageHelper hotel() {
    return hotelPageHelper;
  }

  public WishlistsPageHelper wishlists() {
    return wishlistsPageHelper;
  }

  public FilterBoxHelper filters() {
    return filterBoxHelper;
  }
}
