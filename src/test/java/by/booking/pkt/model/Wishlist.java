package by.booking.pkt.model;

import java.util.Objects;

public class Wishlist {
  public Wishlist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wishlist that = (Wishlist) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }
}
