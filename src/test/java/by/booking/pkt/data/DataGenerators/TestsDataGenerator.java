package by.booking.pkt.data.DataGenerators;

import by.booking.pkt.model.TestsData;
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

public class TestsDataGenerator {

   @Parameter(names = "-pl", description = "Place for travel")
   public String place;

   @Parameter(names = "-c", description = "Search data items count")
   public int count;

   @Parameter(names = "-fn", description = "Data file name: 'data-for-search'")
   public String fileName;

   @Parameter(names = "-ff", description = "Data file path")
   public String fileFormat;

   @Parameter(names = "-t", description = "For what do you need these data: " +
           "\ns: for testing of search page" +
           "\nw: for testing of wishlists page" +
           "\nh: for testing of hotel page")
   public String whatTest;

   public String path = "src/test/resources/";
   public String username = "pkt.autotests@gmail.com";
   public String password = "0123456789";

   public static void main(String[] args) throws IOException {
      TestsDataGenerator generator = new TestsDataGenerator();
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
      String file = path + fileName + "." + fileFormat;
      if (count > 5) {
         throw new ExceptionInInitializerError("Error count: Count more than 5!");
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
      List<TestsData> testsData = new ArrayList<TestsData>();
      if (whatTest.equals("s"))
         testsData = generateSearchData(count);
      else if (whatTest.equals("h"))
         testsData = generateHotelData(count);
      else if (whatTest.equals("w"))
         testsData = generateWishlists(count);
      String jsonString = gson.toJson(testsData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(jsonString);
      }
   }

   private void saveAsXML(String whatTest, File file) throws IOException {
      List<TestsData> testData = generateSearchData(count);
      XStream xStream = new XStream();
      xStream.processAnnotations(TestsData.class);
      String xmlString = xStream.toXML(testData);
      try (Writer writer = new FileWriter(file)) {
         writer.write(xmlString);
      }
   }

   private List<TestsData> generateSearchData(int count) {
      List<TestsData> testsData = new ArrayList<TestsData>();
      for (int i = 1; i <= count; i++) {
         testsData.add(new TestsData().
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
      return testsData;
   }

   private List<TestsData> generateHotelData(int count) {
      List<TestsData> testsData = new ArrayList<TestsData>();
      for (int i = 1; i <= count; i++) {
         testsData.add(new TestsData().
                 withPlace(place.replaceAll("_", " ")).
                 withCurrency("RUB").
                 withInDate("2019-03-0" + (i + 4)).
                 withOutDate("2019-04-0" + i).
                 withWishlist("to " + place + " " + i));
      }
      return testsData;
   }

   private List<TestsData> generateWishlists(int count) {
      List<TestsData> testsData = new ArrayList<TestsData>();
      for (int i = 1; i <= count; i++) {
         testsData.add(new TestsData().
                 withPlace(place.replaceAll("_", " ")).
                 withWishlist("to " + place + " " + i));
      }
      return testsData;
   }
}
