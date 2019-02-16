import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  //
  public static void main(String[] args) {
    String parameter = "label";
    System.out.println(getStringByPattern("\\d", "bk-icon -sprite-ratings_stars_3"));
    System.out.println(getStringByPattern("[\\,\\d]+", "1,900 rм от центра"));
    String s = "9 963 руб.";
    System.out.println(getStringByPattern("[\\s\\d]+\\d", s));
    s = "jhghgkj (0)";
    System.out.println(getStringByPattern(".+(?=\\s)", s));

  }

  private static String getStringByPatternNoSpace(String regex, String inString) {
    return getStringByPattern(regex, inString).replaceAll("\\s", "");
  }


  private static String getStringByPattern(String regex, String inString) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(inString);
    String result = null;
    while (matcher.find()) {
      result = inString.substring(matcher.start(), matcher.end());
    }
    return result;
  }
}

