package by.it_academy.MDK29522.controller;

import by.it_academy.MDK29522.ApplicationContext.api.IApplicationContextBeen;
import by.it_academy.MDK29522.ApplicationContext.fabric.ApplicationContextSingleton;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.service.api.IGenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenreServlet", urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {
    private final String GENRE_PARAM_NAME = "name";
    private final IApplicationContextBeen applicationContextBeen;
    private final IGenreService service;

    public GenreServlet() {
        this.applicationContextBeen = ApplicationContextSingleton.getInstance();
        this.service = applicationContextBeen.getGenreService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<Genre> genres = service.getGenres();
        PrintWriter writer = resp.getWriter();

        for(Genre genre : genres){
            writer.write("<p>"+genre.getId()+": "+genre.getName()+"</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html, charset=UTF-8");

        Map<String,String[]> parameterMap = req.getParameterMap();
        String[] genres = parameterMap.get(GENRE_PARAM_NAME);

        if(genres==null) throw new IllegalCallerException("Список жанров не может быть пустым");
        if(genres.length==0) throw new IllegalArgumentException("Не пережано ни одного жанра");

        boolean save = false;
        for(String genre : genres){
            if(!genre.isBlank()){
                service.save(genre);
                save = true;
            }
        }
        if(!save) throw new IllegalArgumentException("Передаваемый жанр не может быть пустым");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parametrMap = req.getParameterMap();
        try {
            String[] getId = parametrMap.get("id");
            if(getId==null)
                throw new IllegalArgumentException("Не передан id");
            if(getId.length==0)
                throw new IllegalArgumentException("Не передан id");
            if(getId[0]==null)
                throw new IllegalArgumentException("Не передан id");
            String[] getName = parametrMap.get("name");
            if(getName==null)
                throw new IllegalArgumentException("Не передано имя");
            if(getName.length==0)
                throw new IllegalArgumentException("Не передано имя");
            if(getName[0]==null)
                throw new IllegalArgumentException("Не передано имя");
            if(service.exist(getName[0])){
                throw new IllegalArgumentException("Такое имя существует");
            }
            long id = Long.parseLong(getId[0]);
            service.edit(id,getName[0]);
        } catch (IllegalArgumentException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Map<String, String[]> parametrMap = req.getParameterMap();
        try {
            String[] getId = parametrMap.get("id");
            if(getId==null)
                throw new IllegalArgumentException("Не передан id");
            if(getId.length==0)
                throw new IllegalArgumentException("Не передан id");
            if(getId[0]==null)
                throw new IllegalArgumentException("Не передан id");
            long id = Long.parseLong(getId[0]);
            service.delete(id);
        } catch (IllegalArgumentException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }
    }
}
