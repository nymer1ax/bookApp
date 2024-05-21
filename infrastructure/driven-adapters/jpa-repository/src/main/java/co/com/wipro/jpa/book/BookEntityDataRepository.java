package co.com.wipro.jpa.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BookEntityDataRepository extends CrudRepository<BookEntityData, Long>, QueryByExampleExecutor<BookEntityData> {
}
