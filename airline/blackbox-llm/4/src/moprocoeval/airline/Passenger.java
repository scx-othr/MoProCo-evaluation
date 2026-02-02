package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}