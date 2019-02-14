package by.booking.pkt.model;

public class Hotel {
  private String id;
  private String name;
  private int stars;
  private double distance;
  private int totalPrice;


  public String getId() {
    return id;
  }

  public Hotel withID(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Hotel withName(String name) {
    this.name = name;
    return this;
  }

  public int starsCount() {
    return stars;
  }

  public Hotel withStars(int stars) {
    this.stars = stars;
    return this;
  }

  public double getDistance() {
    return distance;
  }

  public Hotel withDistance(double distance) {
    this.distance = distance;
    return this;
  }

  public int currentPrice() {
    return totalPrice;
  }

  public Hotel withTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }
}
