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

    public void borrow() {
        if (status != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Copy is not available for borrowing.");
        }
        status = CopyStatus.ON_LOAN;
    }

    public void returnCopy() {
        if (status != CopyStatus.ON_LOAN) {
            throw new IllegalStateException("Copy is not on loan.");
        }
        status = CopyStatus.AVAILABLE;
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

    public void borrowCopy(Copy copy) {
        if (copy.getStatus() != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Copy is not available for borrowing.");
        }
        Loan loan = new Loan(copy, this);
        loans.add(loan);
        copy.borrow();
    }

    public void returnCopy(Copy copy) {
        Loan loan = findLoanForCopy(copy);
        if (loan == null) {
            throw new IllegalArgumentException("No loan found for this copy.");
        }
        loan.closeLoan();
        copy.returnCopy();
    }

    private Loan findLoanForCopy(Copy copy) {
        for (Loan loan : loans) {
            if (loan.getCopy().equals(copy) && !loan.isClosed()) {
                return loan;
            }
        }
        return null;
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

package moprocoeval.library;

import java.util.Date;

public class Loan {
    private Copy copy;
    private Member member;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private boolean closed;

    public Loan(Copy copy, Member member) {
        this.copy = copy;
        this.member = member;
        this.loanDate = new Date();
        this.dueDate = new Date(System.currentTimeMillis() + 6 * 7 * 24 * 60 * 60 * 1000); // Six weeks from now
        this.closed = false;
    }

    public void closeLoan() {
        if (closed) {
            throw new IllegalStateException("Loan is already closed.");
        }
        this.returnDate = new Date();
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

package moprocoeval.library;

public enum CopyStatus {
    AVAILABLE,
    ON_LOAN
}
```

This code includes all the required classes and methods as described in the structure and behavior sections. The `Library` class manages the list of books and members, the `Book` class manages the list of copies, the `Copy` class manages the status of each copy, the `Member` class manages the list of loans, and the `Loan` class manages the loan details. The `CopyStatus` enum defines the possible states of a copy.