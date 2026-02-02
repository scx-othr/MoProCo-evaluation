package moprocoeval.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
  // generated properties

  // end of generated properties

  // generated associations
  private List<Member> members = new ArrayList<Member>();
  private List<Book> books = new ArrayList<Book>();

  // end of generated associations

  // generated getters and setters

  // end of generated getters and setters

  // generated accessors for associations
  public List<Member> getMembers() {
    return (List<Member>) Collections.unmodifiableList(this.members);
  }

  public int sizeOfMembers() {
    return this.members.size();
  }

  public void addToMembers(Member newValue) {
    if (newValue != null && !this.members.contains(newValue)) {
      this.members.add(newValue);
      newValue.addToLibraries(this);
    }
  }

  public void removeFromMembers(Member oldValue) {
    if (oldValue != null && this.members.contains(oldValue)) {
      this.members.remove(oldValue);
      oldValue.removeFromLibraries(this);
    }
  }

  public List<Book> getBooks() {
    return (List<Book>) Collections.unmodifiableList(this.books);
  }

  public int sizeOfBooks() {
    return this.books.size();
  }

  public void addToBooks(Book newValue) {
    if (newValue != null && !this.books.contains(newValue)) {
      this.books.add(newValue);
      newValue.setLibrary(this);
    }
  }

  public void removeFromBooks(Book oldValue) {
    if (oldValue != null && this.books.contains(oldValue)) {
      this.books.remove(oldValue);
      oldValue.setLibrary(null);
    }
  }

  // end of generated accessors for associations

  // generated operations
  /**
   * @prompt If a member with the specified ID already exists, raise an exception. Otherwise add the
   *     member to the library.
   */
  public void registerMember(Member m) {
    // generated start
if (this.getMembers().stream().anyMatch(m -> m.getMemberID().equals(m.getMemberID()))) {
    throw new IllegalArgumentException("Member with ID " + m.getMemberID() + " already exists");
} else {
    this.addToMembers(m);
}
// generated end
    // insert your code here

  }

  /**
   * @prompt If a book with the specified ISBN already exists, raise an exception. Otherwise add the
   *     book to the library.
   */
  public void addBook(Book b) {
    // generated start
if (this.books.stream().anyMatch(book -> book.getIsbn().equals(b.getIsbn()))) {
    throw new IllegalArgumentException("Book with specified ISBN already exists.");
}
this.books.add(b);
// generated end
    // insert your code here

  }
}
