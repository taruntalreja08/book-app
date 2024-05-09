package bookapp.exceptions;

public class BookIdExistException extends RuntimeException {
    public BookIdExistException() {
        super("Book id already exist");
    }
}
