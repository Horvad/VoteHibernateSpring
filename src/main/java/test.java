
import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;
import by.it_academy.MDK29522.ApplicationContext.fabric.ApplicationContextSingleton;
import by.it_academy.MDK29522.core.dto.*;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.dao.entity.Vote;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.api.IVoteService;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        IApplicationContextBeen applicationContextBeen = ApplicationContextSingleton.getInstance();
        IVoteService voteService = applicationContextBeen.getVoteService();
        IArtistService artistService = applicationContextBeen.getArtisService();
        IGenreService genreService = applicationContextBeen.getGenreService();
        IStatisticService statisticService = applicationContextBeen.getStatisticService();

        artistService.save("One");
        artistService.save("Who");
        artistService.save("Tree");
        artistService.save("Four");
        artistService.save(new ArtistDTO("Five"));
        List<Artist> artists = artistService.getArtists();
        for(Artist artist : artists){
            System.out.println(artist.toString());
        }
        artistService.edit(2L,"Two");
        artists = artistService.getArtists();
        for(Artist artist : artists){
            System.out.println(artist.toString());
        }
        genreService.save("One");
        genreService.save("Tho");
        genreService.save("Tree");
        genreService.save("Four");
        genreService.save("Five");
        genreService.edit(2L,"Two");
        List<Genre> genres = genreService.getGenres();
        for (Genre genre : genres){
            System.out.println(genre.toString());
        }

        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setEmail("My@my.my");
        voteDTO.setAbout("My");
        voteDTO.setArtisVote(2L);
        Set<Long> genreSet = new HashSet<>();
        genreSet.add(2L);
        genreSet.add(3L);
        genreSet.add(4L);
        voteDTO.setGenreDTOSet(genreSet);
        voteService.saveVote(voteDTO);
        List<Vote> votes = voteService.getVotes();
        System.out.println(votes.get(0).toString());


        List<StatisticDTOArtists> statisticDTOArtists = statisticService.getArtists();
        for(StatisticDTOArtists statisticDTOArtists1 : statisticDTOArtists){
            System.out.println(statisticDTOArtists1.toString());
        }


        List<StatisticDTOGenres> statisticDTOGenres = statisticService.getGenres();
        for(StatisticDTOGenres statisticDTOGenre : statisticDTOGenres){
            System.out.println(statisticDTOGenre.toString());
        }

        SessionFactory factory =

    }
}
