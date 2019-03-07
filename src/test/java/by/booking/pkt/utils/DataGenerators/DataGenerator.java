package by.booking.pkt.utils.DataGenerators;

import by.booking.pkt.model.TestData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

   @Parameter(names = "-pl", description = "Place to travel (ex.: New_York)")
   public String place;

   @Parameter(names = "-c", description = "Count of test data")
   public int count;

   @Parameter(names = "-fn", description = "Data file name (ex.: data-for-search)")
   public String fileName;

   @Parameter(names = "-ff", description = "Data file format (xml, json)")
   public String fileFormat;

   @Parameter(names = "-target", description = "For what do you need these data: " +
           "\n       'search': for testing of search page" +
           "\n       'wishlist': for testing of wishlists page" +
           "\n       'hotel': for testing of hotel page")
   public String whatTest;

   public final String filePath = "src/test/resources/";

   public static void main(String[] args) throws IOException {
      DataGenerator generator = new DataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException pe) {
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run() throws IOException {
      String file = filePath + fileName + "." + fileFormat;
      if (count > 5) {
         throw new ExceptionInInitializerError("Error: Count more than 5!");
      }
       if (fileFormat.equals("xml"))
         saveAsXML(whatTest, new File(file));
      else if (fileFormat.equals("json"))
         saveAsJSON(whatTest, new File(file));
      else
         System.out.println("Unvalid file format: " + fileFormat);
   }

   private void saveAsJSON(String whatTest, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      List<TestData> testData = new ArrayList<TestData>();
      if (whatTest.equals("search"))
         testData = generateSearchData(count);
      else if (whatTest.equals("hotel"))
         testData = generateHotelData(count);
      else if (whatTest.equals("wishlist"))
         testData = generateWishlists(count);
      String jsonString = gson.toJson(testData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(jsonString);
      }
   }

   private void saveAsXML(String whatTest, File file) throws IOException {
      List<TestData> testData = generateSearchData(count);
      XStream xStream = new XStream();
      xStream.processAnnotations(TestData.class);
      String xmlString = xStream.toXML(testData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xmlString);
      }
   }

   private List<TestData> generateSearchData(int count) {
      List<TestData> testData = new ArrayList<TestData>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withCurrency("RUB").
                 withPlace(place.replaceAll("_", " ")).
                 withInDate("2019-03-0" + (i + 4)).
                 withOutDate("2019-04-0" + i).
                 withRooms(2).
                 withAdults(i).
                 withChildren(i - 1).
                 withMinBudget(i * 2000).
                 withMaxBudget(i * 4000).
                 withStars(i));
      }
      return testData;
   }

   private List<TestData> generateHotelData(int count) {
      List<TestData> testData = new ArrayList<TestData>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withPlace(place.replaceAll("_", " ")).
                 withCurrency("RUB").
                 withInDate("2019-03-0" + (i + 4)).
                 withOutDate("2019-04-0" + i).
                 withWishlist("to " + place + " " + i));
      }
      return testData;
   }

   private List<TestData> generateWishlists(int count) {
      List<TestData> testData = new ArrayList<TestData>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withPlace(place.replaceAll("_", " ")).
                 withWishlist("to " + place + " " + i));
      }
      return testData;
   }
}
