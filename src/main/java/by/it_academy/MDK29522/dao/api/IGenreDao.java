package by.it_academy.MDK29522.dao.api;

import by.it_academy.MDK29522.core.dto.GenreDTO;
import by.it_academy.MDK29522.dao.entity.Genre;

import java.util.GregorianCalendar;
import java.util.List;

public interface IGenreDao {
    void save(Genre genre);
    List<Genre> getAll();
    Genre getById(long id);
    Genre getByName(String name);
    boolean exist(String name);
    void edit(long id, String name);

    void delete(long id);
}
