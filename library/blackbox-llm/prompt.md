Generate the Java source code for the business logic of a library management system.
Generate private fields, public getters and setters, and additional public operations as suggested.
Follow exactly the structure and behavior described below, without inventing additional details.

## Structure

A library must support two operations: register a given member and add a given book.
Each library maintains a list of books.

Each book belongs to one unique library and has a title and an ISBN (both are strings).

For each book, several copies are managed. Each copy knows which book it belongs to.
For each copy, the system must store a copy ID and a copy status (available or on loan).

Each copy may or may not be connected to a loan. Each loan knows which copy it refers to.
For each loan, the system must store the loan date and the due date (both of type Date).
Loans may be closed.

Loans are always connected to members. Each member may have an arbitrary number of loans.
Each member has a member ID and a name (both strings).
Members can borrow an existing copy, which creates a new loan.
Members may also return a copy.

## Behavior: Register member

If a member with the specified ID already exists, reject the registration.
Otherwise add the member to the library.

## Behavior: Add book

If a book with the specified ISBN already exists, reject adding the book.
Otherwise add the book to the library and ensure that at least one copy of it exists.

## Behavior: Borrow copy

If the copy is not available, reject the request.
Otherwise create a new loan, set its loan date to the current date and the due date to six weeks from today.
Associate the loan with both the member and the copy.
Set the copy's status to "on loan" and return the copy.

## Behavior: Return copy

Find an active loan for the provided copy and member.
If such a loan exists, close it and set the copy's status to available.
Otherwise, reject the return action.

## Behavior: Close loan

If the loan has already been closed, reject the action.
Otherwise, remove the associations from the loan to the copy and member and mark it as closed.