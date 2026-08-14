package yunkar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yunkar.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
