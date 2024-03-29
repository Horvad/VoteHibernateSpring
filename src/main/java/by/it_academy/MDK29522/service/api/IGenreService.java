package by.it_academy.MDK29522.service.api;

import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.dao.entity.Genre;

import java.util.List;

public interface IGenreService {
    void save(GenreDTO genreDTO);
    void save(String name);
    List<Genre> getGenres();
    boolean exist(String name);
    Genre getNameForId(long id);
    Genre getIdForName(String name);
    void edit(long id, String name);

    void delete(long id);
}
