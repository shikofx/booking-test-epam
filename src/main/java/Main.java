import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  //
  public static void main(String[] args) {
    String parameter = "label";
    System.out.println(getStringByPattern("\\d", "bk-icon -sprite-ratings_stars_3"));
    System.out.println(getStringByPattern("\\d+", "900 rм от центра"));
    String s = (getStringByPatternNoSpace("\\s.*?\\s", "900 м от центра"));
    int k = 1;
    if(s.length()>1){
      k = 1000;
    }
    System.out.println(k);
  }

  public static String getStringByPatternNoSpace(String regex, String inString) {
    return getStringByPattern(regex, inString).replaceAll("\\s", "");
  }


  public static String getStringByPattern(String regex, String inString) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inString);
    String result = null;
    while (matcher.find()) {
      result = inString.substring(matcher.start(), matcher.end());
    }
    return result;
  }
}

