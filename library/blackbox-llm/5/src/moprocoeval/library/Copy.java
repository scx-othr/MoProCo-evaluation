package moprocoeval.library;

import java.util.Date;

public class Copy {
    private String copyId;
    private Book book;
    private CopyStatus status;

    public Copy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
        this.status = CopyStatus.AVAILABLE;
    }

    public void borrow() {
        if (status != CopyStatus.AVAILABLE) {
            throw new IllegalStateException("Copy is not available for borrowing.");
        }
        status = CopyStatus.ON_LOAN;
    }

    public void returnCopy() {
        if (status != CopyStatus.ON_LOAN) {
            throw new IllegalStateException("Copy is not on loan.");
        }
        status = CopyStatus.AVAILABLE;
    }

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public CopyStatus getStatus() {
        return status;
    }
}