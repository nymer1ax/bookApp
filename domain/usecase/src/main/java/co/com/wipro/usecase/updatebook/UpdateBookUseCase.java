package co.com.wipro.usecase.updatebook;

import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import co.com.wipro.usecase.exceptions.BookNotExistException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateBookUseCase {
    private final BookRepository bookRepository;

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.getById(id)
                .orElseThrow(() -> new BookNotExistException("Book with id " + id + " not found"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        return bookRepository.saveBook(existingBook);
    }

}
