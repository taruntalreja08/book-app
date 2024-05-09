package bookapp.service;

import bookapp.beans.Book;
import bookapp.dao.BookDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDaoImpl bookDaoImpl;

    @Override
    public void addBook(Book book) {
       bookDaoImpl.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDaoImpl.findAll();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookDaoImpl.get(bookId);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookDaoImpl.findByAuthor(author);
    }

    @Override
    public List<Book> findBooksByPublications(String publications) {
        return bookDaoImpl.findByPublications(publications);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookDaoImpl.findByTitle(title);
    }

    @Override
    public boolean deleteBook(int bookId) {
        return bookDaoImpl.delete(bookId);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDaoImpl.update(book);
    }
}
