package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

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
        if (copy.getStatus() != CopyStatus.AVAILABLE) {
            throw new IllegalArgumentException("Copy is not available for borrowing.");
        }

        Loan loan = new Loan();
        loan.setCopy(copy);
        loan.setMember(member);
        loan.setLoanDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, 6);
        loan.setDueDate(calendar.getTime());
        loan.setClosed(false);

        copy.setStatus(CopyStatus.ON_LOAN);
        copy.addLoan(loan);
    }

    public void returnCopy(Member member, Copy copy) throws IllegalArgumentException {
        Loan loan = findActiveLoan(member, copy);
        if (loan == null) {
            throw new IllegalArgumentException("No active loan found for the provided copy and member.");
        }

        loan.setClosed(true);
        loan.setReturnDate(new Date());
        copy.setStatus(CopyStatus.AVAILABLE);
    }

    private Loan findActiveLoan(Member member, Copy copy) {
        for (Loan loan : copy.getLoans()) {
            if (loan.getMember().equals(member) && !loan.isClosed()) {
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