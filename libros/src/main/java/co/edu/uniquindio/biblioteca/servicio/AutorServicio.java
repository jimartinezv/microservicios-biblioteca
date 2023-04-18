package co.edu.uniquindio.biblioteca.servicio;

import co.edu.uniquindio.biblioteca.DTO.AutorDTO;
import co.edu.uniquindio.biblioteca.model.Autor;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.servicio.exepciones.AutorNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class AutorServicio {
    private final AutorUtils autorUtils;

    private final AutorRepo autorRepo;

    public void delete(long id) {
        autorUtils.getAutor(id);
        autorRepo.deleteById(id);
    }

    public Autor findById(Long id) {
        return autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("AuthorController not found"));
    }

    public List<AutorDTO> findAll() {
        return autorRepo.findAll().stream().map(author -> autorUtils.convertirAutorDTO(author)).collect(Collectors.toList());
    }

    public Autor save(Autor author) {
        return autorRepo.save(author);
    }

    public Autor update(Long id, Autor author) {
        autorRepo.findById(id).orElseThrow(() -> new AutorNoEncontradoException("AuthorController not found"));
        return autorRepo.save(author);
    }
}
