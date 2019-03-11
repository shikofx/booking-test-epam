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
   private String place;

   @Parameter(names = "-c", description = "Count of test data")
   private int count;

   @Parameter(names = "-fn", description = "Data file name (ex.: data-for-search)")
   private String fileName;

   @Parameter(names = "-ff", description = "Data file format (xml, json)")
   private String fileFormat;

   @Parameter(names = "-target", description = "For what do you need data: " +
           "\n       'search': for testing of search page" +
           "\n       'wishlist': for testing of wishlists page" +
           "\n       'hotel': for testing of hotel page")
   private String whatTest;

   private final String filePath = "src/test/resources/";

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
         throw new ExceptionInInitializerError("Error: Count greater than 5!");
      }
      switch (fileFormat) {
         case "xml":
            saveAsXML(whatTest, new File(file));
            break;
         case "json":
            saveAsJSON(whatTest, new File(file));
            break;
         default:
            System.out.printf("Invalid file format: %s%n", fileFormat);
            break;
      }
   }

   private void saveAsJSON(String whatTest, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      List<TestData> testData = getTestData(whatTest);
      String jsonString = gson.toJson(testData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(jsonString);
      }
   }

   private void saveAsXML(String whatTest, File file) throws IOException {
      List<TestData> testData = getTestData(whatTest);
      XStream xStream = new XStream();
      xStream.processAnnotations(TestData.class);
      String xmlString = xStream.toXML(testData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xmlString);
      }
   }

   private List<TestData> getTestData(String whatTest) {
      List<TestData> testData = new ArrayList<>();
      switch (whatTest) {
         case "search":
            testData = generateSearchData(count);
            break;
         case "hotel":
            testData = generateHotelData(count);
            break;
         case "wishlist":
            testData = generateWishlists(count);
            break;
      }
      return testData;
   }

   private List<TestData> generateSearchData(int count) {
      List<TestData> testData = new ArrayList<>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withCurrency("RUB").
                 withPlace(place.replaceAll("_", " ")).
                 withInDate(String.format("2019-03-0%d", i + 4)).
                 withOutDate(String.format("2019-04-0%d", i)).
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
      List<TestData> testData = new ArrayList<>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withPlace(place.replaceAll("_", " ")).
                 withCurrency("RUB").
                 withInDate(String.format("2019-03-0%d", i + 4)).
                 withOutDate(String.format("2019-04-0%d", i)).
                 withWishlist(String.format("to %s %d", place, i)));
      }
      return testData;
   }

   private List<TestData> generateWishlists(int count) {
      List<TestData> testData = new ArrayList<>();
      for (int i = 1; i <= count; i++) {
         testData.add(new TestData().
                 withPlace(place.replaceAll("_", " ")).
                 withWishlist(String.format("to %s %d", place, i)));
      }
      return testData;
   }
}
