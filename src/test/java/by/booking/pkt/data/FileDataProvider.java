package by.booking.pkt.data;

import by.booking.pkt.model.SearchData;
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileDataProvider {

  @DataProvider
  public static Object[] searchDataFromCSV(Method m) throws IOException {

    List<SearchData> result = null;
    if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
      File csvFile = new File(dataSource.value());

      result = new ArrayList<SearchData>();
      CSVReader reader = new CSVReader(new FileReader(csvFile));
      String[] nextLine;
      SearchData data = new SearchData();
      while ((nextLine = reader.readNext()) != null) {
        System.out.println(m.getName() + "\\\\" + Arrays.toString(nextLine));
        data = new SearchData().
                withUsername(nextLine[0]).
                withPassword(nextLine[1]).
                withCurrency(nextLine[2]).
                withPlace(nextLine[3]).
                withInDate(nextLine[4]).
                withOutDate(nextLine[5]).
                withRooms(Integer.parseInt(nextLine[6])).
                withAdults(Integer.parseInt(nextLine[7])).
                withChildren(Integer.parseInt(nextLine[8])).
                withMinBudget(Integer.parseInt(nextLine[9])).
                withMaxBudget(Integer.parseInt(nextLine[10])).
                withStars(Integer.parseInt(nextLine[11])).
                withWishlist(nextLine[12]);
        result.add(data);
      }
    } else {
      throw new Error("There is no @DataSourceFileAnnotation on method " + m);
    }
    return result.toArray(new Object[result.size()]);
  }

  @DataProvider
  public static Object[][] dataFromCSV(Method m) throws IOException {

    if (m.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      int length = m.getParameterTypes().length;
      DataSourceFileAnnotation dataSource = m.getAnnotation(DataSourceFileAnnotation.class);
      File csvFile = new File(dataSource.value());

      List<Object[]> result = new ArrayList<Object[]>();
      CSVReader reader = new CSVReader(new FileReader(csvFile));
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        //System.out.println(Arrays.toString(nextLine));
        Object[] parameters = new Object[length];
        for (int i = 0; i < length; i++) {
          if (i < nextLine.length) {
            parameters[i] = nextLine[i];
          } else {
            parameters[i] = null;
          }
        }

        result.add(parameters);
      }
      return result.toArray(new Object[result.size()][]);
    } else {
      throw new Error("There is no @DataSourceFileAnnotation on method " + m);
    }
  }


  @DataProvider
  public static Iterator<Object[]> dataFromCSVLazy(Method method) throws IOException {
    if (method.isAnnotationPresent(DataSourceFileAnnotation.class)) {
      int length = method.getParameterTypes().length;
      DataSourceFileAnnotation dataSource = method.getAnnotation(DataSourceFileAnnotation.class);
      File csvFile = new File(dataSource.value());
      return new CsvFileIterator(csvFile, length);
    } else {
      throw new Error("There is no @DataSourceFileAnnotation annotation on method " + method);
    }
  }

  private static class CsvFileIterator implements Iterator<Object[]> {

    private final CSVReader reader;
    private int length;
    private String[] nextLine;

    public CsvFileIterator(File csvFile, int length) throws FileNotFoundException {
      this.length = length;
      reader = new CSVReader(new FileReader(csvFile));
    }

    public boolean hasNext() {
      try {
        nextLine = reader.readNext();
        return nextLine != null;
      } catch (IOException e) {
        return false;
      }
    }

    public Object[] next() {
      if (nextLine == null) {
        return null;
      }
      System.out.println(Arrays.toString(nextLine));
      Object[] parameters = new Object[length];
      for (int i = 0; i < length; i++) {
        if (i < nextLine.length) {
          parameters[i] = nextLine[i];
        } else {
          parameters[i] = null;
        }
      }
      return parameters;
    }


    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}

