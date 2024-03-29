package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;
import by.it_academy.MDK29522.ApplicationContext.fabric.ApplicationContextSingleton;
import by.it_academy.MDK29522.core.dto.StatisticDTOArtists;
import by.it_academy.MDK29522.core.dto.StatisticDTOGenres;
import by.it_academy.MDK29522.dao.entity.Vote;
import by.it_academy.MDK29522.service.api.IStatisticService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StatisticServlet", urlPatterns = "/statistic")
public class StatisticServlet extends HttpServlet {
    private final IApplicationContextBeen applicationContextBeen;
    private final IStatisticService statisticService;
    public StatisticServlet() {
        this.applicationContextBeen = ApplicationContextSingleton.getInstance();
        this.statisticService = applicationContextBeen.getStatisticService();
        String sss;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<StatisticDTOArtists> artists = statisticService.getArtists();
        writer.write("<p>Артисты:</p>");
        for (StatisticDTOArtists artist : artists){
            writer.write("<p>id: "+artist.getIdArtists()+
                    ", Название: "+artist.getName()+
                    ", Колличество голосов: "+artist.getCount()+
                    "</p>");
        }

        List<StatisticDTOGenres> genres = statisticService.getGenres();
        writer.write("<p>Жанры:</p>");
        for (StatisticDTOGenres genre : genres){
            writer.write("<p>id: "+genre.getIdGenre()+
                    ", Название: "+genre.getName()+
                    ", Колличество голосов: "+genre.getCount()+
                    "</p>");
        }

        List<Vote> votes = statisticService.getVotes();
        writer.write("<p></p>");
        for (Vote vote : votes){
            writer.write("<p>id: "+vote.getId()+
                    ", О себе: "+vote.getAbout()+
                    ", дата голосования: "+vote.getDateCreate()+
                    "</p>");
        }
    }
}
