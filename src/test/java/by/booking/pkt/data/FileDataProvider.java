package by.booking.pkt.data;

import by.booking.pkt.model.SearchData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class FileDataProvider {

  @DataProvider
  public static Iterator<Object[]> searchDataFromJSON(Method m) throws IOException {
    List<Object[]> result = new ArrayList<Object[]>();
    List<SearchData> searchs = new ArrayList<SearchData>();
    if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
      BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())));
      Gson gson = new Gson();//;
      searchs = gson.fromJson(reader, new TypeToken<List<SearchData>>(){}.getType());
    } else {
      throw new Error("There is no @DataSourceFileAnnotation on method " + m);
    }
    return searchs.stream().map((s)->new Object[] {s}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public static Iterator<Object[]> searchDataFromXML(Method m) throws IOException {
    List<Object[]> result = new ArrayList<Object[]>();
    List<SearchData> searchs = new ArrayList<SearchData>();
    if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
      BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())));
      XStream xStream = new XStream();
      xStream.processAnnotations(SearchData.class);
      searchs = (List<SearchData>) xStream.fromXML(reader);
    } else {
      throw new Error("There is no @DataSourceFileAnnotation on method " + m);
    }
    return searchs.stream().map((s)->new Object[] {s}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public static Iterator<Object[]> searchDataFromCSV(Method m) throws IOException {
    List<Object[]> result = new ArrayList<Object[]>();
    if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
      BufferedReader reader = new BufferedReader(new FileReader(new File(dataSource.value())));
      String nextLine = reader.readLine();
      while (nextLine != null) {
        String[] parameters = nextLine.split(";");
        result.add(new Object[]{new SearchData().
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
    } else {
      throw new Error("There is no @DataSourceFileAnnotation on method " + m);
    }
    return result.iterator();
  }


}

