package by.booking.pkt.recorded.appManager;

import by.booking.pkt.recorded.web.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
  protected WebDriver webDriver;
  protected WebDriverWait wait;


  private int implicitlyWait;
  private AccountHelper navigationHelper;
  private WindowHelper windowManeger;
  private SearchPage searchPage;
  private ResultsPage resultsManager;
  private HotelPage topListManager;
  private WishlistsPage wishlistManager;
  private AccountHelper account;

  public void init(int implicitlyWait) {
    webDriver = new ChromeDriver();
    setImplicitlyWait(implicitlyWait);
    wait = new WebDriverWait(webDriver, 30);

    account = new AccountHelper(webDriver, wait, implicitlyWait);
    searchPage = new SearchPage(webDriver, wait, implicitlyWait);
    resultsManager = new ResultsPage(webDriver, wait, implicitlyWait);
    topListManager = new HotelPage(webDriver, wait, implicitlyWait);
    windowManeger = new WindowHelper(webDriver, wait, implicitlyWait);
    wishlistManager = new WishlistsPage(webDriver, wait, implicitlyWait);

    windowManeger.maximazeWindow();
    windowManeger.goTo("https://www.booking.com");

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

  public AccountHelper account() {
    return account;
  }

  public WindowHelper windows() {
    return windowManeger;
  }

  public SearchPage forSearch() {
    return searchPage;
  }

  public ResultsPage results() {
    return resultsManager;
  }

  public HotelPage hotel() {
    return topListManager;
  }

  public WishlistsPage wishlists() {
    return wishlistManager;
  }
}
