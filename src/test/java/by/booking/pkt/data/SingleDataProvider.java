package by.booking.pkt.data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class SingleDataProvider {

  @DataProvider
  public static Object[][] dataFromArray(Method m) throws NoSuchFieldException, IllegalAccessException {
    if (m.isAnnotationPresent(DataSourceSingleAnnotation.class)) {
      if (m.getParameterTypes().length != 1) {
        throw new Error("Method should have a singl parameter: " + m);
      }
      DataSourceSingleAnnotation dataProviderSource =
              m.getAnnotation(DataSourceSingleAnnotation.class);
      Class clazz = dataProviderSource.clazz();
      if (clazz.equals(void.class)) {
        clazz = m.getDeclaringClass();
      }

      Field field = clazz.getField(dataProviderSource.value());
      Object[] data = (Object[]) field.get(null);

      Object[][] result = new Object[data.length][];
      for (int i = 0; i < data.length; i++) {
        result[i] = new Object[]{data[i]};
      }
      return result;
    } else {
      throw new Error("There is no @DataSourceSingleAnnotation annotation on method " + m);
    }
  }

  @DataProvider
  public static Object[][] dataFromList(Method m) throws NoSuchFieldException, IllegalAccessException {
    if (m.isAnnotationPresent(DataSourceSingleAnnotation.class)) {
      if (m.getParameterTypes().length != 1) {
        throw new Error("Method should have a singl parameter: " + m);
      }
      DataSourceSingleAnnotation dataProviderSource =
              m.getAnnotation(DataSourceSingleAnnotation.class);
      Class clazz = dataProviderSource.clazz();
      if (clazz.equals(void.class)) {
        clazz = m.getDeclaringClass();
      }

      Field field = clazz.getField(dataProviderSource.value());
      List<Object> data = (List<Object>) field.get(null);

      Object[][] result = new Object[data.size()][];
      for (int i = 0; i < data.size(); i++) {
        result[i] = new Object[]{data.get(i)};
      }
      return result;
    } else {
      throw new Error("There is no @DataSourceSingleAnnotation annitation on nethod " + m);
    }
  }
}
