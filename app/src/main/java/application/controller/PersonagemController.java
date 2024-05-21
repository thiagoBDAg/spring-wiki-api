package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import application.model.Personagem;
import application.repository.PersonagemRepository;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    @Autowired
    private PersonagemRepository personagemRepo;

    @GetMapping
    public Iterable<Personagem> getall(){
        return personagemRepo.findAll();
    }

    @GetMapping("/{id}")
    public Personagem getOne(@PathVariable long id){
        Optional<Personagem> result = personagemRepo.findById(id);
        if (result.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Personagem não encontrado"
            );
        }
        return result.get();
    }

    @PostMapping
    private Personagem post(@RequestBody Personagem personagem){
        return personagemRepo.save(personagem);
    }

    @PutMapping("/{id}")
    private Personagem put(@RequestBody Personagem personagem, @PathVariable long id){
        Optional<Personagem> result = personagemRepo.findById(id);
        
        if (result.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Personagem não encontrado"
            );
        }
        
        result.get().setNome(personagem.getNome());
        result.get().setImagem(personagem.getImagem());
        result.get().setIdade(personagem.getIdade());
        result.get().setSexo(personagem.getSexo());
        
        return personagemRepo.save(result.get());

    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable long id){
        if(personagemRepo.existsById(id)){
            personagemRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Personagem não encontrado"
            );
        }
        
    }
}
