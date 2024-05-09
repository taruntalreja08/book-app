package bookapp.dao;

import bookapp.beans.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    int add(Book book);
    Book get(int bookId);
    boolean delete(int bookId);
    boolean update(Book book);
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublications(String publications);
}
