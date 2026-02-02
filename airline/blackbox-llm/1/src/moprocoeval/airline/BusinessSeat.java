package moprocoeval.airline;

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

    @Override
    public void reserve(Passenger passenger) throws Exception {
        if (getStatus() != SeatStatus.AVAILABLE) {
            throw new Exception("Seat is not available.");
        }
        Reservation reservation = new Reservation(LocalDate.now(), this, passenger);
        setStatus(SeatStatus.RESERVED);
        passenger.addReservation(reservation);
    }
}