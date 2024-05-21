package co.com.wipro.usecase.createbook;

import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import co.com.wipro.usecase.exceptions.BookExistException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class CreateBookUseCase {

    private final BookRepository bookRepository;

    public Book saveBooking(Book book){
      Optional<Book> existBook = bookRepository.getByTitleAndAuthor(book.getTitle(), book.getAuthor());
      if(existBook.isPresent()){
          throw new BookExistException("Conflict:: book Already exist.");
      }
      return bookRepository.saveBook(book);
    }
}
