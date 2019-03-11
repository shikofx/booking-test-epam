package by.booking.pkt.application;

import by.booking.pkt.web.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class  ApplicationManager {
   private WebDriver webDriver;
   private WebDriverWait wait;

   private AccountHelper accountHelper;
   private PageNavigationHelper pageNavigationHelper;
   private SearchPageHelper searchPageHelper;
   private ResultsPageHelper resultsPageHelper;
   private HotelPageHelper hotelPageHelper;
   private WishlistsPageHelper wishlistsPageHelper;
   private FilterBoxHelper filterBoxHelper;
   private ApplicationProperties appProperties;

   public void init(int implicitlyWait) throws IOException {
      appProperties = new ApplicationProperties().initProperties();
      webDriver = appProperties.getDriver();

      webDriver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
      wait = new WebDriverWait(webDriver, 30);
      accountHelper = new AccountHelper(webDriver, wait, implicitlyWait);
      searchPageHelper = new SearchPageHelper(webDriver, wait, implicitlyWait);
      resultsPageHelper = new ResultsPageHelper(webDriver, wait, implicitlyWait);
      filterBoxHelper = new FilterBoxHelper(webDriver, wait, implicitlyWait);
      hotelPageHelper = new HotelPageHelper(webDriver, wait, implicitlyWait);
      pageNavigationHelper = new PageNavigationHelper(webDriver, wait, implicitlyWait);
      wishlistsPageHelper = new WishlistsPageHelper(webDriver, wait, implicitlyWait);

      windowsNavigation().maximizeWindow();
   }

   public void deinit() {
      webDriver.quit();
      webDriver = null;
   }

   public AccountHelper account() {
      return accountHelper;
   }

   public PageNavigationHelper windowsNavigation() {
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

   public ApplicationProperties appProperties() {
      return appProperties;
   }
}
