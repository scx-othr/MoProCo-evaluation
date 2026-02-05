package moprocoeval.library;

import java.util.Date;

public class Loan {
  // generated properties
  private Date loanDate;
  private Date dueDate;
  private Date returnDate;
  private Boolean closed;
  // end of generated properties

  // generated associations
  private Member member;
  private Copy copy;

  // end of generated associations

  // generated getters and setters
  public Date getLoanDate() {
    return this.loanDate;
  }

  public void setLoanDate(Date loanDate) {
    this.loanDate = loanDate;
  }

  public Date getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getReturnDate() {
    return this.returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Boolean getClosed() {
    return this.closed;
  }

  public void setClosed(Boolean closed) {
    this.closed = closed;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Member getMember() {
    return this.member;
  }

  public void setMember(Member newValue) {
    if (this.member != newValue) {
      Member oldValue = member;
      if (oldValue != null) {
        this.member = null;
        oldValue.removeFromLoans(this);
      }
      this.member = newValue;
      if (newValue != null) newValue.addToLoans(this);
    }
  }

  public Copy getCopy() {
    return this.copy;
  }

  public void setCopy(Copy newValue) {
    if (this.copy != newValue) {
      Copy oldValue = copy;
      if (oldValue != null) {
        this.copy = null;
        oldValue.removeFromLoans(this);
      }
      this.copy = newValue;
      if (newValue != null) newValue.addToLoans(this);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If the loan is open, set it closed, set the return date to now, and set the copy's
   *     status to available. Otherwise, raise an exception.
   */
  public void returnCopy(Copy copy) {
    // generated start
if (!this.closed) {
      this.setClosed(true);
      this.setReturnDate(new java.util.Date());
      this.getCopy().setStatus(CopyStatus.AVAILABLE);
    } else {
      throw new java.lang.IllegalStateException("Loan is already closed");
    }
// generated end
    // insert your code here

  }
}
