package bookapp.dto;

import bookapp.beans.Book;

import java.util.List;

public class GetBooksResponseDTO extends GenericResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
