ackage moprocoeval.airline;

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