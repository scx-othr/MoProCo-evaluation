package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Seat {
    public enum Status {
        AVAILABLE, RESERVED
    }

    private String seatNumber;
    private Status status;
    private Flight flight;
    private Reservation reservation;

    public Seat(String seatNumber, Flight flight) {
        this.seatNumber = seatNumber;
        this.flight = flight;
        this.status = Status.AVAILABLE;
    }

    public void reserve(Passenger passenger) {
        if (status != Status.AVAILABLE) {
            throw new IllegalStateException("Seat is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), this, passenger);
        this.reservation = reservation;
        this.status = Status.RESERVED;
    }

    public void changeReservation(Reservation reservation) {
        if (status != Status.AVAILABLE) {
            throw new IllegalStateException("Seat is not available.");
        }
        if (this.reservation != null) {
            this.reservation.getSeat().setStatus(Status.AVAILABLE);
            this.reservation.setSeat(this);
            this.status = Status.RESERVED;
        }
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}