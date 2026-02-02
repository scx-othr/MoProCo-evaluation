class Flight {
    private String flightNumber;
    private LocalDate date;
    private Airline airline;
    private List<Seat> seats;

    public Flight(String flightNumber, LocalDate date, Airline airline) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.airline = airline;
        this.seats = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats);
    }

    public void addSeat(Seat seat) throws SeatAlreadyExistsException {
        if (seats.stream().anyMatch(s -> s.getSeatNumber().equals(seat.getSeatNumber()))) {
            throw new SeatAlreadyExistsException("Seat with number " + seat.getSeatNumber() + " already exists.");
        }
        seats.add(seat);
        seat.setStatus(SeatStatus.AVAILABLE);
    }
}