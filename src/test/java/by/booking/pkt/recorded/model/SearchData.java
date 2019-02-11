package by.booking.pkt.recorded.model;

public class SearchData {

  private String currency;
  private String place;
  private String inDate;
  private String outDate;
  private int roomsCount;
  private int adultsCount;
  private int childrenCount;

  public SearchData() {
    currency = "";
    place = "";
    inDate = "";
    outDate = "";
    roomsCount = 0;
    adultsCount = 2;
    childrenCount = 0;

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
}