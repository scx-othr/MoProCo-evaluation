package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private List<Copy> copies;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
    }

    public void addCopy(Copy copy) {
        copies.add(copy);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<Copy> getCopies() {
        return new ArrayList<>(copies);
    }
}