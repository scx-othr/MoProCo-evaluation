package moprocoeval.test.moprocoeval.airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservationChangeSeatTest {

    @Test
    void changeSeatMovesReservationAndUpdatesStatuses() {
        Passenger p = new Passenger();

        Seat oldSeat = new EconomySeat();
        oldSeat.setStatus(SeatStatus.AVAILABLE);
        Reservation r = oldSeat.reserve(p);

        Seat newSeat = new EconomySeat();
        newSeat.setStatus(SeatStatus.AVAILABLE);

        r.changeSeat(newSeat);

        assertEquals(SeatStatus.AVAILABLE, oldSeat.getStatus());
        assertEquals(SeatStatus.RESERVED, newSeat.getStatus());
        assertSame(newSeat, r.getSeat());
        assertSame(r, newSeat.getReservation());
    }

    @Test
    void changeSeatFailsIfNewSeatUnavailable() {
        Passenger p = new Passenger();

        Seat oldSeat = new EconomySeat();
        oldSeat.setStatus(SeatStatus.AVAILABLE);
        Reservation r = oldSeat.reserve(p);

        Seat newSeat = new EconomySeat();
        newSeat.setStatus(SeatStatus.RESERVED);

        assertThrows(IllegalArgumentException.class,
                () -> r.changeSeat(newSeat));
    }
}
