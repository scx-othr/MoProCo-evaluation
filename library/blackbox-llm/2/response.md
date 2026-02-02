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

    public void registerMember(Member member) throws IllegalArgumentException {
        if (getMemberById(member.getMemberId()) != null) {
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " already exists.");
        }
        members.add(member);
    }

    public void addBook(Book book) throws IllegalArgumentException {
        if (getBookByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    public void borrowCopy(Member member, Copy copy) throws IllegalArgumentException {
        if (!copy.isAvailable()) {
            throw new IllegalArgumentException("Copy is not available for borrowing.");
        }
        Loan loan = new Loan(copy, member);
        loan.setLoanDate(new Date());
        loan.setDueDate(new Date(System.currentTimeMillis() + 6 * 7 * 24 * 60 * 60 * 1000)); // Six weeks from now
        copy.setStatus(CopyStatus.ON_LOAN);
        member.addLoan(loan);
    }

    public void returnCopy(Member member, Copy copy) throws IllegalArgumentException {
        Loan loan = findLoan(member, copy);
        if (loan == null) {
            throw new IllegalArgumentException("No active loan found for this member and copy.");
        }
        loan.setReturnDate(new Date());
        loan.setClosed(true);
        copy.setStatus(CopyStatus.AVAILABLE);
    }

    private Loan findLoan(Member member, Copy copy) {
        for (Loan loan : member.getLoans()) {
            if (loan.getCopy() == copy && !loan.isClosed()) {
                return loan;
            }
        }
        return null;
    }

    private Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private Member getMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }
}

package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String author;
    private String title;
    private String isbn;
    private List<Copy> copies;

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.copies = new ArrayList<>();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void addCopy(Copy copy) {
        copies.add(copy);
    }
}

package moprocoeval.library;

public class Copy {
    private String copyId;
    private CopyStatus status;
    private Book book;

    public Copy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
        this.status = CopyStatus.AVAILABLE;
    }

    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isAvailable() {
        return status == CopyStatus.AVAILABLE;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
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
        this.closed = false;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

package moprocoeval.library;

public enum CopyStatus {
    AVAILABLE,
    ON_LOAN
}
```

This code includes all the required classes and methods as described in the structure and behavior sections. The `Library` class manages the books and members, while the `Book`, `Copy`, `Member`, and `Loan` classes represent the respective entities. The `CopyStatus` enum defines the possible states of a copy.