package bookapp.dto;

import bookapp.beans.Book;

public class GetBookResponseDTO extends GenericResponse {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
