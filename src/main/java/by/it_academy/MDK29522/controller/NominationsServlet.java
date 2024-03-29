package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;
import by.it_academy.MDK29522.ApplicationContext.fabric.ApplicationContextSingleton;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.service.api.IArtistService;
import by.it_academy.MDK29522.service.api.IGenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "NominationsServlet", urlPatterns = "/nomination")
public class NominationsServlet extends HttpServlet {
    private final IApplicationContextBeen applicationContextBeen;
    private final IArtistService artistService;
    private final IGenreService genreService;

    public NominationsServlet() {
        this.applicationContextBeen = ApplicationContextSingleton.getInstance();
        this.artistService = applicationContextBeen.getArtisService();
        this.genreService = applicationContextBeen.getGenreService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<p>Номинированные артисты:</p>");
        List<Artist> artists = artistService.getArtists();

        for(Artist artist : artists){
            writer.write("<p>"+artist.getId()+" "+artist.getName()+"</p>");
        }

        List<Genre> genres = genreService.getGenres();
        writer.write("<p></p><p>Номинированные жанры:</p>");
        for(Genre genre : genres){
            writer.write("</p>"+genre.getId()+" "+genre.getName()+"</p>");
        }
    }
}
