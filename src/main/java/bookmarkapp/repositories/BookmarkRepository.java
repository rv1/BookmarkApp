package bookmarkapp.repositories;

import bookmarkapp.models.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {
}
