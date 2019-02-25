package by.booking.pkt.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Search")
public class SearchData {
  private String username;
  private String password;
  @XStreamAsAttribute
  private String currency;
  private String place;
  private String inDate;
  private String outDate;
  @XStreamAsAttribute
  private int rooms;
  @XStreamAsAttribute
  private int adults;
  @XStreamAsAttribute
  private int children;
  @XStreamAsAttribute
  private int budgetMin;
  @XStreamAsAttribute
  private int budgetMax;
  @XStreamAsAttribute
  private int stars;
  private String wishlistName;

  @Override
  public String toString() {
    return "SearchData{" +
            "place='" + place + '\'' +
            ", rooms=" + rooms +
            ", adults=" + adults +
            ", children=" + children +
            ", budgetMin=" + budgetMin +
            ", budgetMax=" + budgetMax +
            ", stars=" + stars +
            '}';
  }



  public SearchData() {
    username = "";
    password = "";
    currency = "";
    place = "";
    inDate = "";
    outDate = "";
    rooms = 1;
    adults = 1;
    children = 0;
    budgetMin = 0;
    budgetMax = 0;
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
    this.rooms = roomsCount;
    return this;
  }

  public SearchData withAdults(int adultsCount) {
    this.adults = adultsCount;
    return this;
  }

  public SearchData withChildren(int childrenCount) {
    this.children = childrenCount;
    return this;
  }

  public SearchData withMinBudget(int budget) {
    this.budgetMin = budget;
    return this;
  }

  public SearchData withMaxBudget(int budget) {
    this.budgetMax = budget;
    return this;
  }

  public SearchData withStars(int stars) {
    this.stars = stars;
    return this;
  }

  public SearchData withWishlist(String wishlistName) {
    this.wishlistName = wishlistName;
    return this;
  }

  public String userName() {
    return username;
  }

  public String userPassword() {
    return password;
  }

  public String currency() {
    return currency;
  }

  public String placeTo() {
    return place;
  }

  public String inDate() {
    return inDate;
  }

  public String outDate() {
    return outDate;
  }

  public int roomsCount() {
    return rooms;
  }

  public int adultsCount() {
    return adults;
  }

  public int childrenCount() {
    return children;
  }

  public int maxBudget() {
    return budgetMax;
  }

  public int minBudget() {
    return budgetMin;
  }

  public int stars() {
    return stars;
  }

  public String wishlistName() {
    return wishlistName;
  }
}