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
import application.model.Obra;
import application.repository.ObraRepository;

@RestController
@RequestMapping("/obras")
public class ObraController {
    @Autowired
    private ObraRepository obraRepo;

    @GetMapping
    public Iterable<Obra> getall(){
        return obraRepo.findAll();
    }

    @GetMapping("/{id}")
    public Obra getOne(@PathVariable long id){
        Optional<Obra> result = obraRepo.findById(id);
        if (result.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Obra não encontrada"
            );
        }
        return result.get();
    }

    @PostMapping
    private Obra post(@RequestBody Obra obra){
        return obraRepo.save(obra);
    }

    @PutMapping("/{id}")
    private Obra put(@RequestBody Obra obra, @PathVariable long id){
        Optional<Obra> result = obraRepo.findById(id);
        
        if (result.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Obra não encontrada"
            );
        }
        
        result.get().setTitulo(obra.getTitulo());
        result.get().setAutor(obra.getAutor());
        result.get().setGenero(obra.getGenero());
        result.get().setDuracao(obra.getDuracao());
        return obraRepo.save(result.get());

    }

    @DeleteMapping("/id")
    private void delete(@PathVariable long id){
        if(obraRepo.existsById(id)){
            obraRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Obra não encontrada"
            );
        }
        
    }
}
