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