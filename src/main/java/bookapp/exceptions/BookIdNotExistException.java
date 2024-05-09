package bookapp.exceptions;

public class BookIdNotExistException extends RuntimeException {
    public BookIdNotExistException() {
        super("Book id does not exist");
    }
}
