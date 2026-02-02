package moprocoeval.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
  // generated properties
  private String isbn;
  private String title;
  private String author;
  // end of generated properties

  // generated associations
  private Library library;
  private List<Copy> copies = new ArrayList<Copy>();

  // end of generated associations

  // generated getters and setters
  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  // end of generated getters and setters

  // generated accessors for associations
  public Library getLibrary() {
    return this.library;
  }

  public void setLibrary(Library newValue) {
    if (this.library != newValue) {
      Library oldValue = library;
      if (oldValue != null) {
        this.library = null;
        oldValue.removeFromBooks(this);
      }
      this.library = newValue;
      if (newValue != null) newValue.addToBooks(this);
    }
  }

  public List<Copy> getCopies() {
    return (List<Copy>) Collections.unmodifiableList(this.copies);
  }

  public int sizeOfCopies() {
    return this.copies.size();
  }

  public void addToCopies(Copy newValue) {
    if (newValue != null && !this.copies.contains(newValue)) {
      this.copies.add(newValue);
      newValue.setBook(this);
    }
  }

  public void removeFromCopies(Copy oldValue) {
    if (oldValue != null && this.copies.contains(oldValue)) {
      this.copies.remove(oldValue);
      oldValue.setBook(null);
    }
  }
  // end of generated accessors for associations

  // generated operations

}
