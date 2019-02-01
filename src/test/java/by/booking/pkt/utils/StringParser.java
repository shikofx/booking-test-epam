package by.booking.pkt.utils;

import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
  private String inString;

  private Pattern pattern;
  Matcher matcher;


  
  public StringParser(String url){
    this.inString = url;
  }

  public String getParameterOfURL(String parameter) {
    Pattern pattern = Pattern.compile("(?<=("+parameter+"=))+[A-Za-z0-9-_]*");
    return getStringByPattern(pattern, inString);
  }

  public static String getHeaderOfURL(String inString){
    Pattern pattern = Pattern.compile((".+/[A-Za-z0-9_-]*"));
    return getStringByPattern(pattern, inString);
  }

  public static String getTotalPrice(String inString){
    Pattern pattern = Pattern.compile("(?<=:).+\\d");
    return getStringByPattern(pattern, inString).replaceAll("\\s", "");
  }
  public static String getNightsCount(String inString){
    Pattern pattern = Pattern.compile("\\d+");
    return getStringByPattern(pattern, inString).replaceAll("\\s", "");
  }

  public static String getStarsCount(String inString){
    Pattern pattern = Pattern.compile("\\d");
    return getStringByPattern(pattern, inString).replaceAll("\\s", "");
  }

  @Nullable
  public static String getStringByPattern(Pattern pattern, String string) {
    Matcher matcher = pattern.matcher(string);
    String result= null;
    while(matcher.find()) {
      result = string.substring(matcher.start(), matcher.end());
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }
}
