package moprocoeval.airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservationCancelTest {

    @Test
    void cancelFreesSeatAndRemovesAssociations() {
        Passenger p = new Passenger();
        Seat seat = new EconomySeat();
        seat.setStatus(SeatStatus.AVAILABLE);

        Reservation r = seat.reserve(p);
        r.cancel();

        assertEquals(SeatStatus.AVAILABLE, seat.getStatus());
        assertNull(r.getSeat());
        assertNull(r.getPassenger());
        assertNull(seat.getReservation());
    }
}
