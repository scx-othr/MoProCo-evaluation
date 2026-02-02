Generate the Java source code for the business logic of a library management system.
Generate private fields, public getters and setters, and additional public operations as suggested.
Follow exactly the structure and behavior described below, without inventing additional details.
Include all Java files in your response, assuming "moprocoeval.library" as package name.

## Structure

A library must support two operations: register a member and add a book.
Each library maintains a list of books and a list of members.

Each book belongs to one unique library and has an author, a title and an ISBN (all strings).

For each book, several copies are managed. Each copy knows which book it belongs to.
For each copy, the system must store a copy ID and a copy status (available or on loan).

Each copy may be connected to loans. Each loan knows which copy it refers to.
For each loan, the system must store the loan date and the due date, and optionally a return date (all three of type Date).
Loans may or may not be closed.
Loans are always connected to one member. 

Each member may be part of several libraries and may have an arbitrary number of loans.
Each member has a member ID and a name (both strings).
Members can borrow a copy, which creates a new loan.
Members may also return a copy.

## Behavior: Register member

If a member with the specified ID already exists, raise an exception.
Otherwise add the member to the library.

## Behavior: Add book

If a book with the specified ISBN already exists, raise an exception.
Otherwise add the book to the library.

## Behavior: Borrow copy

If the copy is not available, reject the request.
Otherwise create a new loan, set its loan date to the current date and the due date to six weeks from today.
Associate the loan with both the member and the copy.
Set the loan to non-closed, the copy's status to "on loan" and return the copy.

## Behavior: Return copy

Find a non-closed loan for the provided copy and member.
If such a loan exists, set it closed, set the return date to now, and set the copy's status to available.
If no such loan exists, raise an exception.
