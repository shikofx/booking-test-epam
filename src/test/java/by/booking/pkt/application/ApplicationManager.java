package by.booking.pkt.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
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
  private String browser;
  private final Properties properties;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();

  }

  public void init(int implicitlyWait) throws IOException {
    String target = (System.getProperty("target", "booking"));
    properties.load(new FileReader(new File(String.format("/src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.EDGE))
      webDriver = new EdgeDriver();
    else if (browser.equals(BrowserType.FIREFOX))
      webDriver = new FirefoxDriver();
    else if (browser.equals(BrowserType.IE))
      webDriver = new InternetExplorerDriver();
    else if (browser.equals(BrowserType.OPERA_BLINK))
      webDriver = new OperaDriver();
    else if (browser.equals(BrowserType.CHROME))
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

  public void goToBooking() {
    webDriver.get(properties.getProperty("baseUrl"));
  }

  public void login() {
    accountHelper.loginAs(properties.getProperty("userName"), properties.getProperty("userPassword"));
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
