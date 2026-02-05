package moprocoeval.airline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airline {
  // generated properties
  private String name;
  // end of generated properties

  // generated associations
  private List<Flight> flights = new ArrayList<Flight>();

  // end of generated associations

  // generated getters and setters
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public List<Flight> getFlights() {
    return (List<Flight>) Collections.unmodifiableList(this.flights);
  }

  public int sizeOfFlights() {
    return this.flights.size();
  }

  public void addToFlights(Flight newValue) {
    if (newValue != null && !this.flights.contains(newValue)) {
      this.flights.add(newValue);
      newValue.setAirline(this);
    }
  }

  public void removeFromFlights(Flight oldValue) {
    if (oldValue != null && this.flights.contains(oldValue)) {
      this.flights.remove(oldValue);
      oldValue.setAirline(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If a flight with the specified ID already exists, raise an exception. Otherwise add the
   *     flight to the airline.
   */
  public void addFlight(Flight flight) {
    // generated start
for (Flight existingFlight : this.flights) {
      if (existingFlight.getFlightNumber().equals(flight.getFlightNumber())) {
        throw new java.lang.IllegalArgumentException("Flight with ID " + flight.getFlightNumber() + " already exists");
      }
    }
    this.addToFlights(flight);
// generated end
    // insert your code here

  }
}
