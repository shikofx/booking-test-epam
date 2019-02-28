package by.booking.pkt.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

   private static final String DEBUG_PROPERTIES = "/application-debug.properties";
   private static final String CURRENT_PROPERTIES = "/application.properties";

   public static Capabilities loadCapabilities() throws IOException {
      return loadCapabilities(System.getProperty(CURRENT_PROPERTIES, DEBUG_PROPERTIES));
   }

   public static Capabilities loadCapabilities(String fromResource) throws IOException {
      Properties properties = new Properties();
      properties.load(PropertyLoader.class.getResourceAsStream(fromResource));
      String capabilitiesFile = properties.getProperty("capabilities");

      Properties capsProps = new Properties();
      capsProps.load(PropertyLoader.class.getResourceAsStream(capabilitiesFile));

      DesiredCapabilities capabilities = new DesiredCapabilities();
      for (String name : capsProps.stringPropertyNames()) {
         String value = capsProps.getProperty(name);
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

   public static String loadProperty(String name) throws IOException {
      String s = System.getProperty(CURRENT_PROPERTIES);
      return loadProperty(name, System.getProperty(CURRENT_PROPERTIES, DEBUG_PROPERTIES));
   }

   public static String loadProperty(String name, String fromResource) throws IOException {
      Properties properties = new Properties();
      properties.load(PropertyLoader.class.getResourceAsStream(fromResource));

      return properties.getProperty(name);
   }

}