package be.kdg.se2.spring.interfaces;

import be.kdg.se2.spring.model.DataObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Repository layer interface.
 * Place in: src/main/java/be/kdg/se2/templates/spring/interfaces/
 */
@Component
public interface RepositoryInterface {
    DataObject save(DataObject entity);
    Optional<DataObject> findById(Long id);
    DataObject findByName(String name);
    List<DataObject> findAll();
    DataObject update(DataObject entity);
    void deleteById(Long id);
    void deleteAll();
    boolean existsById(Long id);
    long count();
}
