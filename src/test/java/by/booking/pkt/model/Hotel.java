package by.booking.pkt.model;

import java.util.Objects;

public class Hotel {
  private String id;
  private String name;
  private int stars;
  private double distance;
  private int totalPrice;


  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hotel hotel = (Hotel) o;
    return Objects.equals(id, hotel.id) &&
            Objects.equals(name, hotel.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name);
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

  @Override
  public String toString() {
     return "HotelToWishlistTests{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
