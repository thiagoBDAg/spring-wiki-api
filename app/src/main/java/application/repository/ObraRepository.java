package application.repository;

import org.springframework.data.repository.CrudRepository;
import application.model.Obra;

public interface ObraRepository extends CrudRepository<Obra, Long> {
    
}
