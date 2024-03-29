package by.it_academy.MDK29522.dao.api;

import by.it_academy.MDK29522.dao.entity.Artist;

import java.util.List;

public interface IArtistDao {
    void save(Artist artist);
    List<Artist> getAll();
    Artist getById(long id);
    Artist getByName(String name);
    boolean exist(String name);

    void edit(long id, String name);

    void delete(long id);
}
