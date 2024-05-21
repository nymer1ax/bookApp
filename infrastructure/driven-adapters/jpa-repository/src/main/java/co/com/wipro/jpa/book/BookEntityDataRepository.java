package co.com.wipro.jpa.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface BookEntityDataRepository extends CrudRepository<BookEntityData, Long>, QueryByExampleExecutor<BookEntityData> {

Optional<BookEntityData> findByTitleAndAuthor(String title, String author);
}
