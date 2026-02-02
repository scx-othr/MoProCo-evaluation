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