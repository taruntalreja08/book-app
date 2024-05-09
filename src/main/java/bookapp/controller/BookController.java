package bookapp.controller;

import bookapp.beans.Book;
import bookapp.dto.GenericResponse;
import bookapp.dto.GetBookResponseDTO;
import bookapp.dto.GetBooksResponseDTO;
import bookapp.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/findallbooks")
    private ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookServiceImpl.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/findBookById/{bookId}")
    private ResponseEntity<GetBookResponseDTO> findBookById(@PathVariable("bookId") int bookId) {
        Book book = bookServiceImpl.getBookById(bookId);
        GetBookResponseDTO getBookResponseDTO = new GetBookResponseDTO();
        getBookResponseDTO.setBook(book);
        if (book == null) {
            getBookResponseDTO.setMessage("Book not found");
        }
        return new ResponseEntity<>(getBookResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/addbook")
    private ResponseEntity<GenericResponse> addBook(@RequestBody Book book) {
        bookServiceImpl.addBook(book);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Book added successfully");
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @PutMapping("/editbook/{bookId}")
    private ResponseEntity<GenericResponse> updateBook(@PathVariable int bookId, @RequestBody Book book) {
        book.setBookId(bookId);
        boolean updatedBook = bookServiceImpl.updateBook(book);
        GenericResponse genericResponse = new GenericResponse();

        if (!updatedBook) {
            genericResponse.setMessage("Book details were not edited");
        } else {
            genericResponse.setMessage("Book details edited successfully");
        }

        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletebook/{bookId}")
    private ResponseEntity<GenericResponse> deleteBook(@PathVariable("bookId") int bookId) {
        GenericResponse genericResponse = new GenericResponse();
        boolean isDeleted = bookServiceImpl.deleteBook(bookId);

        if (!isDeleted) {
            genericResponse.setMessage("Book was not deleted successfully");
        } else {
            genericResponse.setMessage("Book details deleted successfully");
        }

        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/findBooksByAuthor/{author}")
    private ResponseEntity<GetBooksResponseDTO> findBookByAuthor(@PathVariable String author) {
        List<Book> books = bookServiceImpl.findBooksByAuthor(author);
        GetBooksResponseDTO getBooksResponseDTO = new GetBooksResponseDTO();
        getBooksResponseDTO.setBooks(books);
        if (books == null || books.isEmpty()) {
            getBooksResponseDTO.setMessage("No matching books found with author " + author);
        }
        return new ResponseEntity<>(getBooksResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/findBookByTitle/{title}")
    private ResponseEntity<GetBookResponseDTO> findBookByTitle(@PathVariable String title) {
        Book book = bookServiceImpl.findBookByTitle(title);
        GetBookResponseDTO getBooksResponseDTO = new GetBookResponseDTO();
        getBooksResponseDTO.setBook(book);
        if (book == null) {
            getBooksResponseDTO.setMessage("No matching books found with title " + title);
        }
        return new ResponseEntity<>(getBooksResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/findBooksByPublications/{publications}")
    private ResponseEntity<GetBooksResponseDTO> findBookByPublications(@PathVariable String publications) {
        List<Book> books = bookServiceImpl.findBooksByPublications(publications);
        GetBooksResponseDTO getBooksResponseDTO = new GetBooksResponseDTO();
        getBooksResponseDTO.setBooks(books);
        if (books == null || books.isEmpty()) {
            getBooksResponseDTO.setMessage("No matching books found with publications "+publications);
        }
        return new ResponseEntity<>(getBooksResponseDTO, HttpStatus.OK);
    }
}
