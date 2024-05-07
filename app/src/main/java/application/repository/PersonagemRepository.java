package application.repository;

import org.springframework.data.repository.CrudRepository;
import application.model.Personagem;

public interface PersonagemRepository extends CrudRepository<Personagem, Long> {
    
}
