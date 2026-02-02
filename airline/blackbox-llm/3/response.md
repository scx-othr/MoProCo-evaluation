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
        return new ArrayList<>(flights);
    }

    public void addFlight(Flight flight) throws FlightAlreadyExistsException {
        if (flights.stream().anyMatch(f -> f.getFlightNumber().equals(flight.getFlightNumber()))) {
            throw new FlightAlreadyExistsException("Flight with number " + flight.getFlightNumber() + " already exists.");
        }
        flights.add(flight);
    }
}

class FlightAlreadyExistsException extends Exception {
    public FlightAlreadyExistsException(String message) {
        super(message);
    }
}

class Flight {
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
        return new ArrayList<>(seats);
    }

    public void addSeat(Seat seat) throws SeatAlreadyExistsException {
        if (seats.stream().anyMatch(s -> s.getSeatNumber().equals(seat.getSeatNumber()))) {
            throw new SeatAlreadyExistsException("Seat with number " + seat.getSeatNumber() + " already exists.");
        }
        seats.add(seat);
        seat.setStatus(SeatStatus.AVAILABLE);
    }
}

class SeatAlreadyExistsException extends Exception {
    public SeatAlreadyExistsException(String message) {
        super(message);
    }
}

abstract class Seat {
    private String seatNumber;
    private SeatStatus status;
    private Flight flight;

    public Seat(String seatNumber, Flight flight) {
        this.seatNumber = seatNumber;
        this.flight = flight;
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

    public abstract boolean isBusiness();
}

class EconomySeat extends Seat {
    public EconomySeat(String seatNumber, Flight flight) {
        super(seatNumber, flight);
    }

    @Override
    public boolean isBusiness() {
        return false;
    }
}

class BusinessSeat extends Seat {
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
    public boolean isBusiness() {
        return true;
    }
}

enum SeatStatus {
    AVAILABLE, RESERVED
}

class Reservation {
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
        if (seat != null) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        this.seat = null;
        this.passenger = null;
    }

    public void changeSeat(Seat newSeat) throws SeatNotAvailableException {
        if (newSeat.getStatus() != SeatStatus.AVAILABLE) {
            throw new SeatNotAvailableException("Seat " + newSeat.getSeatNumber() + " is not available.");
        }
        if (seat != null) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        this.seat = newSeat;
        newSeat.setStatus(SeatStatus.RESERVED);
    }
}

class SeatNotAvailableException extends Exception {
    public SeatNotAvailableException(String message) {
        super(message);
    }
}

class Passenger {
    private String passengerId;
    private String name;
    private List<Seat> seats;

    public Passenger(String passengerId, String name) {
        this.passengerId = passengerId;
        this.name = name;
        this.seats = new ArrayList<>();
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

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public void reserveSeat(Seat seat) throws SeatNotAvailableException {
        if (seat.getStatus() != SeatStatus.AVAILABLE) {
            throw new SeatNotAvailableException("Seat " + seat.getSeatNumber() + " is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), seat, this);
        seat.setStatus(SeatStatus.RESERVED);
        seats.add(seat);
    }
}
```