package by.it_academy.MDK29522.service.api;

import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.dao.entity.Artist;

import java.util.List;

public interface IArtistService {
    void save(ArtistDTO artistDTO);
    void save(String name);
    List<Artist> getArtists();
    boolean exist(String name);
    Artist getNameForId(long id);

    Artist getIDForName(String name);
    void edit(long id, String name);

    void delete(long id);
}
