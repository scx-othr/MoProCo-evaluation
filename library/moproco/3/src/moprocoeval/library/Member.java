package moprocoeval.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
  // generated properties
  private String memberID;
  private String name;
  // end of generated properties

  // generated associations
  private List<Library> libraries = new ArrayList<Library>();
  private List<Loan> loans = new ArrayList<Loan>();

  // end of generated associations

  // generated getters and setters
  public String getMemberID() {
    return this.memberID;
  }

  public void setMemberID(String memberID) {
    this.memberID = memberID;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public List<Library> getLibraries() {
    return (List<Library>) Collections.unmodifiableList(this.libraries);
  }

  public int sizeOfLibraries() {
    return this.libraries.size();
  }

  public void addToLibraries(Library newValue) {
    if (newValue != null && !this.libraries.contains(newValue)) {
      this.libraries.add(newValue);
      newValue.addToMembers(this);
    }
  }

  public void removeFromLibraries(Library oldValue) {
    if (oldValue != null && this.libraries.contains(oldValue)) {
      this.libraries.remove(oldValue);
      oldValue.removeFromMembers(this);
    }
  }

  public List<Loan> getLoans() {
    return (List<Loan>) Collections.unmodifiableList(this.loans);
  }

  public int sizeOfLoans() {
    return this.loans.size();
  }

  public void addToLoans(Loan newValue) {
    if (newValue != null && !this.loans.contains(newValue)) {
      this.loans.add(newValue);
      newValue.setMember(this);
    }
  }

  public void removeFromLoans(Loan oldValue) {
    if (oldValue != null && this.loans.contains(oldValue)) {
      this.loans.remove(oldValue);
      oldValue.setMember(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If the copy is not available, reject the request. Otherwise create a new loan, set its
   *     loan date to the current date and the due date to six weeks from today. Associate the loan
   *     with both the member and the copy. Set the loan to non-closed, the copy's status to "on
   *     loan" and return the copy.
   */
  public Loan borrowCopy(Copy copy) {
    // generated start
if (copy == null) {
      throw new IllegalArgumentException("Copy cannot be null");
    }
    if (copy.getStatus() != CopyStatus.AVAILABLE) {
      return null;
    }
    Loan loan = new Loan();
    loan.setLoanDate(new java.util.Date());
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.setTime(loan.getLoanDate());
    cal.add(java.util.Calendar.WEEK_OF_YEAR, 6);
    loan.setDueDate(cal.getTime());
    loan.setClosed(false);
    this.addToLoans(loan);
    copy.addToLoans(loan);
    copy.setStatus(CopyStatus.ON_LOAN);
    return loan;
// generated end
    // insert your code here

  }
}
