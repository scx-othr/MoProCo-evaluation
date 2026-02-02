package moprocoeval.library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void registerMember(Member member) {
        if (memberExists(member.getMemberId())) {
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " already exists.");
        }
        members.add(member);
    }

    public void addBook(Book book) {
        if (bookExists(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    private boolean memberExists(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    private boolean bookExists(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}