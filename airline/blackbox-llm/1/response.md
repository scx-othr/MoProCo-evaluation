Here is the Java source code for the airline reservation system as described:

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Airline {
    private String name;
    private List<Flight> flights;

    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) throws Exception {
        if (flights.stream().anyMatch(f -> f.getFlightNumber().equals(flight.getFlightNumber()))) {
            throw new Exception("Flight with the specified ID already exists.");
        }
        flights.add(flight);
    }
}

package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightNumber;
    private LocalDate date;
    private Airline airline;
    private List<Seat> seats;

    public Flight(String flightNumber, LocalDate date, Airline airline) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.airline = airline;
        this.seats = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat) throws Exception {
        if (seats.stream().anyMatch(s -> s.getSeatNumber().equals(seat.getSeatNumber()))) {
            throw new Exception("Seat with the given seat number already exists.");
        }
        seats.add(seat);
        seat.setStatus(SeatStatus.AVAILABLE);
    }
}

package moprocoeval.airline;

import java.time.LocalDate;

public abstract class Seat {
    private String seatNumber;
    private SeatStatus status;
    private Flight flight;

    public Seat(String seatNumber, Flight flight) {
        this.seatNumber = seatNumber;
        this.flight = flight;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public abstract void reserve(Passenger passenger) throws Exception;
}

package moprocoeval.airline;

public class EconomySeat extends Seat {
    public EconomySeat(String seatNumber, Flight flight) {
        super(seatNumber, flight);
    }

    @Override
    public void reserve(Passenger passenger) throws Exception {
        if (getStatus() != SeatStatus.AVAILABLE) {
            throw new Exception("Seat is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), this, passenger);
        setStatus(SeatStatus.RESERVED);
        passenger.addReservation(reservation);
    }
}

package moprocoeval.airline;

public class BusinessSeat extends Seat {
    private int legroom;

    public BusinessSeat(String seatNumber, Flight flight, int legroom) {
        super(seatNumber, flight);
        this.legroom = legroom;
    }

    public int getLegroom() {
        return legroom;
    }

    public void setLegroom(int legroom) {
        this.legroom = legroom;
    }

    @Override
    public void reserve(Passenger passenger) throws Exception {
        if (getStatus() != SeatStatus.AVAILABLE) {
            throw new Exception("Seat is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), this, passenger);
        setStatus(SeatStatus.RESERVED);
        passenger.addReservation(reservation);
    }
}

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
        seat.setStatus(SeatStatus.AVAILABLE);
        seat = null;
        passenger = null;
    }

    public void changeSeat(Seat newSeat) throws Exception {
        if (newSeat.getStatus() != SeatStatus.AVAILABLE) {
            throw new Exception("New selected seat is not available.");
        }
        seat.setStatus(SeatStatus.AVAILABLE);
        seat = newSeat;
        newSeat.setStatus(SeatStatus.RESERVED);
    }
}

package moprocoeval.airline;

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
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}

package moprocoeval.airline;

public enum SeatStatus {
    AVAILABLE,
    RESERVED
}
```