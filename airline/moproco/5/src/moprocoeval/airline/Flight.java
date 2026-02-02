package moprocoeval.airline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flight {
  // generated properties
  private String flightNumber;
  private Date date;
  // end of generated properties

  // generated associations
  private Airline airline;
  private List<Seat> seats = new ArrayList<Seat>();

  // end of generated associations

  // generated getters and setters
  public String getFlightNumber() {
    return this.flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Airline getAirline() {
    return this.airline;
  }

  public void setAirline(Airline newValue) {
    if (this.airline != newValue) {
      Airline oldValue = airline;
      if (oldValue != null) {
        this.airline = null;
        oldValue.removeFromFlights(this);
      }
      this.airline = newValue;
      if (newValue != null) newValue.addToFlights(this);
    }
  }

  public List<Seat> getSeats() {
    return (List<Seat>) Collections.unmodifiableList(this.seats);
  }

  public int sizeOfSeats() {
    return this.seats.size();
  }

  public void addToSeats(Seat newValue) {
    if (newValue != null && !this.seats.contains(newValue)) {
      this.seats.add(newValue);
      newValue.setFlight(this);
    }
  }

  public void removeFromSeats(Seat oldValue) {
    if (oldValue != null && this.seats.contains(oldValue)) {
      this.seats.remove(oldValue);
      oldValue.setFlight(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If a seat with the given seat number already exists, raise an exception. Otherwise add
   *     the seat to the flight and set the seat's status to available.
   */
  public void addSeat(Seat s) {
    // generated start
    // generated end
    // insert your code here

  }
}
