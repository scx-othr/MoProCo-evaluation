package moprocoeval.airline;

import java.util.Date;

public class Reservation {
  // generated properties
  private Date reservationDate;
  // end of generated properties

  // generated associations
  private Seat seat;
  private Passenger passenger;

  // end of generated associations

  // generated getters and setters
  public Date getReservationDate() {
    return this.reservationDate;
  }

  public void setReservationDate(Date reservationDate) {
    this.reservationDate = reservationDate;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Seat getSeat() {
    return this.seat;
  }

  public void setSeat(Seat newValue) {
    if (this.seat != newValue) {
      Seat oldValue = seat;
      if (oldValue != null) {
        this.seat = null;
        oldValue.setReservation(null);
      }
      this.seat = newValue;
      if (newValue != null) newValue.setReservation(this);
    }
  }

  public Passenger getPassenger() {
    return this.passenger;
  }

  public void setPassenger(Passenger newValue) {
    if (this.passenger != newValue) {
      Passenger oldValue = passenger;
      if (oldValue != null) {
        this.passenger = null;
        oldValue.removeFromReservations(this);
      }
      this.passenger = newValue;
      if (newValue != null) newValue.addToReservations(this);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If the new selected seat is not available, raise an exception. Otherwise set the status
   *     of the reservation's current seat to available, associate the reservation with the new seat
   *     and set the new seat's status to reserved.
   */
  public void changeSeat(Seat newSeat) {
    // generated start
if (newSeat.getStatus() != SeatStatus.AVAILABLE) {
      throw new java.lang.IllegalArgumentException("The new seat is not available");
    }
    if (this.seat != null) {
      this.seat.setStatus(SeatStatus.AVAILABLE);
    }
    this.setSeat(newSeat);
    newSeat.setStatus(SeatStatus.RESERVED);
// generated end
    // insert your code here

  }

  /**
   * @prompt Set the status of the reservation's seat to available. Remove the associations to the
   *     seat and to the passenger.
   */
  public void cancel() {
    // generated start
if (this.seat != null) {
      this.seat.setStatus(SeatStatus.AVAILABLE);
    }
    this.setSeat(null);
    this.setPassenger(null);
// generated end
    // insert your code here

  }
}
