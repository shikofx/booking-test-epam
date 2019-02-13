package by.booking.pkt.model;

public class SearchData {
  private int parametersCount;
  private String username;
  private String password;
  private String currency;
  private String place;
  private String inDate;
  private String outDate;
  private int roomsCount;
  private int adultsCount;
  private int childrenCount;
  private int minBudget;
  private int maxBudget;
  private int stars;

  public SearchData() {
    username = "";
    password = "";
    currency = "";
    place = "";
    inDate = "";
    outDate = "";
    roomsCount = 1;
    adultsCount = 1;
    childrenCount = 0;
    minBudget = 0;
    maxBudget = 0;
    stars = 0;
  }

  public SearchData withUsername(String username) {
    this.username = username;
    return this;
  }


  public SearchData withPassword(String password) {
    this.password = password;
    return this;
  }

  public SearchData withCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public SearchData withPlace(String place) {
    this.place = place;
    return this;
  }

  public SearchData withInDate(String inDate) {
    this.inDate = inDate;
    return this;
  }

  public SearchData withOutDate(String outDate) {
    this.outDate = outDate;
    return this;
  }

  public SearchData withRooms(int roomsCount) {
    this.roomsCount = roomsCount;
    return this;
  }

  public SearchData withAdults(int adultsCount) {
    this.adultsCount = adultsCount;
    return this;
  }

  public SearchData withChildren(int childrenCount) {
    this.childrenCount = childrenCount;
    return this;
  }

  public SearchData withMinBudget(int budget) {
    this.maxBudget = budget;
    return this;
  }

  public SearchData withMaxBudget(int budget) {
    this.maxBudget = budget;
    return this;
  }

  public SearchData withStars(int stars) {
    this.stars = stars;
    return this;
  }

  public String userName() {
    return username;
  }

  public String userPassword() {
    return password;
  }

  public String getCurrency() {
    return currency;
  }

  public String withPlace() {
    return place;
  }

  public String getInDate() {
    return inDate;
  }

  public String getOutDate() {
    return outDate;
  }

  public int getRoomsCount() {
    return roomsCount;
  }

  public int getAdultsCount() {
    return adultsCount;
  }

  public int getChildrenCount() {
    return childrenCount;
  }

  public int maxBudget() {
    return maxBudget;
  }

  public int minBudget() {
    return minBudget;
  }

  public int stars() {
    return stars;
  }
}