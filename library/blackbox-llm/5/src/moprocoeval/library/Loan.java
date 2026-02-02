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