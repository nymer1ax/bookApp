package co.com.wipro.jpa.book;

import co.com.wipro.jpa.helper.AdapterOperations;
import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class BookEntityDataAdapter extends AdapterOperations<Book, BookEntityData, Long, BookEntityDataRepository> implements BookRepository {
    protected BookEntityDataAdapter(BookEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Book.class));
    }

    @Override
    public List<Book> getAll() {
        Iterable<BookEntityData> entities = this.repository.findAll();
        return toList(entities).stream()
                .map(book -> mapper.map(book, Book.class))
                .collect(Collectors.toList());
    }

    @Override
    public Book saveBook(Book book){
        BookEntityData bookEntityData = mapper.map(book, BookEntityData.class);
        BookEntityData savedEntityData = this.repository.save(bookEntityData);
        return mapper.map(savedEntityData, Book.class);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return this.repository.findById(id)
                .map(bookEntityData -> mapper.map(bookEntityData, Book.class));
    }

    @Override
    public Optional<Book> getByTitleAndAuthor(String title, String author) {
        Optional<BookEntityData> bookEntityDataOptional = this.repository.findByTitleAndAuthor(title, author);
        return bookEntityDataOptional.map(bookEntityData -> mapper.map(bookEntityData, Book.class));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
