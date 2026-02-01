# Airline

## Class diagram

```plantuml
@startuml

class Library {
  +registerMember(m: Member)
  +addBook(b: Book)
}

class Member {
  +memberId: String
  +name: String
  +borrowCopy(c: Copy): Loan
  +returnCopy(c: Copy)
}

class Book {
  +isbn: String
  +title: String
}

class Copy {
  +copyId: String
  +status: CopyStatus
}

class Loan {
  +loanDate: Date
  +dueDate: Date
  +close()
}

enum CopyStatus {
  Available
  OnLoan
}

Library "1" o-- "*" Book
Book "1" o-- "*" Copy
Member "1" -- "*" Loan
Copy "1" -- "0..1" Loan

@enduml
```

## Activity diagram for `Library.registerMember(m: Member)`

```plantuml
@startuml
title Operation: Library.registerMember(m: Member)

start
:Check memberId uniqueness;

if (memberId exists) then (yes)
  :Reject registration;
else (no)
  :Add m to Library.members;
endif

stop
@enduml
```

## Activity diagram for `Library.addBook(b: Book)`

```plantuml
@startuml
title Operation: Library.addBook(b: Book)

start
:Check ISBN uniqueness;

if (ISBN exists) then (yes)
  :Reject book;
else (no)
  :Add b to Library.books;
  :Ensure b has >= 1 Copy;
endif

stop
@enduml
```

## Activity diagram for `Member.borrowCopy(c: Copy)`

```plantuml
@startuml
title Operation: Member.borrowCopy(c: Copy)

start
:Request borrow(c);

if (c.status == Available) then (yes)
  :Create Loan;
  :loanDate = now;
  :dueDate = now + loanPeriod;
  :Associate Loan with Member;
  :Associate Loan with Copy;
  :c.status = OnLoan;
  :Return Loan;
else (no)
  :Reject request;
endif

stop
@enduml
```

## Activity diagram for `Member.returnCopy(c: Copy)`

```plantuml
@startuml
title Operation: Member.returnCopy(c: Copy)

start
:Find active Loan for c and Member;

if (Loan exists) then (yes)
  :Call Loan.close();
  :c.status = Available;
else (no)
  :Reject return;
endif

stop
@enduml
```

## Activity diagram for `Loan.close()`

```plantuml
@startuml
title Operation: Loan.close()

start
if (Loan already closed) then (yes)
  :Reject operation;
else (no)
  :Remove association to Copy;
  :Remove association to Member;
  :Mark Loan as closed;
endif

stop
@enduml
```

