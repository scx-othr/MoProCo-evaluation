Generate the Java source code for the business logic of an airline reservation system.
Generate private fields, public getters and setters, and additional public operations as suggested.
Follow exactly the structure and behavior described below, without inventing additional details.
Include all Java files in your response, assuming "moprocoeval.airline" as package name.

## Structure

An airline has a name and manages a list of flights.
An operation to add a flight is offered.

Each flight has a flight number and a date.
Each flight knows the airline it belongs to.
For a flight, a seat can be added. 

Each seat belongs to one flight, has a seat number and a status (available or reserved).
There are two disjoint types of seats: economy and business seats.
For business seats, the legroom in inches is stored additionally.
Seats can be reserved by passengers, which results in the creation of a reservation.

For a seat, a reservation may or may not exist; reservations are aware of their seat.
A reservation consists of a reservation date.
Reservations may cancelled upon request.
Furthermore, it is possible to change the seat of a reservation to a given new seat.
Each reservation keeps track of the passenger it was issued by.

Passengers in turn have a list of seats.
In addition, each passenger has a passenger ID and a name (both strings).

## Behavior: Add flight

If a flight with the specified ID already exists, raise an exception.
Otherwise add the flight to the airline.

## Behavior: Add seat

If a seat with the given seat number already exists, raise an exception.
Otherwise add the seat to the flight and set the seat's status to available.

## Behavior: Reserve seat

If the seat is not available, raise an exception.
Otherwise create a new reservation and associate it with the current passenger and the provided seat.
Set the reservation date to now.
Finally, set the seat's status to reserved and return the reservation.

## Behavior: Change seat of a reservation

If the new selected seat is not available, raise an exception.
Otherwise set the status of the reservation's current seat to available, associate the reservation with the new seat and set the new seat's status to reserved.

## Behavior: Cancel reservation

Set the status of the reservation's seat to available.
Remove the associations to the seat and to the passenger.