package co.com.wipro.api.dto;

import co.com.wipro.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookRequest {
    private String title;
    private String author;
    private String genre;

    public Book toBook(){
        return Book
                .builder()
                .author(this.author)
                .title(this.title)
                .genre(this.genre)
                .build();
    }
}
