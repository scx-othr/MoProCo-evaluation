class EconomySeat extends Seat {
    public EconomySeat(String seatNumber, Flight flight) {
        super(seatNumber, flight);
    }

    @Override
    public boolean isBusiness() {
        return false;
    }
}