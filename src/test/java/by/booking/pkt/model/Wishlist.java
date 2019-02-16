package by.booking.pkt.model;

import java.util.List;
import java.util.Objects;

public class Wishlist {
  private int id;
  private String name;
  private List<Hotel> hotelList;

  public int getId() {
    return id;
  }

  public Wishlist withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Wishlist withName(String name) {
    this.name = name;
    return this;
  }

  public List<Hotel> getHotelList() {
    return hotelList;
  }

  public Wishlist withHotels(List<Hotel> hotelList) {
    this.hotelList = hotelList;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wishlist wishlist = (Wishlist) o;
    return Objects.equals(id, wishlist.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Wishlist{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
