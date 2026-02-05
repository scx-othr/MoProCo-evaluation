package moprocoeval.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryAddBookTest {

  private Library library;

  @BeforeEach
  void setup() {
    library = new Library();
  }

  @Test
  void addBook_successfullyAddsBook() {
    Book b = new Book();
    b.setIsbn("isbn-1");

    library.addBook(b);

    assertEquals(1, library.sizeOfBooks());
    assertTrue(library.getBooks().contains(b));
  }

  @Test
  void addBook_setsLibraryReference() {
    Book b = new Book();
    b.setIsbn("isbn-1");

    library.addBook(b);

    assertEquals(library, b.getLibrary());
  }

  @Test
  void addBook_duplicateIsbn_throwsException() {
    Book b1 = new Book();
    b1.setIsbn("isbn-1");

    Book b2 = new Book();
    b2.setIsbn("isbn-1");

    library.addBook(b1);

    assertThrows(IllegalArgumentException.class,
        () -> library.addBook(b2));
  }
}
