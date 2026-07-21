package repositories;

import entities.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortURLRepository extends JpaRepository<ShortURL, String> {
}
