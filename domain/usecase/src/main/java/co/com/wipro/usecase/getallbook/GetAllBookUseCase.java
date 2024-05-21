package co.com.wipro.usecase.getallbook;

import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllBookUseCase {

    private final BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }
}
