package br.andresgois.github.io.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.andresgois.github.io.domain.Categoria;
import br.andresgois.github.io.dtos.CategoriaDTO;
import br.andresgois.github.io.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaService categoriaService; 
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findByIdCategoria(@PathVariable Integer id){
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok(obj);
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> obj = categoriaService.findAll();
        List<CategoriaDTO> objDto = obj.stream().map(item -> new CategoriaDTO(item)).collect(Collectors.toList());
        return ResponseEntity.ok(objDto);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria){
        Categoria obj = categoriaService.create(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build(); //.body(new CategoriaDTO(categoria));
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody Categoria categoria){
        Categoria obj = categoriaService.update(id, categoria);
        return ResponseEntity.ok().body(new CategoriaDTO(obj));
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
