package moprocoeval.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryRegisterMemberTest {

  private Library library;

  @BeforeEach
  void setup() {
    library = new Library();
  }

  @Test
  void registerMember_successfullyAddsMember() {
    Member m = new Member();
    m.setMemberID("m1");

    library.registerMember(m);

    assertEquals(1, library.sizeOfMembers());
    assertTrue(library.getMembers().contains(m));
  }

  @Test
  void registerMember_establishesBidirectionalAssociation() {
    Member m = new Member();
    m.setMemberID("m1");

    library.registerMember(m);

    assertTrue(m.getLibraries().contains(library));
  }

  @Test
  void registerMember_duplicateId_throwsException() {
    Member m1 = new Member();
    m1.setMemberID("m1");

    Member m2 = new Member();
    m2.setMemberID("m1");

    library.registerMember(m1);

    assertThrows(IllegalArgumentException.class,
        () -> library.registerMember(m2));
  }
}
