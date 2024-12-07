package bu.eugene.map.repository;

import bu.eugene.map.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository  extends JpaRepository<ImageEntity, Integer> {
}
