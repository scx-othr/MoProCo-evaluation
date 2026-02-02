package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private String passengerId;
    private String name;
    private List<Reservation> reservations;

    public Passenger(String passengerId, String name) {
        this.passengerId = passengerId;
        this.name = name;
        this.reservations = new ArrayList<>();
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public void reserveSeat(Seat seat) throws IllegalArgumentException {
        if (seat.getStatus() != Seat.Status.AVAILABLE) {
            throw new IllegalArgumentException("Seat is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), seat, this);
        reservations.add(reservation);
        seat.setStatus(Seat.Status.RESERVED);
        seat.setReservation(reservation);
    }
}