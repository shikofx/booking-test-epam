package by.booking.pkt.application;

import by.booking.pkt.utils.PropertyLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   protected static Capabilities capabilities;
   protected static String baseUrl;
   protected static String gridHubUrl;
   protected static String username;
   protected static String password;

   protected WebDriver webDriver;
   protected WebDriverWait wait;

   private int implicitlyWait;
   private AccountHelper accountHelper;
   private PageNavigationHelper pageNavigationHelper;
   private SearchPageHelper searchPageHelper;
   private ResultsPageHelper resultsPageHelper;
   private HotelPageHelper hotelPageHelper;
   private WishlistsPageHelper wishlistsPageHelper;
   private FilterBoxHelper filterBoxHelper;
   private String browser;

   public void init(int implicitlyWait) throws IOException {
      baseUrl = PropertyLoader.loadProperty("site.url");
      gridHubUrl = PropertyLoader.loadProperty("grid.url");
      capabilities = PropertyLoader.loadCapabilities();
      if (gridHubUrl != null && !gridHubUrl.equals("")) {
         webDriver = WebDriverPool.DEFAULT.getDriver(new URL(gridHubUrl), capabilities);
      } else {
         webDriver = WebDriverPool.DEFAULT.getDriver(capabilities);
      }
      username = PropertyLoader.loadProperty("account.username");
      password = PropertyLoader.loadProperty("account.password");
      setImplicitlyWait(implicitlyWait);
      wait = new WebDriverWait(webDriver, 30);
      accountHelper = new AccountHelper(webDriver, wait, implicitlyWait);
      searchPageHelper = new SearchPageHelper(webDriver, wait, implicitlyWait);
      resultsPageHelper = new ResultsPageHelper(webDriver, wait, implicitlyWait);
      filterBoxHelper = new FilterBoxHelper(webDriver, wait, implicitlyWait);
      hotelPageHelper = new HotelPageHelper(webDriver, wait, implicitlyWait);
      pageNavigationHelper = new PageNavigationHelper(webDriver, wait, implicitlyWait);
      wishlistsPageHelper = new WishlistsPageHelper(webDriver, wait, implicitlyWait);
      pageNavigation().maximazeWindow();
   }

   public void goToMainPage() {
      pageNavigation().goTo(baseUrl);
   }

   public void login() {
      account().loginAs(username, password);
   }

   public void deinit() {
      WebDriverPool.DEFAULT.dismissAll();
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

   public PageNavigationHelper pageNavigation() {
      return pageNavigationHelper;
   }

   public SearchPageHelper searchPage() {
      return searchPageHelper;
   }

   public ResultsPageHelper resultsPage() {
      return resultsPageHelper;
   }

   public HotelPageHelper hotelPage() {
      return hotelPageHelper;
   }

   public WishlistsPageHelper wishlistsPage() {
      return wishlistsPageHelper;
   }

   public FilterBoxHelper filters() {
      return filterBoxHelper;
   }
}
