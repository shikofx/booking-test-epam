package by.booking.pkt.application;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApplicationProperties {
   private static final String DEBUG_PROPERTIES = "/application-debug.properties";
   private static final String CURRENT_PROPERTIES = "/application.properties";

   private static String platform;
   private static Capabilities browserCapabilities;
   private static String browserCapabilitiesFileName;
   private static String mainPageUrl;
   private static String gridHubUrl;
   private static String username;
   private static String password;

   public static String getMaimPageURL() {
      return mainPageUrl;
   }

   public static String getUsername() {
      return username;
   }


   public static String getPassword() {
      return password;
   }

   public ApplicationProperties initProperties() throws IOException {
      mainPageUrl = loadProperty("site.url", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      gridHubUrl = loadProperty("grid.url", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      username = loadProperty("account.username", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      password = loadProperty("account.password", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      platform = loadProperty("platform.name", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      browserCapabilitiesFileName = loadProperty("browserCapabilities", CURRENT_PROPERTIES, DEBUG_PROPERTIES);
      browserCapabilities = loadCapabilities(browserCapabilitiesFileName);
      return this;
   }

   private String loadProperty(String name, String source, String debugSource) throws IOException {
      Properties properties = new Properties();
      String fromResource = System.getProperty(source, debugSource);
      properties.load(getClass().getResourceAsStream(fromResource));
      return properties.getProperty(name);
   }

   private Capabilities loadCapabilities(String fileName) throws IOException {
      Properties fromProperties = new Properties();
      fromProperties.load(getClass().getResourceAsStream(fileName));
      DesiredCapabilities capabilities = new DesiredCapabilities();
      for (String name : fromProperties.stringPropertyNames()) {
         String value = fromProperties.getProperty(name);
         if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
            capabilities.setCapability(name, Boolean.valueOf(value));
         } else if (value.startsWith("file:")) {
            capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
         } else {
            capabilities.setCapability(name, value);
         }
      }
      return capabilities;
   }

   public WebDriver getDriver() throws MalformedURLException {
      if (gridHubUrl != null && !gridHubUrl.equals("")) {
         DesiredCapabilities remoteCapabilities = new DesiredCapabilities();
         remoteCapabilities.setBrowserName(browserCapabilities.getBrowserName());
         remoteCapabilities.setPlatform(Platform.fromString(platform));
         return new RemoteWebDriver(new URL(gridHubUrl), remoteCapabilities);
      } else {
         String browser = browserCapabilities.getBrowserName();
         if (browser.equals(BrowserType.CHROME)) {
            return new ChromeDriver();
         } else if (browser.equals(BrowserType.FIREFOX)) {
            return new FirefoxDriver();
         } else if (browser.equals(BrowserType.IE)) {
            return new InternetExplorerDriver();
         } else if (browser.equals(BrowserType.OPERA)) {
            return new OperaDriver();
         } else if (browser.equals(BrowserType.SAFARI)) {
            return new SafariDriver();
         } else if (browser.equals(BrowserType.PHANTOMJS)) {
            return new PhantomJSDriver();
         } else if (browser.equals(BrowserType.HTMLUNIT)) {
            return new HtmlUnitDriver();
         }
      }
      return null;
   }
}
