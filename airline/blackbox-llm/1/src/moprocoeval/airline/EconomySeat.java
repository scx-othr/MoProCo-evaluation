package moprocoeval.airline;

public class EconomySeat extends Seat {
    public EconomySeat(String seatNumber, Flight flight) {
        super(seatNumber, flight);
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