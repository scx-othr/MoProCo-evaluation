class Reservation {
    private LocalDate reservationDate;
    private Seat seat;
    private Passenger passenger;

    public Reservation(LocalDate reservationDate, Seat seat, Passenger passenger) {
        this.reservationDate = reservationDate;
        this.seat = seat;
        this.passenger = passenger;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void cancel() {
        if (seat != null) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        this.seat = null;
        this.passenger = null;
    }

    public void changeSeat(Seat newSeat) throws SeatNotAvailableException {
        if (newSeat.getStatus() != SeatStatus.AVAILABLE) {
            throw new SeatNotAvailableException("Seat " + newSeat.getSeatNumber() + " is not available.");
        }
        if (seat != null) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        this.seat = newSeat;
        newSeat.setStatus(SeatStatus.RESERVED);
    }
}