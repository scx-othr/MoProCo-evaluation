package moprocoeval.airline;

import java.time.LocalDate;

public class Reservation {
    private LocalDate reservationDate;
    private Seat seat;
    private Passenger passenger;

    public Reservation(LocalDate reservationDate, Seat seat, Passenger passenger) {
        this.reservationDate = reservationDate;
        this.seat = seat;
        this.passenger = passenger;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void cancel() {
        seat.setStatus(Seat.Status.AVAILABLE);
        seat.setReservation(null);
        passenger.getReservations().remove(this);
    }

    public void changeSeat(Seat newSeat) throws IllegalArgumentException {
        if (newSeat.getStatus() != Seat.Status.AVAILABLE) {
            throw new IllegalArgumentException("New seat is not available.");
        }
        seat.setStatus(Seat.Status.AVAILABLE);
        seat.setReservation(null);
        newSeat.setStatus(Seat.Status.RESERVED);
        newSeat.setReservation(this);
        seat = newSeat;
    }
}