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
  private List<Loan> loan = new ArrayList<Loan>();

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

  public List<Loan> getLoan() {
    return (List<Loan>) Collections.unmodifiableList(this.loan);
  }

  public int sizeOfLoan() {
    return this.loan.size();
  }

  public void addToLoan(Loan newValue) {
    if (newValue != null && !this.loan.contains(newValue)) {
      this.loan.add(newValue);
      newValue.setMember(this);
    }
  }

  public void removeFromLoan(Loan oldValue) {
    if (oldValue != null && this.loan.contains(oldValue)) {
      this.loan.remove(oldValue);
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
  public Loan borrowCopy(Copy c) {
    // generated start
if (c.getStatus() != CopyStatus.AVAILABLE) {
  throw new IllegalArgumentException("Copy is not available");
}
Loan loan = new Loan();
loan.setLoanDate(new Date());
loan.setDueDate(DateUtils.addDays(new Date(), 42));
loan.setClosed(false);
loan.setMember(this);
loan.setCopy(c);
c.setStatus(CopyStatus.ON_LOAN);
return c;
// generated end
    // insert your code here

  }

  /**
   * @prompt Find a non-closed loan for the provided copy and member. If such a loan exists, set it
   *     closed, set the return date to now, and set the copy's status to available. If no such loan
   *     exists, raise an exception.
   */
  public void returnCopy(Copy c) {
    // generated start
Loan loan = this.loan.stream()
    .filter(l -> l.getCopy() == c && !l.getClosed())
    .findFirst()
    .orElseThrow(() -> new IllegalArgumentException("No non-closed loan found for copy " + c.getCopyId()));

loan.setClosed(true);
loan.setReturnDate(new Date());
c.setStatus(CopyStatus.AVAILABLE);
// generated end
    // insert your code here

  }
}
