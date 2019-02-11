package by.booking.pkt.recorded.model;

import java.util.Objects;

public class Filter {
  private int budget;
  private int stars;

  public Filter(int budget, int stars) {
    this.budget = budget;
    this.stars = stars;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Filter that = (Filter) o;
    return budget == that.budget &&
            stars == that.stars;
  }

  @Override
  public int hashCode() {

    return Objects.hash(budget, stars);
  }
}
