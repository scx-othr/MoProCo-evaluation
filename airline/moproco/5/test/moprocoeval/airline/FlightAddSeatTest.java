package moprocoeval.airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FlightAddSeatTest {

    @Test
    void addSeatAddsSeatAndInitializesStatus() {
        Flight flight = new Flight();
        Seat seat = new EconomySeat();
        seat.setSeatNumber("12A");

        flight.addSeat(seat);

        assertEquals(1, flight.sizeOfSeats());
        assertSame(flight, seat.getFlight());
        assertEquals(SeatStatus.AVAILABLE, seat.getStatus());
    }

    @Test
    void addSeatRejectsDuplicateSeatNumber() {
        Flight flight = new Flight();

        Seat s1 = new EconomySeat();
        s1.setSeatNumber("12A");

        Seat s2 = new EconomySeat();
        s2.setSeatNumber("12A");

        flight.addSeat(s1);

        assertThrows(IllegalArgumentException.class,
                () -> flight.addSeat(s2));
    }
}
