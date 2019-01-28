
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String url1 = "?aid=304142;label=gen173nr-1FCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQHoAQH4AQyIAgGoAgM;sid=a74e44c63fdc081681e03b07b572eb6a&from_list=1;checkin=2019-03-24;checkout=2019-04-03;group_rooms=1;group_adults=2;group_children=0;;label=gen173nr-1FCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQHoAQH4AQyIAgGoAgM";
        Pattern pattern = Pattern.compile("aid=.+?;?&");
        Matcher m = pattern.matcher(url1);
        String aid = null;
        String sid = null;
        String label = null;
        while(m.find()) {
            aid = url1.substring(m.start(), m.end());
        }
        pattern = Pattern.compile("label=.+?;?&");
        m = pattern.matcher(url1);
        while (m.find()) {
            label = url1.substring(m.start(), m.end());
        }
        pattern = Pattern.compile("sid=.+?;?&");
        m = pattern.matcher(url1);
        while (m.find()) {
            sid = url1.substring(m.start(), m.end());
        }
        System.out.println(aid+"\n"+label+"\n"+sid);
    }
}
