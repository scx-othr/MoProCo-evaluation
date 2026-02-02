package moprocoeval.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Copy {
  // generated properties
  private String copyId;
  private CopyStatus status;
  // end of generated properties

  // generated associations
  private Book book;
  private List<Loan> loans = new ArrayList<Loan>();

  // end of generated associations

  // generated getters and setters
  public String getCopyId() {
    return this.copyId;
  }

  public void setCopyId(String copyId) {
    this.copyId = copyId;
  }

  public CopyStatus getStatus() {
    return this.status;
  }

  public void setStatus(CopyStatus status) {
    this.status = status;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Book getBook() {
    return this.book;
  }

  public void setBook(Book newValue) {
    if (this.book != newValue) {
      Book oldValue = book;
      if (oldValue != null) {
        this.book = null;
        oldValue.removeFromCopies(this);
      }
      this.book = newValue;
      if (newValue != null) newValue.addToCopies(this);
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
      newValue.setCopy(this);
    }
  }

  public void removeFromLoans(Loan oldValue) {
    if (oldValue != null && this.loans.contains(oldValue)) {
      this.loans.remove(oldValue);
      oldValue.setCopy(null);
    }
  }
  // end of generated accessors for associations

  // generated operations

}
