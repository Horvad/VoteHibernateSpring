package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.service.api.IArtistService;

import java.util.List;


public class ArtistService implements IArtistService {
    private IArtistDao artistDao;

    public ArtistService(IArtistDao artistDao) {
        this.artistDao = artistDao;
    }
    @Override
    public void save(ArtistDTO artistDTO) {
        if(validation(artistDTO.getName())){
            Artist artist = new Artist();
            artist.setName(artistDTO.getName());
            artistDao.save(artist);
        }
    }

    @Override
    public void save(String name) {
        if(validation(name)){
            Artist artist = new Artist();
            artist.setName(name);
            artistDao.save(artist);
        }
    }

    @Override
    public List<Artist> getArtists() {
        return artistDao.getAll();
    }

    @Override
    public boolean exist(String name) {
        return artistDao.exist(name);
    }

    @Override
    public Artist getNameForId(long id) {
        return artistDao.getById(id);
    }

    @Override
    public Artist getIDForName(String name) {
        return artistDao.getByName(name);
    }

    @Override
    public void edit(long id, String name) {
        validation(name);
        artistDao.edit(id,name);
    }

    @Override
    public void delete(long id) {
        artistDao.delete(id);
    }

    private boolean validation(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException("Artist name cannot be empty");
        }
        if(artistDao.exist(name)){
            throw new IllegalArgumentException("Artist name is repeated");
        }
        return true;
    }
}
