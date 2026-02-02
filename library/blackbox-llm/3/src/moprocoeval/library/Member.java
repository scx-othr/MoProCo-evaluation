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