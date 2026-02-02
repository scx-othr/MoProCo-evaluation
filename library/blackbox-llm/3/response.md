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
        if (getMemberById(member.getMemberId()) != null) {
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " already exists.");
        }
        members.add(member);
    }

    public void addBook(Book book) {
        if (getBookByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    public Book getBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Member getMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
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

    public void addCopy(Copy copy) {
        copies.add(copy);
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
        return new ArrayList<>(copies);
    }
}

package moprocoeval.library;

import java.util.Date;

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
}

package moprocoeval.library;

public enum CopyStatus {
    AVAILABLE,
    ON_LOAN
}

package moprocoeval.library;

import java.util.Date;

public class Loan {
    private Copy copy;
    private Member member;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private boolean isClosed;

    public Loan(Copy copy, Member member, Date loanDate, Date dueDate) {
        this.copy = copy;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.isClosed = false;
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
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
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

    public void borrowCopy(Copy copy, Date loanDate, Date dueDate) {
        if (copy.getStatus() != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Copy is not available for loan.");
        }
        Loan loan = new Loan(copy, this, loanDate, dueDate);
        loans.add(loan);
        copy.setStatus(CopyStatus.ON_LOAN);
    }

    public void returnCopy(Copy copy) {
        Loan loan = findOpenLoanForCopy(copy);
        if (loan == null) {
            throw new IllegalStateException("No open loan found for this copy.");
        }
        loan.setClosed(true);
        loan.setReturnDate(new Date());
        copy.setStatus(CopyStatus.AVAILABLE);
    }

    private Loan findOpenLoanForCopy(Copy copy) {
        for (Loan loan : loans) {
            if (!loan.isClosed() && loan.getCopy().equals(copy)) {
                return loan;
            }
        }
        return null;
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
        return new ArrayList<>(loans);
    }
}
```

This code includes all the required classes and methods as described in the structure and behavior sections. The `Library` class manages the list of books and members, the `Book` class represents a book with its copies, the `Copy` class represents a copy of a book, the `Loan` class represents a loan, and the `Member` class represents a library member. The `CopyStatus` enum is used to represent the status of a copy.