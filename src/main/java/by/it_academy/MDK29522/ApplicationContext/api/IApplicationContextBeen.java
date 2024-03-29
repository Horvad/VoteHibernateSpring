package by.it_academy.MDK29522.ApplicationContext.api;

import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.api.IVoteService;

public interface IApplicationContextBeen {
    IArtistService getArtisService();
    IGenreService getGenreService();
    IVoteService getVoteService();
    IStatisticService getStatisticService();
}
