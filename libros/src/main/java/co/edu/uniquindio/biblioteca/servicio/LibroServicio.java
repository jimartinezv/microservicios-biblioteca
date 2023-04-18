package co.edu.uniquindio.biblioteca.servicio;

import co.edu.uniquindio.biblioteca.DTO.LibroDTO;
import co.edu.uniquindio.biblioteca.DTO.LibroIsbnDTO;
import co.edu.uniquindio.biblioteca.model.Autor;
import co.edu.uniquindio.biblioteca.model.Genero;
import co.edu.uniquindio.biblioteca.model.Libro;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.servicio.exepciones.AutorNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicio.exepciones.LibroNoEncontradoExeption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroServicio {

    private final LibroRepo libroRepo;
    private final AutorRepo autorRepo;

    public Libro save(LibroDTO libro){

        Optional<Libro> guardado = libroRepo.findById(libro.isbn());

        if(guardado.isPresent()){
            throw new RuntimeException("El libro con el isbn "+libro.isbn()+" ya existe");
        }

        return libroRepo.save( convertir(libro) );
    }
    public void delete(String idlibro) {
        libroRepo.deleteById(idlibro);
    }
    public Libro findById(String isbn){
        return libroRepo.findById(isbn).orElseThrow(()-> new LibroNoEncontradoExeption("EL LIBRPO NO HA SIDO ENCONTRADO"));
    }

    public List<Libro> findAll(){
        return libroRepo.findAll();
    }

    public Libro update(LibroDTO libro){
        return libroRepo.save( convertir(libro) );
    }

    private Libro convertir(LibroDTO libro) throws AutorNoEncontradoException {

        List<Autor> autores = autorRepo.findAllById( libro.idAutores() );

        if(autores.size()!=libro.idAutores().size()){

            List<Long> idsExistentes = autores.stream().map(Autor::getId).toList();

            String noEncontrados = libro.idAutores()
                    .stream()
                    .filter(id -> !idsExistentes.contains(id))
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            throw new AutorNoEncontradoException("Los autores "+noEncontrados+" no existen");

        }

        Libro nuevo = Libro.builder()
                .isbn(libro.isbn())
                .nombre(libro.nombre())
                .genero(Genero.values()[libro.genero()])
                .fechaPublicacion(libro.fechaPublicacion())
                .unidades(libro.unidades())
                .autor(autores)
                .build();

        return nuevo;
    }
    public LibroIsbnDTO validarListaLibros(List<String> listaLibros) {
        List<Libro> existingBooksList = libroRepo.findAllById(listaLibros);
        boolean result = true;

        //result = false;
        List<String> idsExistentes = existingBooksList.stream().map(Libro::getIsbn).toList();
        List<String> noEncontrados = listaLibros
                .stream()
                .filter(id -> !idsExistentes.contains(id))
                .map(Object::toString)
                .collect(Collectors.toList());

        LibroIsbnDTO respuesta = new LibroIsbnDTO(idsExistentes, noEncontrados);

        return respuesta;
    }
}
