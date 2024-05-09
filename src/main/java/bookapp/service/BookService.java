package bookapp.service;

import bookapp.beans.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(int bookId);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByPublications(String publications);

    Book findBookByTitle(String title);

    boolean deleteBook(int bookId);

    boolean updateBook(Book book);
}
