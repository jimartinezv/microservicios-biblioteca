package co.edu.uniquindio.biblioteca.DTO;

import java.time.LocalDate;
import java.util.List;
public record LibroDTO(String isbn, String nombre, int genero, int unidades, LocalDate fechaPublicacion, List<Long> idAutores){


}

