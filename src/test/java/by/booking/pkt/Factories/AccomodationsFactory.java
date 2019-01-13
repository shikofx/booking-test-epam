package by.booking.pkt.Factories;

import by.booking.pkt.MainSearchTests;
import org.testng.annotations.Factory;

public class AccomodationsFactory {

  @Factory
  public Object[] accomodationsTestFactory() {
    int n = 1;
    Object[] tests = new Object[n];
    for (int i = 0; i < n; i++) {
      tests[i] = new MainSearchTests();
    }
    return tests;
  }
}
