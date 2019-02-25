package by.booking.pkt.data;

import by.booking.pkt.model.TestsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataProvider {

   @DataProvider
   public static Iterator<Object[]> testDataFromJSON(Method m) throws IOException {
      List<TestsData> testsData = new ArrayList<TestsData>();
      if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
         DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
         Gson gson = new Gson();
         try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())))) {
            testsData = gson.fromJson(reader, new TypeToken<List<TestsData>>() {
            }.getType());
         }
      } else {
         throw new Error("There is no @DataSourceFileAnnotation on method " + m);
      }
      return testsData.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
   }

   @DataProvider
   public static Iterator<Object[]> testDataFromXML(Method m) throws IOException {
      List<TestsData> testsData = new ArrayList<TestsData>();
      if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
         DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
         XStream xStream = new XStream();
         xStream.processAnnotations(TestsData.class);
         try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())))) {
            testsData = (List<TestsData>) xStream.fromXML(reader);
         }
      } else {
         throw new Error("There is no @DataSourceFileAnnotation on method " + m);
      }
      return testsData.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
   }

   @DataProvider
   public static Iterator<Object[]> testDataFromCSV(Method m) throws IOException {
      List<Object[]> testData = new ArrayList<Object[]>();
      if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
         DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
         try (BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
               String[] parameters = nextLine.split(";");
               testData.add(new Object[]{new TestsData().
                       withUsername(parameters[0]).
                       withPassword(parameters[1]).
                       withCurrency(parameters[2]).
                       withPlace(parameters[3]).
                       withInDate(parameters[4]).
                       withOutDate(parameters[5]).
                       withRooms(Integer.parseInt(parameters[6])).
                       withAdults(Integer.parseInt(parameters[7])).
                       withChildren(Integer.parseInt(parameters[8])).
                       withMinBudget(Integer.parseInt(parameters[9])).
                       withMaxBudget(Integer.parseInt(parameters[10])).
                       withStars(Integer.parseInt(parameters[11])).
                       withWishlist(parameters[12])});
               nextLine = reader.readLine();
            }
         }
      } else {
         throw new Error("There is no @DataSourceFileAnnotation on method " + m);
      }
      return testData.iterator();
   }
}

