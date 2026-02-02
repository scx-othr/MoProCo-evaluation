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