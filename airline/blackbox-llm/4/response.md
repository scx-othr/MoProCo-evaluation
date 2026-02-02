Here is the Java source code for the airline reservation system as described:

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Airline {
    private String name;
    private List<Flight> flights;

    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        if (flights.stream().anyMatch(f -> f.getFlightNumber().equals(flight.getFlightNumber()))) {
            throw new IllegalArgumentException("Flight with the same ID already exists.");
        }
        flights.add(flight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return new ArrayList<>(flights);
    }
}
```

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addSeat(Seat seat) {
        if (seats.stream().anyMatch(s -> s.getSeatNumber().equals(seat.getSeatNumber()))) {
            throw new IllegalArgumentException("Seat with the same number already exists.");
        }
        seats.add(seat);
        seat.setStatus(Seat.Status.AVAILABLE);
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
        return new ArrayList<>(seats);
    }
}
```

```java
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
```

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EconomySeat extends Seat {
    public EconomySeat(String seatNumber, Flight flight) {
        super(seatNumber, flight);
    }
}
```

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
```

```java
package moprocoeval.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private LocalDate reservationDate;
    private Seat seat;
    private Passenger passenger;

    public Reservation(LocalDate reservationDate, Seat seat, Passenger passenger) {
        this.reservationDate = reservationDate;
        this.seat = seat;
        this.passenger = passenger;
    }

    public void cancel() {
        seat.setStatus(Seat.Status.AVAILABLE);
        seat.setReservation(null);
        passenger.getReservations().remove(this);
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
}
```

```java
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
```