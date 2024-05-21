package co.com.wipro.usecase.deletebook;

import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeleteBookUseCase {

    private final BookRepository bookRepository;
    public void delete(Long id){
        Optional<Book> existBook = bookRepository.getById(id);
        if(existBook.isPresent()){
            bookRepository.deleteById(id);
        }
    }
}
