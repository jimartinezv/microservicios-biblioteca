package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.DTO.AutorDTO;
import co.edu.uniquindio.biblioteca.DTO.Respuesta;
import co.edu.uniquindio.biblioteca.model.Autor;
import co.edu.uniquindio.biblioteca.servicio.AutorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AutorController {
    private final AutorServicio autorServicio;

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Respuesta<String>> deleteAuthor(@PathVariable long authorId) {
        autorServicio.delete(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El autor se elimino correctamente"));
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<AutorDTO>>> findAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autores encontrados", autorServicio.findAll()));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Respuesta<Autor>> findAuthorById(@PathVariable Long authorId) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autor encontrado", autorServicio.findById(authorId)));
    }

    @PostMapping
    public ResponseEntity<Respuesta<Autor>> saveAuthor(@RequestBody Autor author) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Autor creado correctamente", autorServicio.save(author)));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Respuesta<Autor>> updateAuthor(@PathVariable Long authorId, @RequestBody Autor author) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("El Autor se actualizo correctamente", autorServicio.update(authorId, author)));
    }
}
