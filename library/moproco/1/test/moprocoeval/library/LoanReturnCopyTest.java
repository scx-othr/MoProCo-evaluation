package moprocoeval.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanReturnCopyTest {

  private Member member;
  private Copy copy;
  private Loan loan;

  @BeforeEach
  void setup() {
    member = new Member();
    copy = new Copy();
    copy.setStatus(CopyStatus.AVAILABLE);

    loan = member.borrowCopy(copy);
  }

  @Test
  void returnCopy_openLoan_closesLoan() {
    loan.returnCopy(copy);

    assertTrue(loan.getClosed());
    assertNotNull(loan.getReturnDate());
  }

  @Test
  void returnCopy_setsCopyAvailable() {
    loan.returnCopy(copy);

    assertEquals(CopyStatus.AVAILABLE, copy.getStatus());
  }

  @Test
  void returnCopy_closedLoan_throwsException() {
    loan.returnCopy(copy);

    assertThrows(IllegalStateException.class,
        () -> loan.returnCopy(copy));
  }
}
