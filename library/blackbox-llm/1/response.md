Here is the Java source code for the library management system as described:

```java
package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void registerMember(Member member) {
        if (memberExists(member.getMemberId())) {
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " already exists.");
        }
        members.add(member);
    }

    public void addBook(Book book) {
        if (bookExists(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    private boolean memberExists(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    private boolean bookExists(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}
```

```java
package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private List<Copy> copies;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
    }

    public void addCopy(Copy copy) {
        copies.add(copy);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<Copy> getCopies() {
        return new ArrayList<>(copies);
    }
}
```

```java
package moprocoeval.library;

import java.util.Date;

public class Copy {
    private String copyId;
    private Book book;
    private CopyStatus status;

    public Copy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
        this.status = CopyStatus.AVAILABLE;
    }

    public void borrow(Member member, Date loanDate, Date dueDate) {
        if (status != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Copy is not available for borrowing.");
        }
        Loan loan = new Loan(this, member, loanDate, dueDate);
        member.addLoan(loan);
        this.status = CopyStatus.ON_LOAN;
    }

    public void returnCopy(Date returnDate) {
        if (status != CopyStatus.ON_LOAN) {
            throw new IllegalStateException("Copy is not on loan.");
        }
        Loan loan = getActiveLoan();
        if (loan == null) {
            throw new IllegalStateException("No active loan found for this copy.");
        }
        loan.close(returnDate);
        this.status = CopyStatus.AVAILABLE;
    }

    private Loan getActiveLoan() {
        for (Loan loan : member.getLoans()) {
            if (loan.getCopy() == this && !loan.isClosed()) {
                return loan;
            }
        }
        return null;
    }

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public CopyStatus getStatus() {
        return status;
    }
}
```

```java
package moprocoeval.library;

public enum CopyStatus {
    AVAILABLE,
    ON_LOAN
}
```

```java
package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<Loan> loans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
}
```

```java
package moprocoeval.library;

import java.util.Date;

public class Loan {
    private Copy copy;
    private Member member;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private boolean closed;

    public Loan(Copy copy, Member member, Date loanDate, Date dueDate) {
        this.copy = copy;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.closed = false;
    }

    public void close(Date returnDate) {
        this.returnDate = returnDate;
        this.closed = true;
    }

    public Copy getCopy() {
        return copy;
    }

    public Member getMember() {
        return member;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isClosed() {
        return closed;
    }
}
```

This code provides the basic structure and behavior for a library management system as described. The `Library` class manages the books and members, the `Book` class represents a book with its copies, the `Copy` class represents a copy of a book, the `Member` class represents a library member, and the `Loan` class represents a loan of a copy to a member. The `CopyStatus` enum defines the possible states of a copy.