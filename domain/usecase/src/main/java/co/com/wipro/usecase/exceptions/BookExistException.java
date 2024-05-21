package co.com.wipro.usecase.exceptions;

public class BookExistException extends  RuntimeException {
    public BookExistException(String message) {
        super(message);
    }
}
