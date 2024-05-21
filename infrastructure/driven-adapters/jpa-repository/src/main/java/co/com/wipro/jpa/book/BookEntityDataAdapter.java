package co.com.wipro.jpa.book;

import co.com.wipro.jpa.helper.AdapterOperations;
import co.com.wipro.model.book.Book;
import co.com.wipro.model.book.gateways.BookRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class BookEntityDataAdapter extends AdapterOperations<Book, BookEntityData, Long, BookEntityDataRepository> implements BookRepository {
    protected BookEntityDataAdapter(BookEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Book.class));
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
