package moprocoeval.airline;


public abstract class Seat {
  // generated properties
  private String seatNumber;
  private SeatStatus status;
  // end of generated properties

  // generated associations
  private Flight flight;
  private Reservation reservation;

  // end of generated associations

  // generated getters and setters
  public String getSeatNumber() {
    return this.seatNumber;
  }

  public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public SeatStatus getStatus() {
    return this.status;
  }

  public void setStatus(SeatStatus status) {
    this.status = status;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Flight getFlight() {
    return this.flight;
  }

  public void setFlight(Flight newValue) {
    if (this.flight != newValue) {
      Flight oldValue = flight;
      if (oldValue != null) {
        this.flight = null;
        oldValue.removeFromSeats(this);
      }
      this.flight = newValue;
      if (newValue != null) newValue.addToSeats(this);
    }
  }

  public Reservation getReservation() {
    return this.reservation;
  }

  public void setReservation(Reservation newValue) {
    if (this.reservation != newValue) {
      Reservation oldValue = reservation;
      if (oldValue != null) {
        this.reservation = null;
        oldValue.setSeat(null);
      }
      this.reservation = newValue;
      if (newValue != null) newValue.setSeat(this);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If the seat is not available, raise an exception. Otherwise create a new reservation
   *     and associate it with the current passenger and the provided seat. Set the reservation date
   *     to now. Finally, set the seat's status to reserved and return the reservation.
   */
  public Reservation reserve(Passenger passenger) {
    // generated start
if (this.status != SeatStatus.AVAILABLE) {
      throw new IllegalArgumentException("Seat is not available");
    }
    Reservation reservation = new Reservation();
    reservation.setPassenger(passenger);
    reservation.setSeat(this);
    reservation.setReservationDate(new java.util.Date());
    this.setStatus(SeatStatus.RESERVED);
    return reservation;
// generated end
    // insert your code here

  }
}
