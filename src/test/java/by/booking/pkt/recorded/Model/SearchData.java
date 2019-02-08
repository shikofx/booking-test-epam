package by.booking.pkt.recorded.Model;

public class SearchData {
  private final String currency;
  private final String place;
  private final String checkInDate;
  private final String checkOutDate;
  private final int roomsCount;
  private final int adultsCount;
  private final int childrenCount;

  public SearchData(String currency, String place, String checkInDate, String checkOutDate, int roomsCount, int adultsCount, int childrenCount) {
    this.currency = currency;
    this.place = place;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.roomsCount = roomsCount;
    this.adultsCount = adultsCount;
    this.childrenCount = childrenCount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getPlace() {
    return place;
  }

  public String getCheckInDate() {
    return checkInDate;
  }

  public String getCheckOutDate() {
    return checkOutDate;
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
