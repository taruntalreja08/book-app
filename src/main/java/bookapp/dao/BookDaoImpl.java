package bookapp.dao;

import bookapp.beans.Book;
import bookapp.exceptions.BookIdExistException;
import bookapp.exceptions.BookIdNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    List<Book> books = new ArrayList<>();

    @Override
    public List<Book> findAll() {
        String query = "select * from book";
        List<Book> books = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    @Override
    public int add(Book book) {
        String idExistsQuery = "select count(*) from book where bookId = ?";
        int count = jdbcTemplate.queryForObject(idExistsQuery, Integer.class, book.getBookId());
        if (count > 0) throw new BookIdExistException();

        String query = "insert into book values (?, ?, ?, ?, ?)";
        Object[] args = {book.getBookId(), book.getTitle(), book.getAuthor(),book.getPublications(), book.getPrice()};

        int rows = jdbcTemplate.update(query, args);
        return rows;
    }

    @Override
    public Book get(int bookId) {
        String idExistsQuery = "select count(*) from book where bookId = ?";
        int count = jdbcTemplate.queryForObject(idExistsQuery, Integer.class, bookId);
        if (count == 0) throw new BookIdNotExistException();

        String query = "select * from book where bookId = ?";
        Book book = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Book.class), bookId);
        return book;
        // return books.stream().filter(book -> book.getBookId() == bookId).findFirst().orElse(null);
    }

    @Override
    public boolean delete(int bookId) {
        String idExistsQuery = "select count(*) from book where bookId = ?";
        int count = jdbcTemplate.queryForObject(idExistsQuery, Integer.class, bookId);
        if (count == 0) throw new BookIdNotExistException();

        String query = "delete from book where bookId = ?";
        int rows = jdbcTemplate.update(query, bookId);
        return rows > 0;
        // return books.removeIf(book -> book.getBookId() == bookId);
    }

    @Override
    public boolean update(Book book) {
        String idExistsQuery = "select count(*) from book where bookId = ?";
        int count = jdbcTemplate.queryForObject(idExistsQuery, Integer.class, book.getBookId());
        if (count == 0) throw new BookIdNotExistException();

        String query = "update book set title = ? , author = ? , publications = ? , price = ? where bookId = ?";
        int rows = jdbcTemplate.update(query, book.getTitle(), book.getAuthor(), book.getPublications(), book.getPrice(), book.getBookId());
        return rows > 0;
    }

    @Override
    public Book findByTitle(String title) {
        String query = "select * from book where title = ?";
        List<Book> books = jdbcTemplate.query(query, new Object[] {title}, new BeanPropertyRowMapper<>(Book.class));
        return books.isEmpty() ? null: books.get(0);
    }

    public List<Book> findByAuthor(String author) {
        String query = "select * from book where author = ?";
        List<Book> books = jdbcTemplate.query(query, new Object[] {author}, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    public List<Book> findByPublications(String publications) {
        String query = "select * from book where publications = ?";
        List<Book> books = jdbcTemplate.query(query, new Object[] {publications}, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }
}
