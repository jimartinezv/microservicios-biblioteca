package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.DTO.LibroDTO;
import co.edu.uniquindio.biblioteca.DTO.LibroIsbnDTO;
import co.edu.uniquindio.biblioteca.DTO.Respuesta;
import co.edu.uniquindio.biblioteca.model.Libro;
import co.edu.uniquindio.biblioteca.servicio.LibroServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@AllArgsConstructor
public class LibroController {

    private final LibroServicio libroServicio;

    @PostMapping
    public ResponseEntity<Respuesta<Libro>> save(@RequestBody LibroDTO libroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Libro creado correctamente", libroServicio.save(libroDTO)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Libro>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", libroServicio.findAll()) );
    }

    @GetMapping("/{isbnLibro}")
    public ResponseEntity<Respuesta<Libro>> findAll(@PathVariable String isbnLibro){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", libroServicio.findById(isbnLibro)) );
    }
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable String isbn) {
        libroServicio.delete(isbn);
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Libro borrado correctamente"));
    }

    @PostMapping("/validarListaLibros")
    public ResponseEntity<Respuesta<LibroIsbnDTO>> validarListaLibros(@RequestBody List<String> listaLibros) {
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", libroServicio.validarListaLibros(listaLibros)));
    }

    @PutMapping
    public ResponseEntity<Respuesta<Libro>> udpate(@RequestBody LibroDTO libroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Libro actualizado correctamente", libroServicio.update(libroDTO)));
    }

}
