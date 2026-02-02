package moprocoeval.airline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Passenger {
  // generated properties
  private String passengerId;
  private String name;
  // end of generated properties

  // generated associations
  private List<Reservation> reservations = new ArrayList<Reservation>();

  // end of generated associations

  // generated getters and setters
  public String getPassengerId() {
    return this.passengerId;
  }

  public void setPassengerId(String passengerId) {
    this.passengerId = passengerId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public List<Reservation> getReservations() {
    return (List<Reservation>) Collections.unmodifiableList(this.reservations);
  }

  public int sizeOfReservations() {
    return this.reservations.size();
  }

  public void addToReservations(Reservation newValue) {
    if (newValue != null && !this.reservations.contains(newValue)) {
      this.reservations.add(newValue);
      newValue.setPassenger(this);
    }
  }

  public void removeFromReservations(Reservation oldValue) {
    if (oldValue != null && this.reservations.contains(oldValue)) {
      this.reservations.remove(oldValue);
      oldValue.setPassenger(null);
    }
  }
  // end of generated accessors for associations

  // generated operations

}
