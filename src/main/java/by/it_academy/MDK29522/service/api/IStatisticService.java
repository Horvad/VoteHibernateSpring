package by.it_academy.MDK29522.service.api;

import by.it_academy.MDK29522.core.dto.StatisticDTOArtists;
import by.it_academy.MDK29522.core.dto.StatisticDTOGenres;
import by.it_academy.MDK29522.dao.entity.Vote;

import java.util.List;

public interface IStatisticService {
    List<StatisticDTOArtists> getArtists();
    List<StatisticDTOGenres> getGenres();
    List<Vote> getVotes();
}
