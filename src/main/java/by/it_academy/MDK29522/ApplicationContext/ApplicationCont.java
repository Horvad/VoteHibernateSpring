package by.it_academy.MDK29522.ApplicationContext;

import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;
import by.it_academy.MDK29522.service.api.IStatisticService;
import by.it_academy.MDK29522.service.api.IVoteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

public class ApplicationCont implements IApplicationContextBeen {
    private ApplicationContext context;
    private Properties properties;

    public ApplicationCont() {
        this.context = new ClassPathXmlApplicationContext("BeenContextDB.xml");
    }

    @Override
    public IArtistService getArtisService() {
        return context.getBean(IArtistService.class);
    }

    @Override
    public IGenreService getGenreService() {
        return context.getBean(IGenreService.class);
    }

    @Override
    public IVoteService getVoteService() {
        return context.getBean(IVoteService.class);
    }

    @Override
    public IStatisticService getStatisticService() {
        return context.getBean(IStatisticService.class);
    }

}
