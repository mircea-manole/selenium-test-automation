package org.automation.dto;

public class BookOrderDTO {
    private BookDTO book;
    private int quantity;

    public BookOrderDTO() {}

    public BookOrderDTO(BookDTO book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
