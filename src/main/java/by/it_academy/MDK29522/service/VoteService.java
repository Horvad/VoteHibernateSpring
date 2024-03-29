package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.dao.entity.Vote;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IVoteService;

import java.util.ArrayList;
import java.util.List;

public class VoteService implements IVoteService {
    private IGenreService genreService;
    private IArtistService artistService;
    private IVoteDao voteDao;

    public VoteService(IGenreService genreService, IArtistService artistService, IVoteDao voteDao) {
        this.genreService = genreService;
        this.artistService = artistService;
        this.voteDao = voteDao;
    }

    @Override
    public List<Vote> getVotes() {
        return voteDao.get();
    }

    @Override
    public void saveVote(VoteDTO voteDTO) {
        if(voteDTO.getAbout().isBlank())
            throw new IllegalArgumentException("Не введено о себе");
        Artist artist = artistService.getNameForId(voteDTO.getArtisVote());
        if(artist==null)
            throw new IllegalArgumentException("Артиста с id = "+voteDTO.getArtisVote()+" не существует");
        List<Genre> genres = new ArrayList<>();
        for(long idGenre : voteDTO.getGenreDTOSet()){
            Genre genre = genreService.getNameForId(idGenre);
            if(genre==null)
                throw new IllegalArgumentException("Жарна с id = "+idGenre+" не существует");
            genres.add(genre);
        }
        if(voteDTO.getEmail().isBlank()){
            throw new IllegalArgumentException("Не введен email");
        }
        Vote vote = new Vote();
        vote.setArtist(artist);
        vote.setGenres(genres);
        vote.setAbout(voteDTO.getAbout());
        vote.setEmail(voteDTO.getEmail());
        voteDao.save(vote);
    }

}
