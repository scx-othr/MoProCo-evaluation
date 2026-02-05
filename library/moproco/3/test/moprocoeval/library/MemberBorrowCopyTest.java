package moprocoeval.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberBorrowCopyTest {

  private Member member;
  private Copy copy;

  @BeforeEach
  void setup() {
    member = new Member();
    member.setMemberID("m1");

    copy = new Copy();
    copy.setStatus(CopyStatus.AVAILABLE);
  }

  @Test
  void borrowCopy_availableCopy_createsLoan() {
    Loan loan = member.borrowCopy(copy);

    assertNotNull(loan);
    assertEquals(member, loan.getMember());
    assertEquals(copy, loan.getCopy());
  }

  @Test
  void borrowCopy_setsDatesAndStatus() {
    Loan loan = member.borrowCopy(copy);

    assertNotNull(loan.getLoanDate());
    assertNotNull(loan.getDueDate());
    assertEquals(CopyStatus.ON_LOAN, copy.getStatus());
  }

  @Test
  void borrowCopy_unavailableCopy_returnsNull() {
    copy.setStatus(CopyStatus.ON_LOAN);

    Loan loan = member.borrowCopy(copy);

    assertNull(loan);
    assertEquals(0, member.sizeOfLoans());
  }
}
