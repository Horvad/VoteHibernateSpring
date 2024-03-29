package by.it_academy.MDK29522.service;

import by.it_academy.MDK29522.core.dto.*;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.dao.entity.Vote;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.api.IVoteService;
import by.it_academy.MDK29522.util.comparator.ComparatorArtistStatistic;
import by.it_academy.MDK29522.util.comparator.ComparatorGenreStatistic;
import by.it_academy.MDK29522.util.comparator.ComparatorVoteStatistic;

import java.util.*;

public class StatisticService implements IStatisticService {
    IVoteService voteService;
    IGenreService genreService;
    IArtistService artistService;

    public StatisticService(IVoteService voteService, IGenreService genreService, IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    @Override
    public List<StatisticDTOArtists> getArtists() {
        List<Vote> votes = voteService.getVotes();
        List<StatisticDTOArtists> statisticArtists = new LinkedList<>();
        List<Artist> artists = artistService.getArtists();
        for(Artist artist : artists){
            statisticArtists.add(new StatisticDTOArtists(artist.getName(), artist.getId()));
        }
        for(Vote vote : votes){
            for(StatisticDTOArtists statisticArtist : statisticArtists){
                if(vote.getArtist().getId()==statisticArtist.getIdArtists()){
                    statisticArtist.addCount();
                    break;
                }
            }
        }
        Collections.sort(statisticArtists, new ComparatorArtistStatistic());
        return statisticArtists;
    }

    @Override
    public List<StatisticDTOGenres> getGenres() {
        List<Vote> votes = voteService.getVotes();
        List<StatisticDTOGenres> genres = new LinkedList<>();
        List<Genre> genreIDS = genreService.getGenres();
        for(Genre genre : genreIDS){
            genres.add(new StatisticDTOGenres(genre.getName(),genre.getId()));
        }
        for(Vote vote : votes){
            for(Genre genre : vote.getGenres()){
                for(StatisticDTOGenres genreStat : genres){
                    if(genre.getId()==genreStat.getIdGenre()){
                        genreStat.addCount();
                        break;
                    }
                }
            }
        }
        Collections.sort(genres, new ComparatorGenreStatistic());
        return genres;
    }

    @Override
    public List<Vote> getVotes() {
        List<Vote> votes = new LinkedList<>();
        List<Vote> oldVotes = voteService.getVotes();
        for(Vote vote : oldVotes){
            votes.add(vote);
        }
        Collections.sort(votes, new ComparatorVoteStatistic());
        return votes;
    }
}
