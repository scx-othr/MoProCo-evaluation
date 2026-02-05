package moprocoeval.airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SeatReserveTest {

    @Test
    void reserveCreatesReservationAndUpdatesState() {
        Passenger p = new Passenger();
        Seat seat = new EconomySeat();
        seat.setStatus(SeatStatus.AVAILABLE);

        Reservation r = seat.reserve(p);

        assertNotNull(r);
        assertSame(p, r.getPassenger());
        assertSame(seat, r.getSeat());
        assertEquals(SeatStatus.RESERVED, seat.getStatus());
        assertNotNull(r.getReservationDate());
    }

    @Test
    void reserveFailsIfSeatNotAvailable() {
        Passenger p = new Passenger();
        Seat seat = new EconomySeat();
        seat.setStatus(SeatStatus.RESERVED);

        assertThrows(IllegalArgumentException.class,
                () -> seat.reserve(p));
    }
}
