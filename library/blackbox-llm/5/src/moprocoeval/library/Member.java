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