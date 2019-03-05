package by.booking.pkt.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Search")
public class TestData {
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

   @Override
   public String toString() {
      String testString = "";
      if (currency.length() > 0)
         testString += "currency='" + currency + "\'; ";
      if (place.length() > 0)
         testString += "place='" + place + "\'; ";
      if (inDate.length() > 0)
         testString += "inDate='" + inDate + "\'; ";
      if (outDate.length() > 0)
         testString += "outDate='" + outDate + "\'; ";
      if (rooms > 0)
         testString += "rooms='" + rooms + "\'; ";
      if (adults > 0)
         testString += "adults='" + adults + "\'; ";
      if (children > 0)
         testString += "children='" + children + "\'; ";
      if (budgetMin > 0)
         testString += "budgetMin='" + budgetMin + "\'; ";
      if (budgetMax > 0)
         testString += "budgetMax='" + budgetMax + "\'; ";
      if (stars > 0)
         testString += "stars='" + stars + "\'; ";
      if (wishlistName.length() > 0)
         testString += "wishlistName='" + wishlistName + '\'';
      return "TestData{" +
              testString + '}';
   }

  @XStreamAsAttribute
  private int budgetMax;
  @XStreamAsAttribute
  private int stars;
  private String wishlistName;


   public TestData() {
    currency = "";
    place = "";
    inDate = "";
    outDate = "";
      rooms = 0;
      adults = 0;
    children = 0;
    budgetMin = 0;
    budgetMax = 0;
    stars = 0;
      wishlistName = "";
  }


   public TestData withCurrency(String currency) {
    this.currency = currency;
    return this;
  }

   public TestData withPlace(String place) {
    this.place = place;
    return this;
  }

   public TestData withInDate(String inDate) {
    this.inDate = inDate;
    return this;
  }

   public TestData withOutDate(String outDate) {
    this.outDate = outDate;
    return this;
  }

   public TestData withRooms(int roomsCount) {
    this.rooms = roomsCount;
    return this;
  }

   public TestData withAdults(int adultsCount) {
    this.adults = adultsCount;
    return this;
  }

   public TestData withChildren(int childrenCount) {
    this.children = childrenCount;
    return this;
  }

   public TestData withMinBudget(int budget) {
    this.budgetMin = budget;
    return this;
  }

   public TestData withMaxBudget(int budget) {
    this.budgetMax = budget;
    return this;
  }

   public TestData withStars(int stars) {
    this.stars = stars;
    return this;
  }

   public TestData withWishlist(String wishlistName) {
    this.wishlistName = wishlistName;
    return this;
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