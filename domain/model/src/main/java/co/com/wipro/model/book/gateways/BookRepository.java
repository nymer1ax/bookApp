package co.com.wipro.model.book.gateways;


import co.com.wipro.model.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> getAll();
    Optional<Book> getById(Long id);
    Optional<Book> getByTitleAndAuthor(String title, String author);
    Book saveBook(Book book);
    void deleteById(Long id);
}
