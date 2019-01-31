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

  public String getParameter(String parameter) {
    pattern = Pattern.compile("(?<=("+parameter+"=))+[A-Za-z0-9-_]*");
    return getStringByPattern(pattern, inString);
  }

  public String getHeader(){
    pattern = Pattern.compile((".+/[A-Za-z0-9_-]*"));
    return getStringByPattern(pattern, inString);
  }

  public double getTotalPrice(){
    pattern = Pattern.compile("\\d*\\s*\\d+");
    return Double.parseDouble(getStringByPattern(pattern, inString).replaceAll("\\s", ""));
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
