package by.booking.pkt.utils;

import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemUrlParser {
  private String url;

  private Pattern pattern;
  Matcher matcher;


  
  public ItemUrlParser(String url){
    this.url = url;
  }

  public String getParameter(String parameter) {
    pattern = Pattern.compile("(?<=("+parameter+"=))+[A-Za-z0-9-_]*");
    return getStringByPattern(pattern, url);
  }

  public String getHeader(){
    pattern = Pattern.compile((".+/[A-Za-z0-9_-]*"));
    return getStringByPattern(pattern, url);
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
