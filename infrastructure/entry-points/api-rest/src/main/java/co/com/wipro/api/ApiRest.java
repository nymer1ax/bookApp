package co.com.wipro.api;
import co.com.wipro.model.book.Book;
import co.com.wipro.usecase.createbook.CreateBookUseCase;
import co.com.wipro.usecase.deletebook.DeleteBookUseCase;
import co.com.wipro.usecase.getallbook.GetAllBookUseCase;
import co.com.wipro.usecase.updatebook.UpdateBookUseCase;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final CreateBookUseCase createBookUseCase;
    private final GetAllBookUseCase getAllBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;

    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = getAllBookUseCase.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = createBookUseCase.saveBooking(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping(path = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = updateBookUseCase.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        deleteBookUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
