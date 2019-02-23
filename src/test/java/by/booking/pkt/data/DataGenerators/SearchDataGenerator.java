package by.booking.pkt.data.DataGenerators;

import by.booking.pkt.model.SearchData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class SearchDataGenerator {
   @Parameter(names = "-count", description = "Search data items count")
   public int count;

   @Parameter(names = "-path", description = "Data file path")
   public String fileName;

   public static void main(String[] args) throws IOException {
      SearchDataGenerator generator = new SearchDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      }catch (ParameterException pe){
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run() throws IOException {
      if(count>5){
         throw new ExceptionInInitializerError("Error count: Count more than 5!");
      }
      List<SearchData> searchs = generateSearchs(count);
      save(searchs, new File(fileName));
   }

   private void save(List<SearchData> searchs, File file) throws IOException {
      Writer writer = new FileWriter(file);
      for(SearchData search:searchs){
         writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
               search.userName(),
               search.userPassword(),
               search.currency(),
               search.placeTo(),
               search.inDate(),
               search.outDate(),
               search.roomsCount(),
               search.adultsCount(),
               search.childrenCount(),
               search.minBudget(),
               search.maxBudget(),
               search.stars(),
               search.wishlistName()));
      }
      writer.close();
   }

   private List<SearchData> generateSearchs(int count) {
      List<SearchData> searchs = new ArrayList<SearchData>();
      for(int i = 1; i<=count; i++){
         searchs.add(new SearchData().
               withUsername("pkt.autotests@gmail.com").
               withPassword("0123456789").
               withCurrency("RUB").
               withPlace("New York").
               withInDate("2019-03-0"+i).
               withOutDate("2019-04-0"+i).
               withRooms(2).
               withAdults(1).
               withChildren(0).
               withMinBudget(i*2000).
               withMaxBudget(i*4000).
               withStars(i).
               withWishlist("to New York "+i));
      }
      return searchs;
   }
}
