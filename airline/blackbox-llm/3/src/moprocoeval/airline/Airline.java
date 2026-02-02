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