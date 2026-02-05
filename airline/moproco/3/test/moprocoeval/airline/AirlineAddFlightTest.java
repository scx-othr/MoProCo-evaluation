package moprocoeval.airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AirlineAddFlightTest {

    @Test
    void addFlightAddsNewFlightAndSetsAssociation() {
        Airline airline = new Airline();
        Flight flight = new Flight();
        flight.setFlightNumber("LH123");

        airline.addFlight(flight);

        assertEquals(1, airline.sizeOfFlights());
        assertSame(airline, flight.getAirline());
    }

    @Test
    void addFlightRejectsDuplicateFlightNumber() {
        Airline airline = new Airline();

        Flight f1 = new Flight();
        f1.setFlightNumber("LH123");

        Flight f2 = new Flight();
        f2.setFlightNumber("LH123");

        airline.addFlight(f1);

        assertThrows(IllegalArgumentException.class,
                () -> airline.addFlight(f2));
    }
}
