package co.edu.uniquindio.biblioteca.servicio;

import co.edu.uniquindio.biblioteca.DTO.AutorDTO;
import co.edu.uniquindio.biblioteca.model.Autor;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.servicio.exepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AutorUtils {
    private final AutorRepo autorRepo;

    public Autor getAutor(long id) {
        return autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("El Autor no existe"));
    }

    public AutorDTO convertirAutorDTO(Autor autor) {
        return new AutorDTO(autor.getId(), autor.getNombre());
    }
}
