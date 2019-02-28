package by.booking.pkt.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Search")
public class TestsData {
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
      return "TestsData{" +
              testString + '}';
   }

  @XStreamAsAttribute
  private int budgetMax;
  @XStreamAsAttribute
  private int stars;
  private String wishlistName;


   public TestsData() {
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


   public TestsData withCurrency(String currency) {
    this.currency = currency;
    return this;
  }

   public TestsData withPlace(String place) {
    this.place = place;
    return this;
  }

   public TestsData withInDate(String inDate) {
    this.inDate = inDate;
    return this;
  }

   public TestsData withOutDate(String outDate) {
    this.outDate = outDate;
    return this;
  }

   public TestsData withRooms(int roomsCount) {
    this.rooms = roomsCount;
    return this;
  }

   public TestsData withAdults(int adultsCount) {
    this.adults = adultsCount;
    return this;
  }

   public TestsData withChildren(int childrenCount) {
    this.children = childrenCount;
    return this;
  }

   public TestsData withMinBudget(int budget) {
    this.budgetMin = budget;
    return this;
  }

   public TestsData withMaxBudget(int budget) {
    this.budgetMax = budget;
    return this;
  }

   public TestsData withStars(int stars) {
    this.stars = stars;
    return this;
  }

   public TestsData withWishlist(String wishlistName) {
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